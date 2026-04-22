# SPCC Practicals - C++ Implementation

C++ implementations of System Programming and Compiler Construction practicals.

## Programs Included

1. `lex.cpp` - Lexical Analyzer
2. `decent.cpp` - Recursive Descent Parser
3. `inter.cpp` - Intermediate Code Generation
4. `codeopt.cpp` - Code Optimization

## Prerequisites

- GCC/Clang compiler (C++11 or later)
- Windows: Use MinGW or Visual Studio Build Tools

## How To Run

```bash
g++ -o program_name program_name.cpp
./program_name
```

---

## 1) Lexical Analyzer (`lex.cpp`)

### Theory
Tokenizes source code into:
- KEYWORD
- IDENTIFIER
- NUMBER
- OPERATOR
- SEPARATOR

### Compile & Run

```bash
g++ -o lex lex.cpp
./lex
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

## 2) Recursive Descent Parser (`decent.cpp`)

### Theory
Top-down parser implementing grammar:
- E -> T E'
- E' -> + T E' | ε
- T -> F T'
- T' -> * F T' | ε
- F -> ( E ) | id

Prints trace with STACK, INPUT, ACTION columns.

### Compile & Run

```bash
g++ -o decent decent.cpp
./decent
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

## 3) Intermediate Code Generation (`inter.cpp`)

### Theory
Converts expressions to:
- Postfix notation
- Three Address Code (TAC)
- Quadruples
- Triples

### Compile & Run

```bash
g++ -o inter inter.cpp
./inter
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
```

---

## 4) Code Optimization (`codeopt.cpp`)

### Theory
Demonstrates optimization techniques:
- Common Subexpression Elimination (CSE)
- Dead Code Elimination (DCE)
- Strength Reduction

### Compile & Run

```bash
g++ -o codeopt codeopt.cpp
./codeopt
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
```

---

## Notes

- All programs accept interactive input
- Compilation may require `-std=c++11` flag on older compilers
- On Windows, use `.\program_name` instead of `./program_name`
