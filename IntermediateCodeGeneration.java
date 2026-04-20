import java.util.*;

public class IntermediateCodeGeneration {
    static class Quad {
        String op, arg1, arg2, result;
        Quad(String op, String a1, String a2, String r) { this.op = op; arg1 = a1; arg2 = a2; result = r; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter assignment/expression (e.g., a=b*c+d or (a+b)*c):");
        String input = sc.nextLine().replaceAll("\\s+", "");
        if (input.isEmpty()) { System.out.println("Invalid input."); return; }

        String lhs = null;
        String expr = input;
        int eq = input.indexOf('=');
        if (eq != -1) {
            lhs = input.substring(0, eq);
            expr = input.substring(eq + 1);
            if (lhs.isEmpty() || expr.isEmpty()) { System.out.println("Invalid assignment."); return; }
        }

        List<String> postfix = infixToPostfix(expr);
        if (postfix.isEmpty()) { System.out.println("Invalid expression (mismatched operators/parentheses)."); return; }

        List<String> tac = new ArrayList<>();
        List<Quad> quads = new ArrayList<>();
        String finalResult = toTAC(postfix, tac, quads);
        if (finalResult == null) { System.out.println("Invalid expression for TAC generation."); return; }

        if (lhs != null) {
            tac.add(lhs + " = " + finalResult);
            quads.add(new Quad("=", finalResult, "-", lhs));
        }

        System.out.println("\nPostfix Expression:");
        System.out.println(String.join(" ", postfix));

        System.out.println("\nThree Address Code:");
        for (String line : tac) System.out.println(line);

        System.out.println("\nQuadruple Table:");
        System.out.printf("%-6s %-8s %-8s %-8s %-8s%n", "No.", "Op", "Arg1", "Arg2", "Result");
        for (int i = 0; i < quads.size(); i++) {
            Quad q = quads.get(i);
            System.out.printf("%-6d %-8s %-8s %-8s %-8s%n", i + 1, q.op, q.arg1, q.arg2, q.result);
        }
    }

    static List<String> infixToPostfix(String expr) {
        List<String> output = new ArrayList<>();
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < expr.length();) {
            char c = expr.charAt(i);

            if (Character.isLetter(c) || c == '_') {
                int j = i + 1;
                while (j < expr.length() && (Character.isLetterOrDigit(expr.charAt(j)) || expr.charAt(j) == '_')) j++;
                output.add(expr.substring(i, j));
                i = j;
            } else if (Character.isDigit(c)) {
                int j = i + 1;
                boolean dot = false;
                while (j < expr.length()) {
                    char x = expr.charAt(j);
                    if (Character.isDigit(x)) j++;
                    else if (x == '.' && !dot) { dot = true; j++; }
                    else break;
                }
                output.add(expr.substring(i, j));
                i = j;
            } else if (c == '(') {
                ops.push(c);
                i++;
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') output.add(String.valueOf(ops.pop()));
                if (ops.isEmpty()) return new ArrayList<>();
                ops.pop();
                i++;
            } else if (isOp(c)) {
                while (!ops.isEmpty() && isOp(ops.peek())
                        && (prec(ops.peek()) > prec(c) || (prec(ops.peek()) == prec(c) && c != '^'))) {
                    output.add(String.valueOf(ops.pop()));
                }
                ops.push(c);
                i++;
            } else return new ArrayList<>();
        }

        while (!ops.isEmpty()) {
            if (ops.peek() == '(') return new ArrayList<>();
            output.add(String.valueOf(ops.pop()));
        }
        return output;
    }

    static String toTAC(List<String> postfix, List<String> tac, List<Quad> quads) {
        Deque<String> st = new ArrayDeque<>();
        int t = 1;

        for (String tok : postfix) {
            if (!tok.isEmpty() && !isOp(tok.charAt(0))) st.push(tok);
            else if (tok.length() == 1 && isOp(tok.charAt(0))) {
                if (st.size() < 2) return null;
                String b = st.pop();
                String a = st.pop();
                String temp = "t" + t++;
                tac.add(temp + " = " + a + " " + tok + " " + b);
                quads.add(new Quad(tok, a, b, temp));
                st.push(temp);
            } else return null;
        }
        if (st.size() != 1) return null;
        return st.pop();
    }

    static boolean isOp(char c) { return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'; }
    static int prec(char c) { return c == '^' ? 3 : (c == '*' || c == '/' ? 2 : (c == '+' || c == '-' ? 1 : 0)); }
}
