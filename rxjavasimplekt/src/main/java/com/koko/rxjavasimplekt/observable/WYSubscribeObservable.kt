package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver
import com.koko.rxjavasimplekt.schedulers.Schedulers

/**
 * 切换线程 上游  虚假的被观察者
 * Created by huanggang on 2021/12/9
 */
class WYSubscribeObservable<T>(
    private val source: WYObservable<T>,
    private val thread:Int
):WYObservable<T> {
    override fun subscribe(observer: WYObserver<T>) {
        println("WYSubscribeObservable 已经是subscribeOn的形状了")
        val subscribe = WYSubscribeObserver(observer)
        Schedulers.INSTANCE.submitSubscribeWork(source,subscribe,thread)
    }
}

class WYSubscribeObserver<T>(private val downStream:WYObserver<T>):WYObserver<T>{
    override fun onSubscribe() {
        downStream.onSubscribe()
    }

    override fun onNext(item: T) {
       downStream.onNext(item)
    }

    override fun onError(e: Throwable) {
        downStream.onError(e)
    }

    override fun onComplete() {
        downStream.onComplete()
    }

}


