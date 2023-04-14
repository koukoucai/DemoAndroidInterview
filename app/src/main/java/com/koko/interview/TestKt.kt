package com.koko.interview

import android.util.Log
import com.koko.kokoannotation.KokoAnnotationJava
import kotlin.reflect.KProperty

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
            var a = 1
            var b = 2
            a = b.also {
                println("main:b=  $b "  )
                println("main:it =  $it "  )
                b = a
                println("main: b=  $b "  )
                println("main:it =  $it "  )
            }

            println("main: a =$a  b = $b "  )


        }
    }
}

/**
 * 仿 lazy 高阶函数
 */
class Later<T> (val block:() ->T){
   var value:Any? = null
    operator fun getValue(any: Any?,pro:KProperty<*>):T{
        if (value == null){
            value = block()
        }
        return value as T
    }
}
fun <T>later(block: () -> T) = Later(block)


fun main(){
    println("fuck???")

    funA(scope = {
      funB(scope = {
          it.visitA()
          visitB()
      })

        it.expandAScopefun()
        it.abs
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

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var i:Int = 0
        var j:Int = 0;
        for((index1,num) in nums.withIndex()){
          for (index2 in index1+1..nums.size-1){
              if ((num + nums[index2]) == target){
                  i = index1
                  j = index2
                  return intArrayOf(i,j)
              }
          }
        }
        return intArrayOf(i,j)
    }

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


fun AScope.expandAScopefun(){
    println("i am scope")
}

var AScope.abs:String
    get() {
//        TODO()
       return  "i am TODO "
    }
    set(value) {}
//    get() = abs+"i am "
//    set(value) {abs = value}