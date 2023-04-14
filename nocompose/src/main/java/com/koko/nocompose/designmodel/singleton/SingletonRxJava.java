package com.koko.nocompose.designmodel.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * rxjava 源码实现的单例
 * Created by huanggang on 2022/11/4
 */
public class SingletonRxJava {
    private SingletonRxJava(){};
    private static AtomicReference<SingletonRxJava> INSTANCE = new AtomicReference<>();

    public static SingletonRxJava getInstance() {
        for(;;){
            SingletonRxJava singletonRxJava = INSTANCE.get();
            if (singletonRxJava != null){
                return singletonRxJava;
            }
            singletonRxJava = new SingletonRxJava();
            //借助 AtomicReference 原子性操作 通过volatile 和Unsafe 进行CAS
            if (INSTANCE.compareAndSet(null, singletonRxJava)){
                return singletonRxJava;
            }
        }
    }
}
