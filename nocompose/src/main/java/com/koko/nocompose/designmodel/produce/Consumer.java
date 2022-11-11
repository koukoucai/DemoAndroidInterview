package com.koko.nocompose.designmodel.produce;

/**
 * 消费者
 * Created by huanggang on 2021/3/26
 */
public class Consumer extends Thread{

    int num;
    StorageImp storageI;

    public Consumer(StorageImp storageI) {
        this.storageI = storageI;
    }

    @Override
    public void run() {
        super.run();
        consume(num);
    }

    void consume(int num){
        storageI.consume(num);
    }

    public int getNum() {
        return num;
    }

    public StorageImp getStorageI() {
        return storageI;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setStorageI(StorageI storageI) {
        this.storageI = storageI;
    }
}
