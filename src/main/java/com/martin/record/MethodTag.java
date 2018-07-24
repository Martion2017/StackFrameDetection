package com.martin.record;

/**
 */
public class MethodTag {

    private String className;

    private String methodName;

    private MethodTag(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getSimpleDesc() {
        return className + "." + methodName;
    }

    @Override
    public String toString() {
        return "MethodTag{" +
                "methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public static MethodTag getInstance(String className, String methodName) {
        return new MethodTag(className, methodName);
    }
}
