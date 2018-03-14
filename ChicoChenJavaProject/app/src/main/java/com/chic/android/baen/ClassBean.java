package com.chic.android.baen;

/**
 * Created by 陈国权 on 2018/3/14 0014
 */

public class ClassBean {

    private String name;
    private Class<?> className;

    public ClassBean(String name, Class<?> className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClassName() {
        return className;
    }

    public void setClassName(Class<?> className) {
        this.className = className;
    }
}
