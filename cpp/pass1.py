src = """MACRO
SWAP &P,&Q
MOV AX,&P
MOV BX,&Q
MOV &P,BX
MOV &Q,AX
MEND
START 2000
MOV CX,10
SWAP DATA1,DATA2
ADD CX,5
END""".split('\n')

mnt, mdt, pla, inter = [], [], [], []
flag = 0

for l in src:
    w = l.split()
    if not w: continue

    if w[0]  == "MACRO":
        flag = 1
    elif flag == 1:
        mnt.append((w[0], len(mdt)))
        args = w[1].split(',')
        for i in range(len(args)):
            pla.append(("?" + str(i+1), args[i]))
        mdt.append(l)
        flag = 2
    elif flag == 2:
        for p, a in pla:
            l = l.replace(a, p)
        mdt.append(l)
        if w[0] == "MEND": 
            flag = 0
    else:
        inter.append(l)

print("--- INTERMEDIATE CODE ---")
for x in inter: print(x)

print("\n--- MACRO NAME TABLE (MNT) ---")
for n, m in mnt: print(f"{n}\t{m}")

print("\n--- MACRO DEFINITION TABLE (MDT) ---")
for i, x in enumerate(mdt): print(f"{i}\t{x}")

print("\n--- PARAMETER LIST ARRAY (PLA) ---")
for p, a in pla: print(f"{p}\t{a}")





