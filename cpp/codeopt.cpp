def is_num(s):
    # Returns True if the string is not empty and the first character is a digit
    return bool(s) and s[0].isdigit()

def main():
    n = int(input("Enter number of TAC lines: "))
    
    # Initialize lists to store the components of each TAC line
    res, eq, a1, op, a2 = [], [], [], [], []
    
    print("\nEnter TAC (Example: t1 = a + 0):")
    for _ in range(n):
        # Read the line and split it by spaces
        line = input().strip().split()
        
        # Ensure we have exactly 5 parts (e.g., "t1", "=", "a", "+", "0")
        if len(line) == 5:
            res.append(line[0])
            eq.append(line[1])
            a1.append(line[2])
            op.append(line[3])
            a2.append(line[4])
        else:
            print("Invalid format. Please enter as: result = arg1 operator arg2")
            return

    print("\n>>> STEP-BY-STEP OPTIMIZATION <<<")
    print("-" * 33)
    
    for i in range(n):
        print(f"Line: {res[i]} = {a1[i]} {op[i]} {a2[i]}")
        
        # 1. Constant Folding
        if is_num(a1[i]) and is_num(a2[i]):
            v1, v2 = int(a1[i]), int(a2[i])
            ans = 0
            
            if op[i] == "+": ans = v1 + v2
            elif op[i] == "-": ans = v1 - v2
            elif op[i] == "*": ans = v1 * v2
            elif op[i] == "/": ans = v1 // v2  # Using // for integer division like C++
            
            a1[i] = str(ans)
            op[i] = ""
            a2[i] = ""
            print(f" -> Constant Folding: {res[i]} = {a1[i]}\n")
            
        # 2. Algebraic Simplification (Addition/Subtraction by 0)
        elif (op[i] == "+" and a2[i] == "0") or (op[i] == "-" and a2[i] == "0"):
            op[i] = ""
            a2[i] = ""
            print(f" -> Algebraic Simplification: {res[i]} = {a1[i]}\n")
            
        # 3. Algebraic Simplification (Multiplication/Division by 1)
        elif (op[i] == "*" and a2[i] == "1") or (op[i] == "/" and a2[i] == "1"):
            op[i] = ""
            a2[i] = ""
            print(f" -> Algebraic Simplification: {res[i]} = {a1[i]}\n")
            
        # 4. Strength Reduction (Multiplication by 2 becomes Addition)
        elif op[i] == "*" and a2[i] == "2":
            op[i] = "+"
            a2[i] = a1[i]
            print(f" -> Strength Reduction: {res[i]} = {a1[i]} + {a2[i]}\n")
            
        # 5. Null Sequence (Multiplication by 0)
        elif op[i] == "*" and a2[i] == "0":
            a1[i] = "0"
            op[i] = ""
            a2[i] = ""
            print(f" -> Null Sequence: {res[i]} = 0\n")
            
        else:
            print(" -> No Optimization Needed.\n")
            
    print(">>> FINAL OPTIMIZED CODE <<<")
    print("-" * 28)
    for i in range(n):
        if op[i] != "":
            print(f"{res[i]} = {a1[i]} {op[i]} {a2[i]}")
        else:
            print(f"{res[i]} = {a1[i]}")

if __name__ == "__main__":
    main()