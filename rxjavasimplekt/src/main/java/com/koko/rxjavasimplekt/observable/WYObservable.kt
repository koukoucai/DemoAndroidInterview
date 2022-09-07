package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver

/**
 * 被观察者
 * Created by huanggang on 2021/12/9
 */
interface WYObservable<T> {
    fun subscribe(observer: WYObserver<T>)
}