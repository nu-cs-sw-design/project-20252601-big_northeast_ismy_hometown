package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class ClassNamingCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();
        String className = Type.getObjectType(classNode.name).getClassName();

        if (!Character.isUpperCase(className.charAt(0))) {
            warnings.add("Bad class name: " + className +
                    " (Should start with uppercase)");
        }
        
        return warnings;
    }
}
