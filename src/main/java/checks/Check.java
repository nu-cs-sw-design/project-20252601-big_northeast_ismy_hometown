package checks;

import org.objectweb.asm.tree.ClassNode;

public interface Check {
    void run(ClassNode classNode);
}
