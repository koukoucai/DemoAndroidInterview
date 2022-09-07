package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver


/**
 * 过滤 虚假的观察者
 * Created by huanggang on 2021/12/10
 */
class WYFilterObservable<T>(
    private val source:WYObservable<T>,
    private val func:(T)->Boolean
):WYObservable<T> {
    override fun subscribe(observer: WYObserver<T>) {
        println("WYFilterObservable 已经是filter的形状了")
        val f = WYFilterObserver(observer,func)
        source.subscribe(f)
    }
}


class WYFilterObserver<T>(
    private val downStream:WYObserver<T>,
    private val func:(T)->Boolean
):WYObserver<T>{
    override fun onSubscribe() {
        downStream.onSubscribe()
    }

    override fun onNext(item: T) {
       val result = func.invoke(item)
        println("WYFilterObservable  执行条件 $result")
        if (result){
            downStream.onNext(item)
        }
    }

    override fun onError(e: Throwable) {
        downStream.onError(e)
    }

    override fun onComplete() {
        downStream.onComplete()
    }

}