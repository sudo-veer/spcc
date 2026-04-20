import java.util.*;

public class CodeOptimizationTechniques {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] e;

        System.out.println("\n===== CODE OPTIMIZATION TECHNIQUES =====");

        System.out.println("\n--- Common Subexpression Elimination ---");
        int n = readInt(sc, "Enter number of expressions: ");
        e = new String[n][4];

        System.out.println("Enter expressions (format: result = arg1 op arg2)");
        for (int i = 0; i < n; i++) {
            while (true) {
                String[] parsed = parseExpr(sc.nextLine());
                if (parsed != null) {
                    e[i] = parsed;
                    break;
                }
                System.out.println("Invalid expression format. Use: result = arg1 op arg2");
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sameExpr(e[i], e[j])) {
                    e[j][2] = "=";
                    e[j][1] = e[i][0];
                    e[j][3] = "";
                }
            }
        }

        System.out.println("\nOptimized Code:");
        for (String[] ex : e) {
            if (ex[3].isEmpty()) System.out.println(ex[0] + " = " + ex[1]);
            else System.out.println(ex[0] + " = " + ex[1] + " " + ex[2] + " " + ex[3]);
        }

        System.out.println("\n--- Dead Code Elimination ---");
        int m = readInt(sc, "Enter number of statements: ");

        System.out.println("Enter statements (use word 'dead' to mark unused statement):");
        List<String> code = new ArrayList<>();
        for (int i = 0; i < m; i++) code.add(sc.nextLine());

        System.out.println("\nOptimized Code:");
        for (String line : code) if (!line.toLowerCase().contains("dead")) System.out.println(line);

        System.out.println("\n--- Loop Optimization (Loop Invariant Code Motion) ---");
        int a = 5, b = 10, sum = 0, temp = a + b;
        for (int i = 0; i < 5; i++) sum += temp;
        System.out.println("Sum after loop optimization = " + sum);

        System.out.println("\n--- Strength Reduction ---");
        int x = readInt(sc, "Enter value of x: ");
        System.out.println("After Strength Reduction (x * 2 -> x << 1): " + (x << 1));

        System.out.println("\n===== OPTIMIZATION COMPLETE =====");
    }

    static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (Exception ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    static String[] parseExpr(String s) {
        s = s.trim();
        if (s.isEmpty() || !s.contains("=")) return null;

        String[] lr = s.split("=", 2);
        if (lr.length != 2) return null;

        String l = lr[0].trim();
        String r = lr[1].trim().replaceAll("([+\\-*/])", " $1 ").replaceAll("\\s+", " ").trim();
        String[] rhs = r.split(" ");
        if (l.isEmpty() || rhs.length != 3) return null;
        if (!(rhs[1].equals("+") || rhs[1].equals("-") || rhs[1].equals("*") || rhs[1].equals("/"))) return null;
        return new String[] { l, rhs[0], rhs[1], rhs[2] };
    }

    static boolean sameExpr(String[] a, String[] b) {
        return a[1].equals(b[1]) && a[2].equals(b[2]) && a[3].equals(b[3]);
    }
}
