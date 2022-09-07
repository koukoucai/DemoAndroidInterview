package com.koko.interview.java;

import com.koko.example.HelloKoko;
import com.koko.kokoannotation.KokoAnnotationJava;

/**
 * 测试用
 * Created by huanggang on 2022/9/5
 */
@KokoAnnotationJava
class Test {
    public static void main(String[] args) {
//        HelloKoko.main("i am fuck ");
        System.out.println("what i fuck ");
        HelloKoko.main(new String[]{"hahaha"});
    }
}
