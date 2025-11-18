Initial Design Assumptions:

Input Constraints
- The linter operates on compiled Java .class files, not source .java files.
- All .class files provided are valid and readable via the ASM library.
- The input files are located locally and accessible by file path.

Scope of Analysis
- - The linter performs static analysis only; no runtime behavior or dynamic loading is considered.
- The focus is on three specific checks, each independent from one another.
- Organizational checks (like project-level dependency checks) are not in scope for the current version.

Execution Model
- The linter runs as a command-line tool. No graphical interface is required for the core functionality.
- Results are output to stdout or a simple structured report file; no database or log management system is used.

Dependency Usage
- The ASM library is assumed to be available and compatible with the Java version used.
- The implementation assumes basic familiarity with bytecode structure and ASM visitor patterns.

Performance Considerations
- Performance optimizations (e.g., caching, parallel processing) are not prioritized unless needed.
- Analysis is performed sequentially on each .class file; large-scale codebases are outside the current performance testing scope.

Design Flexibility
- New checks can be added by implementing the Check interface and registering via CheckManager.
- Future architectural enhancements (e.g., config-based check selection, GUI) are deferred but possible due to modular design.
