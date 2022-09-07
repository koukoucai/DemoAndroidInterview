package com.koko.nocompose.produce;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通过LinkedBlockingDeque
 * Created by huanggang on 2021/3/27
 */
public class StorageIII implements StorageImp {
    public static final int MAX = 50;

    private LinkedBlockingDeque<Object> linkedBlockingDeque = new LinkedBlockingDeque<>();



    @Override
    public void produce(int num) {
        if (linkedBlockingDeque.size() >= MAX){
            System.out.println("[要生产数量 ] " + num +" [库存量] "+linkedBlockingDeque.size() +" 暂时不能执行任务");
        }

        for (int i = 1; i <= num ; ++i) {
            try {
                linkedBlockingDeque.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产完当前库存 = " + linkedBlockingDeque.size());
    }

    @Override
    public void consume(int num) {
        if (linkedBlockingDeque.size() == 0){
            System.out.println("[要消费数量 ] " + num +" [库存量] "+linkedBlockingDeque.size() +" 暂时不能执行任务");
        }
        for (int i = 1; i <= num ; ++i) {
            try {
                linkedBlockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费完剩余库存 = " + linkedBlockingDeque.size());
    }
}
