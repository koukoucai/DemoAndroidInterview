package com.koko.nocompose.designmodel.adapter;

/**
 * 适配器
 * Created by huanggang on 2022/11/5
 */
class TargetImpl implements Target{
    private Adaptee adaptee;

    public TargetImpl(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
