import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bous on 12.12.17.
 */
public class MyMethodVisitor extends AdviceAdapter implements Opcodes{
    Logger logger;
    protected MyMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc){
        super(api, mv, access, name, desc);
        logger = Logger.getLogger("MethodVisitorLogger");
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
        logger.log(Level.ALL, "Teste den Logger mit opcode {0}", opcode);
        System.out.println("owner: " + owner);
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("CALL " + name);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        super.visitMethodInsn(opcode, owner, name, desc,itf);
    }
}
