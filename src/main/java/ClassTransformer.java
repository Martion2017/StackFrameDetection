import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            System.out.println("transform(" + loader + ", " + className + ", classBeingRedefined, protectionDomain, " + classfileBuffer.length + ")...");

            return getBytes(loader, className, classfileBuffer);
        } catch (Throwable e) {
           System.out.println("Transformer.transform(" + loader + ", " + className + ", " + classBeingRedefined + ", " + protectionDomain + ", " + classfileBuffer.length + ")"+"Error");
        }
        return classfileBuffer;
    }

    private byte[] getBytes(ClassLoader loader,
                            String className,
                            byte[] classFileBuffer) {
        if (loader != null && loader.getClass().getName().equals("org.apache.catalina.loader.WebappClassLoader")) {
            ClassReader cr = new ClassReader(classFileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            DetectionClassAdapter cv = new DetectionClassAdapter(cw, className);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        } else {
            ClassReader cr = new ClassReader(classFileBuffer);//COMPUTE_FRAMES
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            DetectionClassAdapter cv = new DetectionClassAdapter(cw, className);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        }
    }
}
