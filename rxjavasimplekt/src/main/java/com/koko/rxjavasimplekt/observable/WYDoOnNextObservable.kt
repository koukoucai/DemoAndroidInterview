package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver


/**
 * doonnext  多次下游
 * Created by huanggang on 2021/12/10
 */
class WYDoOnNextObservable<T>(
    private val source:WYObservable<T>,
    private val func: (T) -> Unit
) :WYObservable<T>{
    override fun subscribe(observer: WYObserver<T>) {
        println("WYDoOnNextObservable 已经是doOnNext的形状了")
        val doOnNext = WYDoOnNextObserver(observer,func)
        source.subscribe(doOnNext)
    }
}


class WYDoOnNextObserver<T>(
    private val downStream:WYObserver<T>,
    private val func:(T)->Unit
):WYObserver<T>{
    override fun onSubscribe() {
        downStream.onSubscribe()
    }

    override fun onNext(item: T) {
       func.invoke(item)
        downStream.onNext(item)
    }

    override fun onError(e: Throwable) {
        downStream.onError(e)
    }

    override fun onComplete() {
        downStream.onComplete()
    }

}