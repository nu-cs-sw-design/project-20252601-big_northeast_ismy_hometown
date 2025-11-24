package checks;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EqualsHashCodeCheck implements Check {

    @Override
    public void run(ClassNode classNode) {

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
            System.out.println("[Lint] equals() is defined but hashCode() is missing.");
        }

        if (hasHashCode && !hasEquals) {
            System.out.println("[Lint] hashCode() is defined but equals() is missing.");
        }
    }
}
