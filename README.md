# SPCC Laboratory Experiments (Python)

Python implementations of standard System Programming and Compiler Design laboratory experiments.
This repository is designed for quick execution, clear terminal interaction, and easy academic demonstration in practical exams and viva sessions.

## Download Single File (curl)

Use these commands to download one file at a time from this repository.

```bash
curl -L -o pass1.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/pass1.py
curl -L -o pass2.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/pass2.py
curl -L -o lex.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/lex.py
curl -L -o decent.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/decent.py
curl -L -o inter.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/inter.py
curl -L -o codeopt.py https://raw.githubusercontent.com/sudo-veer/spcc/main/python/codeopt.py
```

## Prerequisites

- Python 3.x
- No external dependencies (only Python standard library is used)

## Experiments Guide

### 1) Macro Processor (Pass 1 + Pass 2)

Builds macro tables (MNT/MDT/ALA), generates intermediate code, and expands macro calls into final source.

#### Run Command

```bash
cd python
python pass1.py
python pass2.py
```

#### Sample Input

```text
No interactive input required.
Both scripts use in-code sample source for macro definition and expansion.
```

#### Expected Output

```text
[ MNT ]
INDEX    MACRO        MDT_PTR
0        COMPUTE      0

[ MDT ]
ID       STATEMENT
0        COMPUTE &X,&Y
1        LOAD R1,?1
2        ADD R2,?2
3        STORE R1,TOTAL
4        MEND

[ INTERMEDIATE CODE ]
START 1000
READ VAL1
READ VAL2
COMPUTE VAL1,VAL2
PRINT TOTAL
END

========== PASS 2: EXPANSION COMPLETE ==========
[FINAL EXPANDED SOURCE CODE]
START 1000
READ VAL1
READ VAL2
.* Expansion of COMPUTE
+ LOAD R1,VAL1
+ ADD R2,VAL2
+ STORE R1,TOTAL
PRINT TOTAL
END
```

### 2) Lexical Analyzer

Tokenizes input source code into KEYWORD, IDENTIFIER, NUMBER, OPERATOR, and SEPARATOR.

#### Run Command

```bash
cd python
python lex.py
```

#### Sample Input

```text
int a = 10;
if (a > 5) {
	a = a + 1;
}
END
```

#### Expected Output

```text
-----------------------------------
LEXEME          | TOKEN TYPE
-----------------------------------
int             | KEYWORD
a               | IDENTIFIER
=               | OPERATOR
10              | NUMBER
;               | SEPARATOR
if              | KEYWORD
(               | SEPARATOR
a               | IDENTIFIER
>               | OPERATOR
5               | NUMBER
)               | SEPARATOR
{               | SEPARATOR
a               | IDENTIFIER
=               | OPERATOR
a               | IDENTIFIER
+               | OPERATOR
1               | NUMBER
;               | SEPARATOR
}               | SEPARATOR
-----------------------------------
```

### 3) Recursive Descent Parser

Implements top-down parsing for expression grammar:
E -> T E', E' -> + T E' | e, T -> F T', T' -> * F T' | e, F -> (E) | id

#### Run Command

```bash
cd python
python decent.py
```

#### Sample Input

```text
a+b*c
```

#### Expected Output

```text
-----------------------------------
INPUT           | ACTION
-----------------------------------
a+b*c$          | E -> T E'
a+b*c$          | T -> F T'
a+b*c$          | Match 'a'
+b*c$           | T' -> e
+b*c$           | Match '+'
b*c$            | T -> F T'
b*c$            | Match 'b'
*c$             | Match '*'
c$              | Match 'c'
$               | T' -> e
$               | E' -> e
-----------------------------------
SUCCESS: Input accepted.
```

### 4) Intermediate Code Generation

Converts assignment expressions into postfix notation, three-address code (TAC), quadruples, and triples.

#### Run Command

```bash
cd python
python inter.py
```

#### Sample Input

```text
a = b * c + d
```

#### Expected Output

```text
[ POSTFIX ]
b c * d +

[ THREE ADDRESS CODE ]
t1 = b * c
t2 = t1 + d
a = t2

[ QUADRUPLES ]
INDEX    OP     ARG1       ARG2       RESULT
0        *      b          c          t1
1        +      t1         d          t2
2        =      t2         -          a

[ TRIPLES ]
INDEX    OP     ARG1       ARG2
0        *      b          c
1        +      (0)        d
2        =      a          (1)
```

### 5) Code Optimization Techniques

Demonstrates common optimization techniques: common subexpression elimination, dead code elimination, loop optimization concept, and strength reduction.

#### Run Command

```bash
cd python
python codeopt.py
```

#### Sample Input

```text
2
t1 = a + b
t2 = a + b
3
x = 10
dead temp = 0
y = x
5
```

#### Expected Output

```text
===== CODE OPTIMIZATION TECHNIQUES =====

--- CSE ---
Optimized Code:
t1 = a + b
t2 = t1

--- Dead Code Elimination ---
Optimized Code:
x = 10
y = x

--- Loop Optimization (LICM) ---
Sum after loop optimization = 75

--- Strength Reduction ---
After Strength Reduction (x * 2 -> x << 1): 10
```

## License

This project is licensed under the MIT License.


