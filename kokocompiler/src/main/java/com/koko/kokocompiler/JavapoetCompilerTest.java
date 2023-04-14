package com.koko.kokocompiler;

import com.koko.kokoannotation.KokoAnnotationJava;

import java.util.Arrays;

/**
 * Created by huanggang on 2022/9/5
 */
@KokoAnnotationJava
class JavapoetCompilerTest {
    public static void main(String[] args) {
        System.out.println("args =fuck?? " + Arrays.deepToString(args));
    }
}
