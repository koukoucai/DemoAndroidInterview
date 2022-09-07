package com.koko.kokocompiler;

import com.google.auto.service.AutoService;
import com.koko.kokoannotation.KokoAnnotationJava;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Created by huanggang on 2022/9/5
 */
@AutoService(Processor.class)
public class KokocompilerProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment){
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement :annotations){
            System.out.println("annotations = " + annotations.size() + ", roundEnv = " + roundEnv+"  KokoAnnotationJava.class.getCanonicalName() "+KokoAnnotationJava.class.getCanonicalName());
            if (typeElement.getQualifiedName().toString().equals(KokoAnnotationJava.class.getCanonicalName())){

                //main method  它提供$L(for Literals), $S(for Strings), $T(for Types), $N(for Names)等标识符，用于占位替换。
                MethodSpec methodSpec = MethodSpec.methodBuilder("main")
                        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                        .returns(void.class)
                        .addParameter(String[].class,"param")
                        .addStatement("$T.out.println($S)",System.class,"hello fuck it2")
                        .build();

                //create class
                TypeSpec helloClass = TypeSpec.classBuilder("HelloKoko")
                        .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
                        .addMethod(methodSpec)
                        .build();

                JavaFile javaFile = JavaFile.builder("com.koko.example", helloClass)
                        .addFileComment("$S everybody know what it is expert me", "oh yeah")
                        .build();
                try {
                    javaFile.writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(KokoAnnotationJava.class.getCanonicalName());
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
