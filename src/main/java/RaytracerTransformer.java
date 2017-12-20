import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by bous on 12.12.17.
 */
public class RaytracerTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if ("SDRaytracer".equals(className)) {
            try {
                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                ProfilingClassVisitor profilingClassVisitor = new ProfilingClassVisitor(classWriter);
                classReader.accept(profilingClassVisitor, ClassReader.EXPAND_FRAMES);
                return classWriter.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
                return classfileBuffer;
            }
        }
        return classfileBuffer;
    }
}

//Filter auf klasse raytracer setzen; hashmap wo aufgerufene methoden drin sind; try finally; nanotime; benchmarkprogramm;invokevirtual???