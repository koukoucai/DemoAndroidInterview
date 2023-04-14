package com.koko.nocompose.designmodel.iterator;

/**
 * 客户端
 * Created by huanggang on 2022/11/11
 */
public class Client {
    public static void main(String[] args) {
        String[] names = {"张三","李四","王五"};

        Aggregate aggregate = new ConcreteAggregate(names);

        Iterator iterator = aggregate.createIterator();

        iterator.first();

        while (!iterator.isDone()){
            Object o = iterator.currentItem();
            System.out.println("object "+o );
            iterator.next();
        }
    }
}
