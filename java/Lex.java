import java.util.*;

public class Lex {
    enum Type { KEYWORD, IDENTIFIER, NUMBER, STRING, OPERATOR, SEPARATOR, UNKNOWN }

    static class Token {
        Type type;
        String lexeme;

        Token(Type type, String lexeme) {
            this.type = type;
            this.lexeme = lexeme;
        }
    }

    static final Set<String> KEYWORDS = Set.of(
            "int", "float", "double", "char", "if", "else", "while", "for", "return", "void", "true", "false"
    );
    static final Set<String> TWO_OPS = Set.of("==", "!=", ">=", "<=", "&&", "||", "++", "--", "+=", "-=", "*=", "/=");
    static final Set<Character> ONE_OPS = Set.of('+', '-', '*', '/', '%', '=', '>', '<', '!', '&', '|');
    static final Set<Character> SEPARATORS = Set.of('(', ')', '{', '}', '[', ']', ';', ',', '.');

    public static void main(String[] args) {
        String source = readInput();
        if (source.isBlank()) {
            System.out.println("No source code entered.");
            return;
        }
        printTokens(analyze(source));
    }

    static String readInput() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter code, type END on a new line to finish:");
        while (true) {
            String line = sc.nextLine();
            if (line.equals("END")) break;
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    static List<Token> analyze(String s) {
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            char ch = s.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            }

            if (Character.isLetter(ch) || ch == '_') {
                int j = i;
                while (j < s.length() && (Character.isLetterOrDigit(s.charAt(j)) || s.charAt(j) == '_')) j++;
                String lexeme = s.substring(i, j);
                Type t = KEYWORDS.contains(lexeme) ? Type.KEYWORD : Type.IDENTIFIER;
                tokens.add(new Token(t, lexeme));
                i = j;
                continue;
            }

            if (Character.isDigit(ch)) {
                int j = i;
                boolean dot = false;
                while (j < s.length()) {
                    char c = s.charAt(j);
                    if (Character.isDigit(c)) j++;
                    else if (c == '.' && !dot) { dot = true; j++; }
                    else break;
                }
                tokens.add(new Token(Type.NUMBER, s.substring(i, j)));
                i = j;
                continue;
            }

            if (ch == '"') {
                int j = i + 1;
                while (j < s.length()) {
                    char c = s.charAt(j);
                    if (c == '\\' && j + 1 < s.length()) { j += 2; continue; }
                    if (c == '"') { j++; break; }
                    j++;
                }
                tokens.add(new Token(Type.STRING, s.substring(i, Math.min(j, s.length()))));
                i = j;
                continue;
            }

            if (i + 1 < s.length()) {
                String two = s.substring(i, i + 2);
                if (TWO_OPS.contains(two)) {
                    tokens.add(new Token(Type.OPERATOR, two));
                    i += 2;
                    continue;
                }
            }

            if (ONE_OPS.contains(ch)) tokens.add(new Token(Type.OPERATOR, String.valueOf(ch)));
            else if (SEPARATORS.contains(ch)) tokens.add(new Token(Type.SEPARATOR, String.valueOf(ch)));
            else tokens.add(new Token(Type.UNKNOWN, String.valueOf(ch)));
            i++;
        }
        return tokens;
    }

    static void printTokens(List<Token> tokens) {
        for (Token t : tokens) {
            System.out.printf("%-20s %s%n", t.lexeme, t.type);
        }
    }
}
