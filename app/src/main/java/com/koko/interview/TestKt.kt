package com.koko.interview

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
//            HelloKoko.main(listOf("hahaha").toTypedArray())
        }
    }
}

fun main(){
    println("fuck???")

    funA(scope = {
      funB(scope = {
          it.visitA()
          visitB()
      })
    } )

    funA {
        funB {
            it.visitA()
            visitB()
        }
    }

//    [A, B, aa, bb, 123, abc]
//    [A, B, 0, aa, bb, 我是, 123, abc, 我是大傻逼]
    val list = listOf("A","123","aa","B","abc","bb","我是大傻逼","我是","0")
    val lengthThenCaseInsensitive = compareBy<String>(selector ={it.length} ).then(String.CASE_INSENSITIVE_ORDER)
//    val lengthThenCaseInsensitive = compareBy<String>(selector ={it.length} )

    val sorted = list.sortedWith(lengthThenCaseInsensitive)
    println(sorted)

}

class AScope{
    fun visitA(){}
}

class BScope{
    fun visitB(){}
//    fun visitB(b:BScope){}
}

fun funA(scope:(AScope)->Unit){
    scope(AScope())
}

fun funB(scope: BScope.() -> Unit){
    scope(BScope())
}




