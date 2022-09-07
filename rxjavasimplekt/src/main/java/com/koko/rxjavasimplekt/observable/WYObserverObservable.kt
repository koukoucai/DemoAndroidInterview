package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver
import com.koko.rxjavasimplekt.schedulers.Schedulers

/**
 *  下游，切换线程 虚假的观察者
 * Created by huanggang on 2021/12/10
 */
class WYObserverObservable<T>(
    private val source :WYObservable<T>,
    private val thread:Int
):WYObservable<T> {
    override fun subscribe(observer: WYObserver<T>) {
        println("WYObserverObservable 已经是observerOn的形状了")
        val ob = WYObserverObserver(observer,thread)
        source.subscribe(ob)
    }

}

class WYObserverObserver<T>(
    val downStream:WYObserver<T>,
    val thread:Int
):WYObserver<T>{
    override fun onSubscribe() {
        Schedulers.INSTANCE.submitObserverWork({
            downStream.onSubscribe()
        },thread)
    }

    override fun onNext(item: T) {
        Schedulers.INSTANCE.submitObserverWork({
            downStream.onNext(item)
        },thread)
    }

    override fun onError(e: Throwable) {
        Schedulers.INSTANCE.submitObserverWork({
            downStream.onError(e)
        },thread)
    }

    override fun onComplete() {
        Schedulers.INSTANCE.submitObserverWork({
            downStream.onComplete()
        },thread)
    }

}