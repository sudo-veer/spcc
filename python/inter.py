import re

ops = {"+": 1, "-": 1, "*": 2, "/": 2, "^": 3}

def tokenize(expr):
    # re.findall automatically extracts variables, numbers, and operators while ignoring spaces
    return re.findall(r'[A-Za-z_]\w*|\d+(?:\.\d+)?|[()+\-*/^]', expr)

def infix_to_postfix(tokens):
    st, out = [], []
    for t in tokens:
        if t not in ops and t not in "()": 
            out.append(t)
        elif t == "(": 
            st.append(t)
        elif t == ")":
            while st and st[-1] != "(": out.append(st.pop())
            if st: st.pop()  # Discard the '('
        else:
            while st and st[-1] in ops and (ops[st[-1]] > ops[t] or (ops[st[-1]] == ops[t] and t != "^")):
                out.append(st.pop())
            st.append(t)
    return out + st[::-1]  # Append any remaining operators from the stack

def generate(postfix, lhs):
    st, tac, quads, triples = [], [], [], []
    for t in postfix:
        if t not in ops:
            st.append((t, t))
            continue
            
        # Pop the top two elements: b is right operand, a is left operand
        (b_name, b_ref), (a_name, a_ref) = st.pop(), st.pop()
        temp = f"t{len(tac) + 1}"
        
        tac.append(f"{temp} = {a_name} {t} {b_name}")
        quads.append((len(quads), t, a_name, b_name, temp))
        triples.append((len(triples), t, a_ref, b_ref))
        st.append((temp, f"({len(triples) - 1})"))
        
    final_name, final_ref = st[0]
    tac.append(f"{lhs} = {final_name}")
    quads.append((len(quads), "=", final_name, "-", lhs))
    triples.append((len(triples), "=", lhs, final_ref))
    return tac, quads, triples

# --- Main Execution ---
try:
    user_input = input("Enter assignment expression (example: a = b * c + d):\n").strip()
    lhs, rhs = user_input.split("=", 1)
    
    tokens = tokenize(rhs)
    postfix = infix_to_postfix(tokens)
    tac, quads, triples = generate(postfix, lhs.strip())
    
    print("\n[ POSTFIX ]\n" + " ".join(postfix))
    
    print("\n[ THREE ADDRESS CODE ]")
    for line in tac: print(line)
    
    print("\n[ QUADRUPLES ]")
    print(f"{'INDEX':<8} {'OP':<6} {'ARG1':<10} {'ARG2':<10} {'RESULT'}")
    for i, op, a1, a2, res in quads: 
        print(f"{i:<8} {op:<6} {a1:<10} {a2:<10} {res}")
    
    print("\n[ TRIPLES ]")
    print(f"{'INDEX':<8} {'OP':<6} {'ARG1':<10} {'ARG2'}")
    for i, op, a1, a2 in triples: 
        print(f"{i:<8} {op:<6} {a1:<10} {a2}")

except ValueError:
    print("Invalid input. Please ensure you use the format: result = expression")
except IndexError:
    print("Invalid expression syntax. Check your operators and operands.")