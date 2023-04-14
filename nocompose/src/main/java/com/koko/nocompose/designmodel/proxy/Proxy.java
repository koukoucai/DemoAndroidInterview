package com.koko.nocompose.designmodel.proxy;

/**
 * 静态代理  代理对象
 * Created by huanggang on 2022/11/9
 */
class Proxy implements Subject{
    private  RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {

        //do otherthing

        //do right thing
        if (realSubject != null){
            realSubject.request();
        }


        //do otherthing
    }
}
