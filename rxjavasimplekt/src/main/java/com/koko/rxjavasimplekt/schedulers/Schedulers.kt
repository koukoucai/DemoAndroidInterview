package com.koko.rxjavasimplekt.schedulers

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.koko.rxjavasimplekt.observable.WYObservable
import com.koko.rxjavasimplekt.observer.WYObserver
import java.util.concurrent.Executors

/**
 * 线程池
 * Created by huanggang on 2021/12/9
 */
class Schedulers {
    private var IOThreadPool = Executors.newFixedThreadPool(100)

    private var handler = Handler(Looper.getMainLooper()){
        it.callback.run()
        return@Handler true
    }

    companion object{
        val INSTANCE : Schedulers by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Schedulers()
        }
        private val IO = 0 //io线程
        private val MAIN = 1//主线程
        fun IO()= IO
        fun  MAIN() = MAIN
    }

    fun <T>submitSubscribeWork(source: WYObservable<T>, downStream: WYObserver<T>, thread:Int){
        when(thread){
            IO ->{
                IOThreadPool.submit{
                    source.subscribe(downStream)
                }
            }
            MAIN->{
                handler.let {
                    val message = Message.obtain(it){
                        source.subscribe(downStream)
                    }
                    it.sendMessage(message)
                }
            }
        }
    }


    fun submitObserverWork(func:()->Unit,thread:Int){
        when(thread){
            IO->{
                IOThreadPool?.submit {
                    func.invoke()
                }
            }
            MAIN->{
                handler.let {
                    val message = Message.obtain(it){
                        func.invoke()
                    }
                    it.sendMessage(message)
                }
            }
        }
    }
}