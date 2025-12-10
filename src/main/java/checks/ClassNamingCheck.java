package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class ClassNamingCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();
        String fullClassName = Type.getObjectType(classNode.name).getClassName();
        String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        if (simpleClassName.length() > 0 && !Character.isUpperCase(simpleClassName.charAt(0))) {
            warnings.add("Bad class name: " + fullClassName +
                    " (Should start with uppercase)");
        }
        
        return warnings;
    }
}
