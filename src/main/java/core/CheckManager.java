package core;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;

import checks.Check;

public class CheckManager {
    private final List<Check> checks;

    public CheckManager() {
        this.checks = new ArrayList<>();
    }

    public void addCheck(Check check) {
        checks.add(check);
    }

    public List<String> runAll(ClassNode classNode) {
        List<String> allWarnings = new ArrayList<>();
        
        for (Check check : checks) {
            List<String> warnings = check.run(classNode);
            allWarnings.addAll(warnings);
        }
        
        return allWarnings;
    }
}

