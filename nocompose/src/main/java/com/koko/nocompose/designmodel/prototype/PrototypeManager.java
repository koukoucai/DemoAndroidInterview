package com.koko.nocompose.designmodel.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型管理器
 * Created by huanggang on 2022/11/8
 */
class PrototypeManager {
    private PrototypeManager(){

    }

    private static Map<String,Prototype> map = new HashMap<>();

    public synchronized static void setPrototype(Prototype prototype){
        map.put(prototype.getName(), prototype);
    }

    public synchronized static void removePrototype(String name){
        map.remove(name);
    }

    public synchronized static Prototype getPrototype(String name) throws Exception {
        Prototype prototype = map.get(name);
        if (prototype == null){
            throw  new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }
}
