package checks;

import java.util.List;

import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.MethodInsnNode;

public class ProgramToInterfaceCheck implements Check {

    @Override
    public void run(ClassNode classNode) {
        List<MethodNode> methods = (List<MethodNode>) classNode.methods;

        for (MethodNode m : methods) {

            InsnList insns = m.instructions;

            for (int i = 0; i < insns.size(); i++) {

                AbstractInsnNode insn = insns.get(i);

                if (insn instanceof MethodInsnNode) {
                    MethodInsnNode call = (MethodInsnNode) insn;

                    if (!call.name.equals("<init>")) continue;

                    String type = call.owner.replace("/", ".");

                    if (type.contains("ArrayList")) {
                        System.out.println("[Lint] Warning: Instantiates ArrayList in " + m.name +
                                " — program to interface (List) instead.");
                    }

                    if (type.contains("HashMap")) {
                        System.out.println("[Lint] Warning: Instantiates HashMap in " + m.name +
                                " — program to interface (Map) instead.");
                    }
                }
            }
        }
    }
}
