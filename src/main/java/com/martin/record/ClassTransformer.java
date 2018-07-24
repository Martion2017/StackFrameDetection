package com.martin.record;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            return getBytes(loader, className, classfileBuffer);
        } catch (Throwable e) {
           System.out.println(loader + ", " + className  + ", " + protectionDomain +"Error");
        }
        return classfileBuffer;
    }

    private byte[] getBytes(ClassLoader loader,
                            String className,
                            byte[] classFileBuffer) {
        if (loader != null && loader.getClass().getName().equals("org.apache.catalina.loader.WebappClassLoader")) {
            ClassReader cr = new ClassReader(classFileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            if(className.startsWith("java")||className.startsWith("javax")||className.startsWith("org")||className.startsWith("sun")||className.startsWith("com/martin")){
                cr.accept(cw, ClassReader.EXPAND_FRAMES);
                return cw.toByteArray();
            }else{
                DetectionClassAdapter cv = new DetectionClassAdapter(cw, className);
                cr.accept(cv, ClassReader.EXPAND_FRAMES);
                return cw.toByteArray();

            }



        } else {
            ClassReader cr = new ClassReader(classFileBuffer);//COMPUTE_FRAMES
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            //DetectionClassAdapter cv = new DetectionClassAdapter(cw, className);
            cr.accept(cw, ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
        }
    }
}
