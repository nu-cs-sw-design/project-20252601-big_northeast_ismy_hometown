package checks;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class FieldNamingCheck implements Check {

    @Override
    public void run(ClassNode classNode) {
        List<FieldNode> fields = (List<FieldNode>) classNode.fields;

        for (FieldNode f : fields) {
            if (f.name.length() > 0 &&
                !Character.isLowerCase(f.name.charAt(0))) {

                System.out.println("[Lint] Bad field name: " + f.name +
                        " (Should start with lowercase)");
            }
        }
    }
}
