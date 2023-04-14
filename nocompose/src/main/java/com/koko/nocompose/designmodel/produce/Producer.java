package com.koko.nocompose.designmodel.produce;

/**
 * 生产者
 * Created by huanggang on 2021/3/26
 */
public class Producer extends Thread{
    int num;
    StorageImp storageI;
    public Producer(StorageImp storageI){
        this.storageI = storageI;
    }

    void produce(int num){
        storageI.produce(num);
    }

    @Override
    public void run() {
        super.run();
        produce(num);
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
