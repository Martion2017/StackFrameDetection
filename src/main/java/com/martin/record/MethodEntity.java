package com.martin.record;

import java.io.Serializable;

public class MethodEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    public MethodEntity(String innerClassName, String methodName) {
        int idx = innerClassName.replace('.', '/').lastIndexOf('/');
        String simpleClassName = innerClassName.substring(idx + 1, innerClassName.length());
        this.className = simpleClassName;
        this.methodName = methodName;
    }

    private String className;

    private String methodName;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getFullName(){
        return this.className+"."+this.methodName;
    }

    @Override
    public String toString() {
        return "Method{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
