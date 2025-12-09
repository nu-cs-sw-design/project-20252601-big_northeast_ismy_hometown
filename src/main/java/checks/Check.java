package checks;

import java.util.List;

import org.objectweb.asm.tree.ClassNode;

public interface Check {
    List<String> run(ClassNode classNode);
}
