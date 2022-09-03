package com.koko.nocompose.largeimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koko.nocompose.databinding.ActivityLargeImageBinding

/**
 * 大图加载类，bitmapRegionDecoder的使用
 * Created by huanggang on 2022/9/2
 */
class LargeImageActivity : AppCompatActivity() {
    lateinit var activityLargeImageBinding: ActivityLargeImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLargeImageBinding = ActivityLargeImageBinding.inflate(layoutInflater)
        setContentView(activityLargeImageBinding.root)

        kotlin.runCatching {

        }

    }
}