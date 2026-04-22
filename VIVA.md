SPCC VIVA QUESTION BANK (MU Computer Engineering)
Semester: As per Mumbai University SPCC syllabus

How to use this sheet:
- Start with Module 1 to Module 6 in order.
- Read each answer in 2-4 lines for viva speaking.
- Practice with examples from your lab programs.

MODULE 1: Introduction to System Software
Q1) What is system software? State its goals.
Ans: System software is a set of programs that manages hardware and supports application execution. Its goals are resource efficiency, hardware abstraction, reliability, and user convenience.

Q2) Differentiate system software and application software.
Ans: System software provides a platform and controls resources (OS, compiler, linker). Application software solves user tasks (browser, editor, payroll app).

Q3) What is system programming?
Ans: System programming focuses on developing low-level software such as compilers, loaders, assemblers, and OS modules that interact closely with hardware.

Q4) Explain assembler, compiler, and interpreter.
Ans: Assembler converts assembly instructions to machine code. Compiler translates full high-level program before execution. Interpreter translates and executes statement by statement.

Q5) Compiler vs interpreter in error handling.
Ans: Compiler reports many errors after compilation and creates object/executable code. Interpreter reports errors immediately line by line and usually does not create separate object code.

Q6) What is a linker?
Ans: A linker combines object files, resolves external symbols, links libraries, and produces an executable.

Q7) What is a loader?
Ans: Loader places executable code in memory, performs final relocation if required, and starts execution.

Q8) Role of OS as system software.
Ans: OS manages CPU, memory, I/O, files, and security. It provides services to applications through system calls and process management.

Q9) What is a macro processor?
Ans: It expands macro calls into macro definitions before actual translation. It improves coding speed and reduces repeated source statements.

Q10) Role of editor and debugger.
Ans: Editor is used to create and modify source code. Debugger helps inspect runtime state, breakpoints, and errors.

Q11) List major system programs in translation flow.
Ans: Editor -> Preprocessor/Macro processor -> Compiler/Assembler -> Linker -> Loader -> Execution under OS.

Q12) What is bootstrapping in system software?
Ans: Bootstrapping is the process of loading and starting the operating system using a small initial loader.

Q13) What is a cross-compiler?
Ans: A cross-compiler runs on one platform but generates code for another target platform.

Q14) What are language processors?
Ans: Tools that convert one language form to another, such as assembler, compiler, interpreter, preprocessor, and source-to-source translator.

Q15) Why is system software performance critical?
Ans: Any overhead in system software affects all applications, so efficiency directly impacts total system performance.

MODULE 2: Assemblers
Q1) Define assembler and its output.
Ans: An assembler translates assembly language to machine/object code and may generate symbol table, relocation, and listing files.

Q2) Single-pass vs two-pass assembler.
Ans: Single-pass assembles in one scan and struggles with forward references. Two-pass builds symbol information in Pass 1 and generates code in Pass 2.

Q3) Role of Pass 1 in two-pass assembler.
Ans: Assign addresses using location counter, process pseudo-ops, and build symbol/literal tables.

Q4) Role of Pass 2 in two-pass assembler.
Ans: Translate mnemonics to opcodes, substitute symbol addresses, resolve literals, and produce object code.

Q5) What are forward references?
Ans: Symbol usage before its definition. They are resolved in Pass 2 or by backpatching techniques.

Q6) What is location counter (LC)?
Ans: LC tracks current address during assembly and is updated according to instruction or data size.

Q7) What is OPTAB?
Ans: Opcode table that maps mnemonics to machine opcode and instruction format.

Q8) What is SYMTAB?
Ans: Symbol table storing label name, address, and attributes used for address resolution.

Q9) What is LITTAB and POOLTAB?
Ans: LITTAB stores literal values and assigned addresses. POOLTAB points to start index of each literal pool.

Q10) Explain pseudo-operations/directives.
Ans: Assembler directives (START, END, ORIGIN, EQU, LTORG, DS, DC) guide assembly but do not directly produce executable instructions.

Q11) What is backpatching?
Ans: Temporary placeholders are kept for unresolved addresses and patched after symbol definitions are available.

Q12) Absolute vs relocatable object code.
Ans: Absolute code has fixed addresses. Relocatable code can be loaded at different memory locations with relocation support.

Q13) Why is intermediate code generated in Pass 1?
Ans: It preserves parsed instruction details so Pass 2 can generate final object code efficiently.

Q14) What causes assembler errors?
Ans: Invalid opcode, undefined symbols, duplicate labels, wrong operands, invalid literals, and directive misuse.

Q15) Advantages of two-pass assembler.
Ans: Better forward-reference handling, cleaner symbol resolution, and reliable object generation for complex programs.

MODULE 3: Macros and Macro Processor
Q1) Define macro.
Ans: A macro is a named template of statements expanded at compile/preprocessing time.

Q2) Macro vs subroutine/function.
Ans: Macro expands inline (faster runtime, larger code). Function uses call-return (smaller code, runtime overhead).

Q3) What is macro processor?
Ans: Software that records macro definitions and expands macro calls using argument substitution.

Q4) What is a macro definition?
Ans: It contains macro name, parameter list, and macro body between MACRO and MEND.

Q5) What is macro call?
Ans: Invocation of macro with actual arguments; processor replaces formal parameters with actual values.

Q6) Simple vs parameterized macro.
Ans: Simple macro has no arguments. Parameterized macro accepts arguments for reusable, flexible expansion.

Q7) Positional vs keyword parameters.
Ans: Positional parameters depend on argument order. Keyword parameters use named assignment and allow default values.

Q8) What is nested macro call?
Ans: Macro body may invoke another macro; processor expands in proper sequence.

Q9) What are conditional macro expansions?
Ans: Expansion controlled by conditions (AIF/AGO style or IF-like constructs), enabling selective code generation.

Q10) Explain two-pass macro processor.
Ans: Pass 1 creates MNT/MDT and parameter metadata. Pass 2 expands each macro call using ALA and emits expanded source.

Q11) What is MNT?
Ans: Macro Name Table storing macro name and pointer to its definition in MDT.

Q12) What is MDT?
Ans: Macro Definition Table storing macro body lines in encoded form.

Q13) What is ALA?
Ans: Argument List Array that maps formal parameters to actual arguments during expansion.

Q14) Benefits of macro usage.
Ans: Less repetitive coding, standardization of patterns, easy maintenance of repeated logic.

Q15) Limitations of macros.
Ans: Code size growth, difficult debugging after expansion, and no runtime decision flexibility.

MODULE 4: Loaders and Linkers
Q1) Define loader and list its functions.
Ans: Loader places executable into memory, performs allocation, relocation, and prepares program for execution.

Q2) Define linker.
Ans: Linker combines object modules and libraries, resolves external references, and creates executable image.

Q3) Why is linking required?
Ans: Programs are built as multiple modules; linking resolves inter-module symbol dependencies.

Q4) What is relocation?
Ans: Adjustment of address-sensitive code/data when actual load address differs from assumed address.

Q5) Absolute loader vs relocating loader.
Ans: Absolute loader loads at fixed address without changes. Relocating loader adjusts addresses dynamically.

Q6) What is direct linking loader?
Ans: Loader that performs linking and relocation while loading, without a separate full linking stage.

Q7) Static linking vs dynamic linking.
Ans: Static linking embeds library code into executable. Dynamic linking resolves library references at load/run time.

Q8) Dynamic loading vs dynamic linking.
Ans: Dynamic loading loads routine only when needed. Dynamic linking resolves external library references at runtime.

Q9) Advantages of dynamic linking.
Ans: Smaller executable size, shared libraries across processes, easier library updates.

Q10) What is a relocation bit/mask?
Ans: Indicator in object code showing which fields require relocation.

Q11) What is an external symbol table in linking?
Ans: Table of exported and imported symbols used by linker to resolve cross-module references.

Q12) What is entry point?
Ans: Starting address from where execution begins, usually main or language runtime start symbol.

Q13) What are loader passes?
Ans: Many designs use Pass 1 for allocation/symbol collection and Pass 2 for relocation/loading.

Q14) Common linker errors.
Ans: Undefined reference, multiple definition, missing library, architecture mismatch.

Q15) End-to-end flow from source to execution.
Ans: Source -> compilation/assembly -> object modules -> linking -> executable -> loading -> process execution.

MODULE 5: Compiler Analysis Phase
Q1) List major compiler phases.
Ans: Lexical analysis, syntax analysis, semantic analysis, intermediate code generation, optimization, and code generation.

Q2) What is lexical analysis?
Ans: It scans character stream and groups lexemes into tokens like identifier, keyword, operator, literal.

Q3) Token vs lexeme vs pattern.
Ans: Token is category, lexeme is actual text, pattern is rule used to match lexemes.

Q4) Role of finite automata in scanner.
Ans: DFA/NFA models are used to recognize token patterns efficiently.

Q5) What does a lexical analyzer output?
Ans: Token stream with attributes (e.g., pointer to symbol table entry, numeric value).

Q6) What is syntax analysis?
Ans: Parser checks whether token sequence follows grammar and builds parse tree/AST.

Q7) CFG role in parsing.
Ans: Context-free grammar defines valid language structures used by parser construction.

Q8) Top-down vs bottom-up parsing.
Ans: Top-down derives from start symbol; bottom-up reduces input to start symbol.

Q9) What is LL(1)?
Ans: Predictive parser that uses one lookahead token and left-to-right scan with leftmost derivation.

Q10) What is shift-reduce parsing?
Ans: Bottom-up strategy using shift input tokens and reduce handles by grammar rules.

Q11) What is SLR parser?
Ans: Simple LR parser using LR(0) items with FOLLOW sets to construct ACTION/GOTO tables.

Q12) What is operator precedence parsing?
Ans: Expression parsing method based on precedence and associativity relations among operators.

Q13) What is semantic analysis?
Ans: Checks declarations, type consistency, scope rules, function compatibility, and overall meaning.

Q14) What are syntax-directed definitions?
Ans: Grammar productions augmented with semantic rules/attributes used for translation and checking.

Q15) What are common analysis-phase errors?
Ans: Invalid token, syntax mismatch, undeclared identifier, type mismatch, and scope violations.

MODULE 6: Compiler Synthesis Phase
Q1) What is synthesis phase?
Ans: Backend part of compiler that converts IR to optimized target machine code.

Q2) Why is intermediate representation (IR) used?
Ans: IR provides machine-independent form enabling optimization and retargeting.

Q3) Types of IR.
Ans: Syntax tree/AST, postfix notation, three-address code, control flow graph, SSA form.

Q4) What is three-address code (TAC)?
Ans: IR where each statement has at most one operator on two operands, often using temporaries.

Q5) Explain quadruples.
Ans: Record format (op, arg1, arg2, result), convenient for optimization and code generation.

Q6) Explain triples and indirect triples.
Ans: Triples refer to results by instruction position; indirect triples add pointer table for easier reordering.

Q7) What is code optimization?
Ans: Transformation of IR to improve speed/space while preserving program semantics.

Q8) Machine-independent optimizations examples.
Ans: Constant folding, common subexpression elimination, dead code elimination, copy propagation.

Q9) Machine-dependent optimizations examples.
Ans: Register allocation, instruction scheduling, peephole optimization, target-specific instruction selection.

Q10) What is code generation?
Ans: Selection and emission of target instructions, register usage, addressing modes, and memory access.

Q11) What is a basic block?
Ans: Maximal straight-line code sequence with single entry and single exit.

Q12) What is a flow graph?
Ans: Directed graph where nodes are basic blocks and edges represent possible control flow.

Q13) What is register allocation challenge?
Ans: Limited registers require mapping many temporaries efficiently; spilling to memory increases cost.

Q14) What is peephole optimization?
Ans: Local pattern-based improvement on small instruction windows after code generation.

Q15) Complete flow from IR to machine code.
Ans: IR generation -> optimization -> basic block and CFG analysis -> instruction selection -> register allocation -> target code emission.

Additional MU Viva Rapid-Fire (Important)
Q1) Why is two-pass approach used in both assembler and macro processor?
Ans: First pass collects definitions/tables; second pass performs accurate replacement/translation using collected metadata.

Q2) Which tables are essential in Pass 1 of assembler lab?
Ans: SYMTAB, LITTAB, POOLTAB, and intermediate code table.

Q3) Why is LTORG used?
Ans: It forces allocation of pending literals at current LC instead of waiting for END.

Q4) Why can macros increase object size?
Ans: Every macro call expands inline, duplicating code.

Q5) Difference between parsing and semantic checking.
Ans: Parsing validates grammar structure; semantic checking validates meaning and type rules.

Q6) Why is TAC preferred in compiler labs?
Ans: It breaks complex expressions into simple statements, making optimization and code generation easier.

Q7) What is the practical difference between linker error and loader error?
Ans: Linker error happens during executable creation; loader error happens while starting program in memory.

Q8) Give one example of machine-independent optimization.
Ans: Replace x = 2 * 3 by x = 6 (constant folding).

Q9) What is symbol scope in compiler context?
Ans: Region of program where identifier binding remains valid.

Q10) In viva, how to explain complete translation chain in one line?
Ans: Source code is analyzed, translated into intermediate form, optimized, converted to machine code, linked, loaded, and executed.

ALL THE BEST FOR MU SPCC VIVA
