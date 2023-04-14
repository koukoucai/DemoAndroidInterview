package com.koko.rxjavasimplekt.observable

import com.koko.rxjavasimplekt.observer.WYObserver


/**
 * 实际使用被观察者
 * Created by huanggang on 2021/12/9
 */
class WYRealObservable<T> constructor() {

    private var source:WYObservable<T>?=null
    private var source2:((WYObserver<T>)->Unit)?=null
    constructor(wyObservable: WYObservable<T>):this(){
        this.source = wyObservable
    }
    constructor(source2:(WYObserver<T>)->Unit):this(){
        this.source2 = source2
    }

    fun subscribe(wyObserver: WYObserver<T>){
        wyObserver.onSubscribe()
        source?.subscribe(wyObserver)
        source2?.invoke(wyObserver)
        println("WYRealObservable 我要看看 source的类型   " +
                "是 real   ${source is WYRealObservable<*>}  " +
                "是 map   ${source is WYMapObservable<*,*>}   " +
                "是 subscribe ${source is WYSubscribeObservable<*>}  " +
                "是 observer ${source is WYObserverObservable}" +
                "是 doonnext ${source is WYDoOnNextObservable}" +
                "是 filter ${source is WYFilterObservable}"
        )
    }


    fun <R>map(func:(T)->R):WYRealObservable<R>{
        val map = WYMapObservable(source!!,func)
        //重新构造虚假被观察者  然后调用上面的subscribe 方法
        return WYRealObservable(map)
    }

    fun subscribeOn(thread:Int):WYRealObservable<T>{
        val sub = WYSubscribeObservable(source!!,thread)
        //重新构造虚假被观察者    然后调用上面的subscribe 方法
        return WYRealObservable(sub)
    }

    fun observerOn(thread:Int):WYRealObservable<T>{
        val ob = WYObserverObservable(source!!,thread)
        //重新构造虚假被观察者    然后调用上面的observerOn 方法
        return WYRealObservable(ob)
    }

    fun filter(func:(T)->Boolean):WYRealObservable<T>{
        val f = WYFilterObservable(source!!,func)
        //重新构造虚假被观察者    然后调用上面的filter方法
        return WYRealObservable(f)
    }

    fun doOnNext(func: (T) -> Unit):WYRealObservable<T>{
        val doOn = WYDoOnNextObservable(source!!,func)
        //重新构造虚假被观察者    然后调用上面的doOnNext 方法
        return WYRealObservable(doOn)
    }

    companion object{
        fun <T>create(wyObservable: WYObservable<T>):WYRealObservable<T>{
            return WYRealObservable(wyObservable = wyObservable)
        }

        fun <T>create(source:(WYObserver<T>)->Unit):WYRealObservable<T>{
            return WYRealObservable(source)
        }

        fun <T>just(item:T):WYRealObservable<T>{
            return create {
                it.onNext(item)
                it.onComplete()
            }
        }
    }



}