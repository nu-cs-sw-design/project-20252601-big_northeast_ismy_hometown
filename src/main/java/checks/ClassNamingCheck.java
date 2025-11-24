package checks;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class ClassNamingCheck implements Check {

    @Override
    public void run(ClassNode classNode) {
        String className = Type.getObjectType(classNode.name).getClassName();

        if (!Character.isUpperCase(className.charAt(0))) {
            System.out.println("[Lint] Bad class name: " + className +
                    " (Should start with uppercase)");
        }
    }
}
