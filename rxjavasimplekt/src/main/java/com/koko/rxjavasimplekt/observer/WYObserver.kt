package com.koko.rxjavasimplekt.observer

/**
 * 观察者
 * Created by huanggang on 2021/12/9
 */
interface WYObserver<T> {
    fun onSubscribe()
    fun onNext(item:T)
    fun onError(e:Throwable)
    fun onComplete()

}