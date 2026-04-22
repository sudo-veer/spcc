src = """MACRO
COMPUTE &X,&Y
LOAD R1,&X
ADD R2,&Y
STORE R1,TOTAL
MEND
START 1000
READ VAL1
READ VAL2
COMPUTE VAL1,VAL2
PRINT TOTAL
END""".split('\n')

mnt_name = ""
mdt = []
f_args = []
inter = []
flag = 0

for l in src:
    w = l.split()
    if not w: continue
    if w[0] == "MACRO":
        flag = 1
    elif flag == 1:
        mnt_name = w[0]
        f_args = w[1].split(',')
        mdt.append(l)
        flag = 2
    elif flag == 2:
        for i in range(len(f_args)):
            l = l.replace(f_args[i], "?" + str(i+1))
        mdt.append(l)
        if w[0] == "MEND": flag = 0
    else:
        inter.append(l)

print("========== PASS 1: DATABASES GENERATED ==========\n")
print("[INTERMEDIATE SOURCE]")
for x in inter: print(x)

print("\n[MACRO NAME TABLE (MNT)]\nMNTC\tMACRO_ID\tSTARTING_MDTC")
print(f"0\t{mnt_name}\t\t0")

print("\n[MACRO DEFINITION TABLE (MDT)]\nMDTC\tINSTRUCTION_ENTRY")
for i, x in enumerate(mdt): print(f"{i}\t{x}")

print("\n[PARAMETER LIST ARRAY (PLA)]\nPOS_INDEX\tFORMAL_ARGUMENT")
for i, x in enumerate(f_args): print(f"?{i+1}\t\t{x}")

print("\n========== PASS 2: EXPANSION COMPLETE ==========\n")
print("[FINAL EXPANDED SOURCE CODE]")
print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
for l in inter:
    w = l.split()
    if w[0] == mnt_name:
        print(f".* Expansion of {mnt_name}")
        a_args = w[1].split(',')
        for m_line in mdt[1:-1]:
            exp_line = m_line
            for i in range(len(a_args)):
                exp_line = exp_line.replace("?" + str(i+1), a_args[i])
            print("+ " + exp_line)
    else:
        print(l)
print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

