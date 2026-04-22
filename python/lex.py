# Lexical Analyzer - Simple Scanner
import re

print("Enter your code (type 'END' on a new line to finish):")
lines = []
while (line := input()) != "END":
    lines.append(line)
code = "\n".join(lines)

patterns = [
    ("KEYWORD", r"\b(int|float|double|char|if|else|while|for|return|void|true|false)\b"),
    ("NUMBER", r"\b\d+(\.\d+)?\b"),
    ("IDENTIFIER", r"\b[a-zA-Z_]\w*\b"),
    ("OPERATOR", r"==|!=|>=|<=|\+\+|--|[+\-*/%><!=]"),
    ("SEPARATOR", r"[(){}\[\];,.]"),
    ("WHITESPACE", r"\s+"),
]

regex = "|".join(f"(?P<{name}>{pat})" for name, pat in patterns)
print("-" * 35)
print(f"{'LEXEME':<15} | {'TOKEN TYPE'}")
print("-" * 35)
for match in re.finditer(regex, code):
    if match.lastgroup != "WHITESPACE":
        print(f"{match.group():<15} | {match.lastgroup}")
print("-" * 35)

