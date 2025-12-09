package checks;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class FieldNamingCheck implements Check {

    @Override
    public List<String> run(ClassNode classNode) {
        List<String> warnings = new ArrayList<>();
        List<FieldNode> fields = (List<FieldNode>) classNode.fields;

        for (FieldNode f : fields) {
            if (f.name.length() > 0 &&
                !Character.isLowerCase(f.name.charAt(0))) {

                warnings.add("Bad field name: " + f.name +
                        " (Should start with lowercase)");
            }
        }
        
        return warnings;
    }
}

