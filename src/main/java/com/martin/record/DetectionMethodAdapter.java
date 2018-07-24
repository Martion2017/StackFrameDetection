package com.martin.record;

import com.martin.count.TimeCount;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class DetectionMethodAdapter extends AdviceAdapter {


    private static final String TIMECOUNT_PATH = Type.getInternalName(TimeCount.class);

    private String methodTagId;

    private int startTimeIndex;

    private MethodEntity method;

    public DetectionMethodAdapter(int access,
                                  String name,
                                  String desc,
                                  MethodVisitor mv,
                                  String innerClassName) {
        super(ASM5, mv, access, name, desc);
        this.method = new MethodEntity(innerClassName,name);
        this.methodTagId = method.getFullName();
    }
    private MethodTag getMethodTag(String innerClassName, String methodName) {
        int idx = innerClassName.replace('.', '/').lastIndexOf('/');
        String simpleClassName = innerClassName.substring(idx + 1, innerClassName.length());
        return MethodTag.getInstance(simpleClassName, methodName);
    }
    @Override
    protected void onMethodEnter() {
        if(checkParam(methodTagId)) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J", false);
            startTimeIndex = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, startTimeIndex);
        }
    }

    /**
     *  我们知道JAVA类型分为基本类型和引用类型，在JVM中对每一种类型都有与之相对应的类型描述，如下表：

     Java类型	JVM中的描述
     boolean	Z
     char	C
     byte	B
     short	S
     int	I
     float	F
     long	J
     double	D
     Object	Ljava/lang/Object;
     int	[I
     Object	[[Ljava/lang/Object;




     在Java的二进制文件中，方法的方法名和方法的描述都是存储在Constant pool中的，且在两个不同的单元里。因此，方法描述中不含有方法名，只含有参数类型和返回类型，如下：

     方法描述，在类中的	方法描述，在二进制文件中的
     void a(int i,float f)	(IF)V
     void a(Object o)	(Ljava/lang/Object;)V
     int a(int i,String s)	(ILjava/lang/String;)I
     int[] a(int[] i)	([I)[I
     String a()	()Ljava/lang/String;
     获取一个方法的描述可以使用org.objectweb.asm.Type.getMethodDescriptor方法
     * @param opcode
     */
    @Override
    protected void onMethodExit(int opcode) {
        if(checkParam(methodTagId)) {
            if ((IRETURN <= opcode && opcode <= RETURN) || opcode == ATHROW) {
                mv.visitVarInsn(LLOAD, startTimeIndex);
                mv.visitLdcInsn(methodTagId);
                mv.visitMethodInsn(INVOKESTATIC, TIMECOUNT_PATH, "methodTakesTime", "(JLjava/lang/String;)V", false);
            }
        }
    }

    protected boolean checkParam(String parma) {
        if(parma!=null&&!"".equals(parma)){
            return true;
        }
        return false;
    }

}
