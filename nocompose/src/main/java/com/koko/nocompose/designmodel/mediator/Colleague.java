package com.koko.nocompose.designmodel.mediator;

/**
 * 同事类接口
 * Created by huanggang on 2022/11/9
 */
abstract class Colleague {
    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
