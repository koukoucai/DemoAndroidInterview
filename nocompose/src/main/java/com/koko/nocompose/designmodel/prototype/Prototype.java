package com.koko.nocompose.designmodel.prototype;

/**
 * 原型模式
 * Created by huanggang on 2022/11/8
 */
public interface Prototype {
    public Prototype clone();
    public String getName();
    public void setName(String name);
}
