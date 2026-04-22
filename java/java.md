# SPCC Practicals - Java Implementation

Java implementations of System Programming and Compiler Construction practicals.

## Programs Included

1. `Pass1.java` - Macro Processor Pass 1
2. `Pass2.java` - Macro Processor Pass 2
3. `Lex.java` - Lexical Analyzer
4. `Decent.java` - Recursive Descent Parser
5. `Inter.java` - Intermediate Code Generation
6. `Codeopt.java` - Code Optimization

## Prerequisites

- Java JDK 11 or later

## How To Run

```powershell
javac FileName.java
java ClassName
```

---

## 1) Macro Processor Pass 1 (`Pass1.java`)

### Theory
Pass 1 scans macro definitions and generates:
- MNT (Macro Name Table)
- MDT (Macro Definition Table)
- ALA (Argument List Array)
- Intermediate source code

### Compile & Run

```powershell
javac Pass1.java
java Pass1
```

### Sample Output

```
MNT TABLE:
INDEX    MACRO       MDT_PTR
0        COMPUTE     0

MDT TABLE:
Macro: COMPUTE
LOAD R1,?1
ADD R2,?2
STORE R1,TOTAL

ALA TABLE:
MACRO    PARAMETER    POSITION
COMPUTE  &X           1
COMPUTE  &Y           2
```

---

## 2) Macro Processor Pass 2 (`Pass2.java`)

### Theory
Pass 2 expands macro calls using tables from Pass 1:
- Reads intermediate source
- Looks up macro definitions
- Substitutes actual arguments
- Outputs expanded code

### Compile & Run

```powershell
javac Pass2.java
java Pass2
```

### Sample Output

```
SOURCE: COMPUTE A,B
EXPANSION: 
LOAD R1,A
ADD R2,B
STORE R1,TOTAL
```

---

## 3) Lexical Analyzer (`Lex.java`)

### Theory
Tokenizes source code into:
- KEYWORD
- IDENTIFIER
- NUMBER
- OPERATOR
- SEPARATOR

### Compile & Run

```powershell
javac Lex.java
java Lex
```

### Sample Input

```
int a = 10;
if (a > 5) {
    a = a + 1;
}
END
```

### Expected Output

```
KEYWORD: int
IDENTIFIER: a
OPERATOR: =
NUMBER: 10
SEPARATOR: ;
...
```

---

## 4) Recursive Descent Parser (`Decent.java`)

### Theory
Top-down parser implementing grammar:
- E -> T E'
- E' -> + T E' | ε
- T -> F T'
- T' -> * F T' | ε
- F -> ( E ) | id

Produces trace with STACK, INPUT, ACTION columns.

### Compile & Run

```powershell
javac Decent.java
java Decent
```

### Sample Input

```
a+b*c
```

### Expected Output

```
STACK           | INPUT           | ACTION
$E              | a+b*c$          | E -> T E'
$ET             | a+b*c$          | T -> F T'
...
SUCCESS: Input accepted.
```

---

## 5) Intermediate Code Generation (`Inter.java`)

### Theory
Converts expressions to:
- Postfix notation
- Three Address Code (TAC)
- Quadruples (4-tuple representation)
- Triples (3-tuple with indirect references)

### Compile & Run

```powershell
javac Inter.java
java Inter
```

### Sample Input

```
a=b+c*d
```

### Expected Output

```
[ POSTFIX ]
b c d * +

[ THREE ADDRESS CODE ]
t1 = c * d
t2 = b + t1
a = t2

[ QUADRUPLES ]
INDEX    OP     ARG1       ARG2       RESULT
0        *      c          d          t1
...

[ TRIPLES ]
INDEX    OP     ARG1       ARG2
0        *      c          d
...
```

---

## 6) Code Optimization (`Codeopt.java`)

### Theory
Demonstrates optimization techniques:
- Common Subexpression Elimination (CSE)
- Dead Code Elimination (DCE)
- Strength Reduction (e.g., x*2 → x<<1)

### Compile & Run

```powershell
javac Codeopt.java
java Codeopt
```

### Sample Input

```
2
t1=a*b
t2=a*b
5
```

### Expected Output

```
[ ORIGINAL ]
t1 = a * b
t2 = a * b

[ CSE - Common Subexpression Elimination ]
Redundant: t2 = a * b -> t2 = t1
...

[ STRENGTH REDUCTION ]
x * 2  => 10
x << 1 => 10
```

---

## Notes

- All programs accept interactive input
- Ensure FileName matches ClassName for compilation
- Intermediate files (.txt) are generated in the same directory
