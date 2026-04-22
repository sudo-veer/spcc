src = [
    "START 1000",
    "MACRO",
    "COMPUTE &X,&Y",
    "LOAD R1,&X",
    "ADD R2,&Y", 
    "STORE R1,TOTAL",
    "MEND",
    "READ VAL1",
    "READ VAL2",
    "COMPUTE VAL1,VAL2", 
    "PRINT TOTAL",
    "END"]
mnt, mdt, ala, inter = [], [], [], []
in_macro, formal = False, []

for line in src:
    words = line.split()
    if not words: continue
    if words[0] == "MACRO": in_macro = True; continue
    if in_macro and not formal:
        macro = words[0]
        formal = words[1].split(",") if len(words) > 1 else []
        mnt.append((macro, len(mdt)))
        mdt.append(line)
        for i, arg in enumerate(formal, 1):
            ala.append((macro, arg.strip(), f"?{i}"))
        continue
    if in_macro:
        for i, arg in enumerate(formal, 1):
            line = line.replace(arg.strip(), f"?{i}")
        mdt.append(line)
        if words[0] == "MEND": in_macro, formal = False, []
    else:
        inter.append(line)

for fname, data in [("mnt.txt", [(f"{n} {i}") for n, i in mnt]), 
                     ("mdt.txt", mdt), 
                     ("ala.txt", [(f"{m} {a} {p}") for m, a, p in ala]), 
                     ("intermediate.txt", inter)]:
    with open(fname, "w", encoding="utf-8") as f:
        for row in data: f.write(row + "\n")

print("\n[ MNT ]\nINDEX    MACRO        MDT_PTR")
for i, (n, idx) in enumerate(mnt):
    print(f"{i:<8} {n:<12} {idx:<8}")
print("\n[ MDT ]\nID       STATEMENT")
for i, row in enumerate(mdt):
    print(f"{i:<8} {row:<25}")
print("\n[ ALA ]\nMACRO        PARAM      INDEX")
for m, a, p in ala:
    print(f"{m:<12} {a:<10} {p:<6}")
print("\n[ INTERMEDIATE CODE ]")
for row in inter:
    print(row)

