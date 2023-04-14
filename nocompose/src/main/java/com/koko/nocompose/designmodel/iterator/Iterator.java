package com.koko.nocompose.designmodel.iterator;

/**
 * 迭代器模式 迭代接口
 * Created by huanggang on 2022/11/11
 */
interface Iterator {
    /**
     * 移动到第一位
     */
    public void first();

    /**
     * 下一位
     */
    public void next();

    /**
     * 遍历完成
     */
    public boolean isDone();

    /**
     * 获取迭代的当前对象
     * @return
     */
    public Object currentItem();
}
