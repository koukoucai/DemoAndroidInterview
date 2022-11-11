package com.koko.nocompose.designmodel.produce;

import java.util.LinkedList;

/**
 * 单纯通过 wait 和notifyall 来解决
 * Created by huanggang on 2021/3/26
 */
public class StorageI implements StorageImp {
    final int MAX_SIZE = 150;
    private final LinkedList<Object> linkedList = new LinkedList<>();

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public LinkedList<Object> getLinkedList() {
        return linkedList;
    }


    //生产
    @Override
    public void produce(int num){
        synchronized (linkedList){
            while (linkedList.size() + num > MAX_SIZE){
                System.out.println("[要生产数量 ] " + num +" [库存量] "+linkedList.size() +" 暂时不能执行任务");
                try {
                    //阻塞
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }

            for (int i = 1; i <= num ; ++i) {
//                System.out.println("i = " + i);
                linkedList.add(new Object());
            }


            System.out.println("[已经生产数量 ]  " + num+"  [现库存量] "+linkedList.size()  );

            linkedList.notifyAll();
        }
    }

    //消费
    @Override
    public void consume(int num){
        synchronized (linkedList){
            while (linkedList.size() < num){
                System.out.println("[要消费数量 ] " + num +" [库存量] "+linkedList.size() +" 暂时不能执行任务");

                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i <= num ; ++i) {
//                System.out.println("i = " + i);
                linkedList.remove();
            }

            System.out.println("[已经消费数量 ]  " + num+"  [现库存量] "+linkedList.size()  );

            linkedList.notifyAll();
        }
    }



}
