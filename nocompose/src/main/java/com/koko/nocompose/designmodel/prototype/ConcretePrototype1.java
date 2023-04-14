package com.koko.nocompose.designmodel.prototype;

/**
 * Created by huanggang on 2022/11/8
 */
public class ConcretePrototype1 implements Prototype{
    private String nanme;
    @Override
    public Prototype clone() {
        ConcretePrototype1 concretePrototype1 = new ConcretePrototype1();
        concretePrototype1.nanme = this.getName();
        return concretePrototype1;
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
        return "ConcretePrototype1{" +
                "nanme='" + nanme + '\'' +
                '}';
    }

}
