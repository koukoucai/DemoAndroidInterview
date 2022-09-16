package com.koko.interview.java;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.koko.example.HelloKoko;
import com.koko.interview.R;
import com.koko.kokoannotation.KokoAnnotationJava;

/**
 * Created by huanggang on 2022/9/5
 */
@KokoAnnotationJava
public class JavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_javapoet);
        HelloKoko.main(new String[]{"fuck??? "});
    }
}
