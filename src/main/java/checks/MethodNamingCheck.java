package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodNamingCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();
        List<MethodNode> methods = (List<MethodNode>) classNode.methods;

        for (MethodNode m : methods) {
            if (m.name.startsWith("<")) continue;

            if (!Character.isLowerCase(m.name.charAt(0))) {
                warnings.add("Bad method name: " + m.name +
                        " (Should start with lowercase)");
            }
        }
        
        return warnings;
    }
}

