package checks;

import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class PublicFieldCheck implements Check {

    @Override
    public void run(ClassNode classNode) {
        List<FieldNode> fields = (List<FieldNode>) classNode.fields;

        for (FieldNode field : fields) {
            boolean isPublic = (field.access & Opcodes.ACC_PUBLIC) != 0;
            boolean isFinal = (field.access & Opcodes.ACC_FINAL) != 0;
            boolean isStatic = (field.access & Opcodes.ACC_STATIC) != 0;
            if (isPublic && !isFinal) {
                System.out.println("[Lint] Public non-final field: " + field.name +
                        " (maybe make it private with getter/setter for encapsulation)");
            }
        }
    }
}


