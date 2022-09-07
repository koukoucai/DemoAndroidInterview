package com.koko.interview

import android.graphics.Region
import com.koko.example.HelloKoko
import com.koko.kokoannotation.KokoAnnotationJava

/**
 *
 * Created by huanggang on 2022/9/5
 */
@KokoAnnotationJava
class TestKt {
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            println("hello????")
            HelloKoko.main(listOf("hahaha").toTypedArray())
            HelloKoko.main(arrayOf("hahaha"))
        }
    }
}

fun main(){
    println("fuck???")
}