
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Decent {
    private final String input;
    private int pos;
    private final Deque<String> callStack = new ArrayDeque<>();

    public Decent(String input) {
        this.input = input;
    }

    public boolean parse() {
        System.out.printf("%-20s %-20s %s%n", "Stack", "Input", "Action");
        callStack.push("E");
        print("Start");

        boolean accepted = E() && end();
        print(accepted ? "Accepted" : "Rejected");
        return accepted;
    }

    private boolean E() { print("E -> T E'"); return T() && EP(); }

    private boolean EP() {
        callStack.push("E'");
        if (!peek('+') && !peek('-')) { print("E' -> e"); callStack.pop(); return true; }
        char op = input.charAt(pos++); print("Match " + op);
        boolean ok = T() && EP();
        callStack.pop();
        return ok;
    }

    private boolean T() {
        callStack.push("T");
        print("T -> F T'");
        boolean ok = F() && TP();
        callStack.pop();
        return ok;
    }

    private boolean TP() {
        callStack.push("T'");
        if (!peek('*') && !peek('/')) { print("T' -> e"); callStack.pop(); return true; }
        char op = input.charAt(pos++); print("Match " + op);
        boolean ok = F() && TP();
        callStack.pop();
        return ok;
    }

    private boolean F() {
        callStack.push("F");
        boolean ok;
        if (match('(')) {
            print("Match (");
            ok = E() && match(')');
            if (ok) print("Match )");
        } else if (readId()) {
            print("Match id");
            ok = true;
        } else if (readNum()) {
            print("Match num");
            ok = true;
        } else {
            print("Error in F");
            ok = false;
        }
        callStack.pop();
        return ok;
    }

    private boolean readId() {
        skip();
        if (pos >= input.length()) return false;

        char c = input.charAt(pos);
        if (!Character.isLetter(c) && c != '_') return false;

        pos++;
        while (pos < input.length()) {
            char ch = input.charAt(pos);
            if (!Character.isLetterOrDigit(ch) && ch != '_') break;
            pos++;
        }
        return true;
    }

    private boolean readNum() {
        skip();
        int start = pos;
        boolean hasDot = false;

        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isDigit(c)) pos++;
            else if (c == '.' && !hasDot) {
                hasDot = true;
                pos++;
            } else break;
        }
        return pos > start;
    }

    private boolean peek(char c) { skip(); return pos < input.length() && input.charAt(pos) == c; }

    private boolean match(char c) {
        if (peek(c)) {
            pos++;
            return true;
        }
        return false;
    }

    private boolean end() { skip(); return pos == input.length(); }

    private void skip() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }

    private void print(String action) {
        StringBuilder sb = new StringBuilder("$");
        Iterator<String> it = callStack.descendingIterator();
        while (it.hasNext()) sb.append(it.next());
        String inputView = input.substring(Math.min(pos, input.length())).trim() + "$";
        System.out.printf("%-20s %-20s %s%n", sb.toString(), inputView, action);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression (e.g., a+b*(c-2)):");
        String expression = sc.nextLine();

        Decent parser = new Decent(expression);
        parser.parse();
    }
}
