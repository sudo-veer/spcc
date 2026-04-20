# SPCC Practicals (MU Sem 6)

This repository contains Java implementations for core System Programming and Compiler Construction (SPCC) practicals.

## Included Programs

1. `Pass1.java` - Macro Processor Pass 1
2. `LexicalAnalyzer.java` - Lexical Analysis
3. `RecursiveDescentParser.java` - Recursive Descent Parsing with trace
4. `IntermediateCodeGeneration.java` - Postfix + Three Address Code + Quadruples
5. `CodeOptimization.java` - TAC optimization passes
6. `CodeOptimizationTechniques.java` - Menu-style optimization techniques (CSE, DCE, etc.)

## Prerequisite

- Java JDK 17+ (or any modern JDK)

## How To Run

Use PowerShell from project root:

```powershell
javac <FileName>.java
java <ClassName>
```

Example:

```powershell
javac LexicalAnalyzer.java
java LexicalAnalyzer
```

---

## 1) Macro Processor Pass 1 (`Pass1.java`)

### Theory (short)
Pass 1 of macro processor scans macro definitions, builds:
- MNT (Macro Name Table)
- MDT (Macro Definition Table)
- Intermediate source for later expansion

### Start Command

```powershell
javac Pass1.java
java Pass1
```

### Sample Input
Input program is hardcoded in source.

### Expected Output (sample)

```text
Pass 1 completed.
Created mnt.txt, mdt.txt, intermediate.txt

MNT:
COMPUTE 0

MDT:
COMPUTE &X,&Y
LOAD R1,?1
ADD R2,?2
STORE R1,TOTAL
MEND
```

---

## 2) Lexical Analyzer (`LexicalAnalyzer.java`)

### Theory (short)
Lexical Analyzer converts source code into token stream such as:
- KEYWORD
- IDENTIFIER
- NUMBER
- OPERATOR
- SEPARATOR

### Start Command

```powershell
javac LexicalAnalyzer.java
java LexicalAnalyzer
```

### Sample Input

```text
int a = 10;
if (a > 5) {
	a = a + 1;
}
END
```

### Expected Output (sample)

```text
KEYWORD int
IDENTIFIER a
OPERATOR =
NUMBER 10
SEPARATOR ;
...
```

---

## 3) Recursive Descent Parser (`RecursiveDescentParser.java`)

### Theory (short)
Recursive descent parser is top-down parsing where each non-terminal is implemented as a function.
Current grammar style used:
- E -> T E'
- E' -> (+|-) T E' | e
- T -> F T'
- T' -> (*|/) F T' | e
- F -> (E) | id | num

It also prints trace columns: `Stack`, `Input`, `Action`.

### Start Command

```powershell
javac RecursiveDescentParser.java
java RecursiveDescentParser
```

### Sample Input

```text
a+b*(c-2)
```

### Expected Output (sample)

```text
Stack                Input                Action
$E                   a+b*(c-2)$           Start
$E                   a+b*(c-2)$           E -> T E'
...
$E                   $                    Accepted
```

---

## 4) Intermediate Code Generation (`IntermediateCodeGeneration.java`)

### Theory (short)
ICG converts expressions to machine-independent intermediate form.
This program generates:
- Postfix expression
- Three Address Code (TAC)
- Quadruple representation

### Start Command

```powershell
javac IntermediateCodeGeneration.java
java IntermediateCodeGeneration
```

### Sample Input

```text
a=b*c+d
```

### Expected Output

```text
Postfix Expression:
b c * d +

Three Address Code:
t1 = b * c
t2 = t1 + d
a = t2

Quadruple Table:
No.    Op       Arg1     Arg2     Result
1      *        b        c        t1
2      +        t1       d        t2
3      =        t2       -        a
```

---

## 5) Code Optimization Passes (`CodeOptimization.java`)

### Theory (short)
This program applies standard optimization passes on TAC-like input:
- Constant folding
- Constant propagation
- Copy propagation
- Algebraic simplification
- Dead code elimination

### Start Command

```powershell
javac CodeOptimization.java
java CodeOptimization
```

### Sample Input

```text
7
t1 = 2 + 3
t2 = t1 * 1
t3 = t2 + 0
t4 = t3 * 0
a = t3
b = a
t5 = b + 4
```

### Expected Output (sample)

```text
Optimized TAC:
t1 = 5
t2 = t1
t3 = t2
a = t3
b = t3
```

---

## 6) Code Optimization Techniques (`CodeOptimizationTechniques.java`)

### Theory (short)
This file is practical-oriented and mirrors typical MU lab flow:
1. Common Subexpression Elimination
2. Dead Code Elimination
3. Loop Invariant Code Motion (demo)
4. Strength Reduction

### Start Command

```powershell
javac CodeOptimizationTechniques.java
java CodeOptimizationTechniques
```

### Sample Input

```text
4
t1 = a + b
t2 = a + b
t3 = c + d
t4 = a + b
4
a = b + c
dead t1 = a + b
x = y + z
dead temp = a + 5
6
```

### Expected Output (sample)

```text
===== CODE OPTIMIZATION TECHNIQUES =====

--- Common Subexpression Elimination ---
...
Optimized Code:
t1 = a + b
t2 = t1
t3 = c + d
t4 = t2

--- Dead Code Elimination ---
...
Optimized Code:
a = b + c
x = y + z

--- Loop Optimization (Loop Invariant Code Motion) ---
Sum after loop optimization = 75

--- Strength Reduction ---
After Strength Reduction (x * 2 -> x << 1): 12

===== OPTIMIZATION COMPLETE =====
```

---

## Notes

- Keep expression/operator spacing as shown in sample wherever format-sensitive.
- For interactive programs, finish input exactly as prompt asks (for example, `END` in lexical analyzer).
- If a run fails, compile that specific file again before running.