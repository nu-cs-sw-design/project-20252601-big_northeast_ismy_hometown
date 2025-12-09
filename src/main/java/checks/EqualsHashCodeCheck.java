package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EqualsHashCodeCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();

        boolean hasEquals = false;
        boolean hasHashCode = false;

        List<MethodNode> methods = (List<MethodNode>) classNode.methods;

        for (MethodNode m : methods) {
            if (m.name.equals("equals") &&
                m.desc.equals("(Ljava/lang/Object;)Z")) {
                hasEquals = true;
            }

            if (m.name.equals("hashCode") &&
                m.desc.equals("()I")) {
                hasHashCode = true;
            }
        }

        if (hasEquals && !hasHashCode) {
            warnings.add("equals() is defined but hashCode() is missing.");
        }

        if (hasHashCode && !hasEquals) {
            warnings.add("hashCode() is defined but equals() is missing.");
        }
        
        return warnings;
    }
}
