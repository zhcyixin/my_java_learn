package com.zhc.javabase.designpattern.di.second;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String id;
    private String className;
    private List constructorArgs = new ArrayList<>();
    private Scope scope = Scope.SINGLETON;
    private boolean lazyInit = false; // 省略必要的getter/setter/constructors

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }
    public static enum Scope {
        SINGLETON, PROTOTYPE
    }
    public static class ConstructorArg {
        private Boolean isRef;
        private Class type;
        private Object arg; // 省略必要的getter/setter/constructors

        public Boolean getIsRef() {
            return isRef;
        }

        public void setIsRef(Boolean ref) {
            isRef = ref;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public Object getArg() {
            return arg;
        }

        public void setArg(Object arg) {
            this.arg = arg;
        }
    }
}
