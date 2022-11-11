package com.greenline.myapplication4.activity

import com.koko.nocompose.designmodel.produce.*


/**
 * 生产者消费者模式
 * Created by huanggang on 2021/3/26
 */
class TestKotlin {
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
//            println("hello body")
//            test1()
//            test2()
            test3()
        }
        private fun test3(){
            val storageI  = StorageIII()
            test(storageI)
        }


        private fun test2(){
            val storageI  = StorageII()
            test(storageI)
        }

        private fun test1(){
            val storageI  = StorageI()
            test(storageI)
        }

        private fun test(storageImp: StorageImp){
            val pList = ArrayList<Producer>()
            for (i in 0..10){
                val p =
                    Producer(storageImp)
//                p.setNum(Random.nextInt(0,50))
                p.setNum((i+1)*10)
                pList.add(p)
            }
            val cList = ArrayList<Consumer>()
            for (i in 0 until 5){
                val c =
                    Consumer(storageImp)
//                c.setNum((0..60).random())
                c.setNum((i+1)*10)
                cList.add(c)
            }

            cList.forEach {
                it.start()
            }

            pList.forEach {
                it.start()
            }
        }
    }


}

fun main(args: Array<String>){
    println("hello body")
}