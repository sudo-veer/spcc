print("===== CODE OPTIMIZATION TECHNIQUES =====")

# 1. Common Subexpression Elimination (CSE)
n = int(input("\n--- CSE ---\nEnter number of expressions: "))
print("Enter expressions (e.g., t1 = a + b):")
e = [input().split() for _ in range(n)]

for i in range(len(e)):
    for j in range(i + 1, len(e)):
        # If right-hand sides match (e.g., ['a', '+', 'b']), replace with previous variable
        if len(e[i]) == 5 and len(e[j]) == 5 and e[i][2:] == e[j][2:]:
            e[j] = [e[j][0], '=', e[i][0]]
print("\nOptimized Code:\n" + "\n".join(" ".join(x) for x in e))

# 2. Dead Code Elimination (DCE)
m = int(input("\n--- Dead Code Elimination ---\nEnter number of statements: "))
print("Enter statements (use 'dead' to mark unused):")
print("\nOptimized Code:\n" + "\n".join(c for _ in range(m) if "dead" not in (c := input()).lower()))

# 3. Loop Optimization (Loop Invariant Code Motion)
print("\n--- Loop Optimization (LICM) ---")
temp = 5 + 10  # Invariant logic (a + b) hoisted outside the loop
print(f"Sum after loop optimization = {sum(temp for _ in range(5))}")

# 4. Strength Reduction
print("\n--- Strength Reduction ---")
print(f"After Strength Reduction (x * 2 -> x << 1): {int(input('Enter value of x: ')) << 1}")