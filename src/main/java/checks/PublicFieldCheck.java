package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class PublicFieldCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();
        List<FieldNode> fields = (List<FieldNode>) classNode.fields;

        for (FieldNode field : fields) {
            boolean isPublic = (field.access & Opcodes.ACC_PUBLIC) != 0;
            boolean isFinal = (field.access & Opcodes.ACC_FINAL) != 0;
            if (isPublic && !isFinal) {
                warnings.add("Public non-final field: " + field.name +
                        " (maybe make it private with getter/setter for encapsulation)");
            }
        }
        
        return warnings;
    }
}



