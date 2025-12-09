package example;

import checks.*;
import core.*;

import java.io.IOException;

public class MyFirstLinter {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java example.MyFirstLinter <input-path> [<input-path> ...]");
            System.out.println("  input-path can be:");
            System.out.println("    - A fully qualified class name (e.g., java.lang.String)");
            System.out.println("    - A path to a .class file");
            System.out.println("    - A path to a directory containing .class files");
            System.exit(1);
        }

        ASMHandler asmHandler = new ASMHandler();
        CheckManager checkManager = new CheckManager();
        ReportGenerator reportGenerator = new ReportGenerator();

        checkManager.addCheck(new ClassNamingCheck());
        checkManager.addCheck(new FieldNamingCheck());
        checkManager.addCheck(new MethodNamingCheck());
        checkManager.addCheck(new EqualsHashCodeCheck());
        checkManager.addCheck(new ProgramToInterfaceCheck());
        checkManager.addCheck(new PublicFieldCheck());

        LinterCore linterCore = new LinterCore(asmHandler, checkManager, reportGenerator);

        try {
            linterCore.run(args);
        } catch (IOException e) {
            System.err.println("Error running linter: " + e.getMessage());
            System.exit(1);
        }
    }
}
