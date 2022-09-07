package com.koko.nocompose.produce;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过 await 和signal
 * Created by huanggang on 2021/3/27
 */
public class StorageII implements StorageImp {
    public static final int MAX = 100;
    private LinkedList<Object> linkedList = new LinkedList<>();


    //锁
    private final Lock lock = new ReentrantLock();

    private final Condition full = lock.newCondition();

    private final Condition empty = lock.newCondition();

    @Override
    public void produce(int num){
        lock.lock();

        while(linkedList.size()+num >MAX){
            System.out.println("[要生产数量 ] " + num +" [库存量] "+linkedList.size() +" 暂时不能执行任务");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= num ; ++i) {
//                System.out.println("i = " + i);
            linkedList.add(new Object());
        }

        System.out.println("[已经生产数量 ]  " + num+"  [现库存量] "+linkedList.size()  );

        full.signalAll();
        empty.signalAll();

        lock.unlock();
    }
    @Override
    public void consume(int num){
        lock.lock();

        while (linkedList.size()<num){
            System.out.println("[要消费数量 ] " + num +" [库存量] "+linkedList.size() +" 暂时不能执行任务");

            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= num; ++i) {
            linkedList.remove();
        }


        full.signalAll();
        empty.signalAll();
        lock.unlock();
    }
}
