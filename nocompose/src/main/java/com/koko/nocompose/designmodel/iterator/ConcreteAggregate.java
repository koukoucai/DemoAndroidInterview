package com.koko.nocompose.designmodel.iterator;

/**
 * 具体聚合对象
 * Created by huanggang on 2022/11/11
 */
class ConcreteAggregate extends Aggregate{
    private String[] data = null;

    public ConcreteAggregate(String[] data) {
        this.data = data;
    }

    public int size(){
        return data.length;
    }

    public Object get(int index){
        if (index < data.length){
            return data[index];
        }
        return null;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
}
