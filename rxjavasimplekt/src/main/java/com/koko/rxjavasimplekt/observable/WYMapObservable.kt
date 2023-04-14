package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver

/**
 * 模拟rxjava map 操作符
 * Created by huanggang on 2021/12/9
 */
class WYMapObservable<T,R>(
    private val source:WYObservable<T>,
    private val func:(T)->R
):WYObservable<R> {
    override fun subscribe(observer: WYObserver<R>) {
        println("WYMapObservable 已经是map的形状了")
        val map = WYMapObserver(observer,func)
        source.subscribe(map)
    }

    init {
        println("WYMapObservable 悄悄改变成map 惊艳所有人")
    }
}

class WYMapObserver<T,R>(
    private val downStream:WYObserver<R>,
    private val func: (T) -> R
):WYObserver<T>{
    override fun onSubscribe() {
        downStream.onSubscribe()
    }

    override fun onNext(item: T) {
        println("WYMapObserver 调教前准备。。。。")
        val result = func(item)
        downStream.onNext(result)
    }

    override fun onError(e: Throwable) {
        downStream.onError(e)
    }

    override fun onComplete() {
        downStream.onComplete()
    }

}