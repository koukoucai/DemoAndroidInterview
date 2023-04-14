package com.koko.nocompose.designmodel.singleton;

import androidx.annotation.NonNull;

/**
 * 双重锁定模式
 * 主要volatile 和synchronized
 * Created by huanggang on 2022/11/4
 */
public class DCLSingleton implements Cloneable {

    private volatile static DCLSingleton singleton;

    private DCLSingleton() {
    }


    public static DCLSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DCLSingleton.class) {
                if (singleton == null) {
                    singleton = new DCLSingleton();
                }
            }
        }

        return singleton;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
        return object;
    }
}

