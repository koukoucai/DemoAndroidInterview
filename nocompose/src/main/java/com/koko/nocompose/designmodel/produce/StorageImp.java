package com.koko.nocompose.designmodel.produce;

/**
 * 仓库接口
 * Created by huanggang on 2021/3/27
 */
public interface StorageImp  {
    void produce(int num);
    void consume(int num);

}
