package com.koko.nocompose.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by huanggang on 2022/11/9
 */
class ProxyByJava implements InvocationHandler {
    private RealSubject realSubject;

    public ProxyByJava(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    public Subject getDynamicSubject() throws Exception {
        if (realSubject == null){
            throw new Exception("绑定对象为空");
        }

        return (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("set")){
            throw  new Throwable("没有这个方法");
        }

        return method.invoke(realSubject, args);
    }

}
