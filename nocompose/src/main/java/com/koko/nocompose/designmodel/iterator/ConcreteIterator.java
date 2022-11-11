package com.koko.nocompose.designmodel.iterator;

/**
 * Created by huanggang on 2022/11/11
 */
class ConcreteIterator implements Iterator{
    private int index = -1;
    private ConcreteAggregate concreteAggregate;

    public ConcreteIterator(ConcreteAggregate concreteAggregate) {
        this.concreteAggregate = concreteAggregate;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
      if (index < this.concreteAggregate.size()){
          index=  index+1;
      }
    }

    @Override
    public boolean isDone() {
        return index == this.concreteAggregate.size();
    }

    @Override
    public Object currentItem() {
        return this.concreteAggregate.get(index);
    }
}
