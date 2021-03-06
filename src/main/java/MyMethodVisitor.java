//Philipp Bous - 912590

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.sql.Time;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyMethodVisitor extends AdviceAdapter implements Opcodes{
    String name;
    Logger logger;
    long time;

    protected MyMethodVisitor(int api, MethodVisitor mv, int access, String name, String desc){
        super(api, mv, access, name, desc);
        this.name = name;
        logger = Logger.getLogger("MethodVisitorLogger");
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
        //logger.log(Level.INFO, "Besuche " + owner +"."+name, opcode);
        /* mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("CALL " + name);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);*/
        super.visitMethodInsn(opcode, owner, name, desc,itf);
    }

    @Override
    protected void onMethodEnter(){
        time = System.nanoTime();
    }

    @Override
    protected void onMethodExit(int opcode){
        Statistics.visitedMethod(name, System.nanoTime() - time);
        System.out.println("visited " + name +" which took: " + (System.nanoTime() - time) + " nanoseconds");
    }
}
