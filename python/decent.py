# Grammar: E->TE', E'->+TE'|e, T->FT', T'->*FT'|e, F->(E)|id
text = input("Enter string to parse (e.g., (a+b)*c): ").replace(" ", "")
pos = 0

def log(action):
    # Prints the current remaining input and the action being taken
    print(f"{text[pos:]+'$':<15} | {action}")

def match(expected):
    global pos
    # Matches exact characters OR any letter if we expect an 'id'
    if pos < len(text) and (text[pos] == expected or (expected == 'id' and text[pos].isalpha())):
        log(f"Match '{text[pos]}'")
        pos += 1
        return True
    return False

# --- Recursive Parsing Functions ---
def E():  log("E -> T E'"); return T() and EP()
def EP(): return (T() and EP()) if match('+') else (log("E' -> e") or True)

def T():  log("T -> F T'"); return F() and TP()
def TP(): return (F() and TP()) if match('*') else (log("T' -> e") or True)

def F():
    if match('id'): return True
    if match('('): return E() and match(')')
    log("Syntax Error"); return False

# --- Main Execution ---
print("\n" + "-" * 35)
print(f"{'INPUT':<15} | {'ACTION'}")
print("-" * 35)

# A successful parse means E() returns True AND we reached the end of the string
if E() and pos == len(text):
    print("-" * 35 + "\nSUCCESS: Input accepted.")
else:
    print("-" * 35 + "\nERROR: Input rejected.")