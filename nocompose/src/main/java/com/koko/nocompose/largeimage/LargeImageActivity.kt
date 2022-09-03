package com.koko.nocompose.largeimage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.koko.nocompose.databinding.ActivityLargeImageBinding

/**
 * 大图加载类，bitmapRegionDecoder的使用
 * Created by huanggang on 2022/9/2
 */
class LargeImageActivity : AppCompatActivity() {
    val TAG = "LargeImageActivity";
    lateinit var activityLargeImageBinding: ActivityLargeImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: no compsoe created " )
        activityLargeImageBinding = ActivityLargeImageBinding.inflate(layoutInflater)
        setContentView(activityLargeImageBinding.root)

        kotlin.runCatching {
            val inputStream = assets.open("taiwan.jpg")
            var width =0
            var height = 0

            BitmapFactory.Options().apply {
                inJustDecodeBounds = true
                BitmapFactory.decodeStream(inputStream,null,this)
                width = this.outWidth
                height = this.outHeight

                println("after decodeStream systemimage  imageHeight $height   imageWidth $width   ")

            }

            val bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream,false)

            BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.RGB_565
                val bitmap = bitmapRegionDecoder?.decodeRegion(Rect(width/2-100,height/2-100,width/2+100,height/2+100),this)
                activityLargeImageBinding.largeImg.setImageBitmap(bitmap)
            }

            val inputStreamExtra = assets.open("wallerpaper.jpg")

            activityLargeImageBinding.customLargeimageView.setInputStream(inputStreamExtra)


        }.onSuccess {
            Log.e(TAG,"成功！ ")
        }.onFailure {
            Log.e(TAG,"异常 ${it.message}")
            it.printStackTrace()
        }

    }
}