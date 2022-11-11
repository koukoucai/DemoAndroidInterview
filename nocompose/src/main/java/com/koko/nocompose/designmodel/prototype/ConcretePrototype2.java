package com.koko.nocompose.designmodel.prototype;

/**
 * Created by huanggang on 2022/11/8
 */
public class ConcretePrototype2 implements Prototype{
    private String nanme;
    @Override
    public Prototype clone() {
        ConcretePrototype2 concretePrototype2 = new ConcretePrototype2();
        concretePrototype2.nanme = this.getName();
        return concretePrototype2;
    }

    @Override
    public String getName() {
        return nanme;
    }

    @Override
    public void setName(String name) {
        this.nanme = name;
    }

    @Override
    public String toString() {
        return "concretePrototype2{" +
                "nanme='" + nanme + '\'' +
                '}';
    }

}
