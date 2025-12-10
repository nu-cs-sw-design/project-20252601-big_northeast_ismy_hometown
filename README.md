# Project: JavaGuard

## Contributors
Wendy Huang, Yindi Zhao

## Dependencies
- JDK 11 or higher
- Gradle (wrapper included)

## Testing the Linter

### Automated Test Script:
**Windows (PowerShell or CMD):**
```bash
.\test_linter.bat
```
**Linux/Mac:**
```bash
chmod +x test_linter.sh
./test_linter.sh
```
You can also manually test each test case or add in your new ones in different format

## Test Cases

Test files are located in `src/test/java/testcases/`:

- **GoodDesignExample.java** - Example of good design (should pass all checks)
- **badClassNaming.java** - Class name starts with lowercase (should trigger ClassNamingCheck)
- **BadFieldNaming.java** - Field names start with uppercase (should trigger FieldNamingCheck)
- **BadMethodNaming.java** - Method names start with uppercase (should trigger MethodNamingCheck)
- **MissingHashCode.java** - Has equals() but no hashCode() (should trigger EqualsHashCodeCheck)
- **MissingEquals.java** - Has hashCode() but no equals() (should trigger EqualsHashCodeCheck)
- **ProgramToConcreteClass.java** - Uses ArrayList/HashMap directly (should trigger ProgramToInterfaceCheck)
- **PublicFields.java** - Public non-final fields (should trigger PublicFieldCheck)
- **allBadExample.java** - Multiple violations (should trigger multiple checks)

### Architecture:
- Check `src/main/java/core/` for:
  - `ASMHandler.java` - Handles ASM parsing
  - `CheckManager.java` - Manages checks
  - `LinterCore.java` - Orchestrates the linter
  - `ReportGenerator.java` - Formats output
- Check `src/main/java/checks/` for all 6 check implementations
- Check `src/main/java/example/MyFirstLinter.java` - Simple entry point
