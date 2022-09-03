package com.koko.nocompose.largeimage.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.koko.nocompose.largeimage.gesturedetector.MoveGestureDetector
import com.koko.nocompose.largeimage.gesturedetector.SimpleOnMoveGestureListener
import java.io.InputStream

/**
 * 支持手势拖动的大图加载view
 * Created by huanggang on 2022/9/3
 */
class LargeImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    val TAG = "LargeImageView"

    lateinit var bitmapRegionDecoder: BitmapRegionDecoder
    var imageWidth: Int = 0
    var imageHeight: Int = 0

    @Volatile
    var rect = Rect()


    private var moveGestureDetector: MoveGestureDetector? = null
    val options = BitmapFactory.Options()

    init {
        options.inPreferredConfig = Bitmap.Config.RGB_565
        init()
    }


    fun setInputStream(inputStream: InputStream) {
        kotlin.runCatching {
            println("after decodeStream  inputStream ${inputStream}  ")

            BitmapFactory.Options().apply {
                this.inJustDecodeBounds = true
                BitmapFactory.decodeStream(inputStream, null, this)


                imageHeight = this.outHeight
                imageWidth = this.outWidth

                println("after decodeStream  imageHeight $imageHeight   imageWidth $imageWidth   ")
                println("after decodeStream   this.outHeight ${this.outHeight}    this.outWidth ${this.outWidth}   ")

            }

            bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)!!
            requestLayout()
            invalidate()

        }.onSuccess {
            inputStream.close()
        }.onFailure {
            it.printStackTrace()
            inputStream.close()
        }

    }

    private fun init() {
        moveGestureDetector = MoveGestureDetector(context, object : SimpleOnMoveGestureListener() {
            override fun onMove(detector: MoveGestureDetector): Boolean {
                val moveX = detector.getMoveX().toInt()
                val moveY = detector.getMoveY().toInt()
                println("onmove  moveX $moveX moveY $moveY")
                if (imageWidth > width) {
                    rect.offset((-moveX), 0)
                    checkWidth()
                    invalidate()
                }

                if (imageHeight > height) {
                    rect.offset(0, (-moveY))
                    checkHeight()
                    invalidate()
                }

                return true
            }
        })
    }

    private fun checkWidth() {
        val r = rect
        val iw = imageWidth
        val ih = imageHeight

        if (r.right > iw) {
            r.right = iw
            r.left = iw - width
        }
        if (r.left < 0) {
            r.left = 0
            r.right = width
        }
    }

    private fun checkHeight() {
        val r = rect
        val iw = imageWidth
        val ih = imageHeight
        if (r.bottom > ih) {
            r.bottom = ih
            r.top = ih - height
        }

        if (r.top < 0) {
            r.top = 0
            r.bottom = height
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        moveGestureDetector?.onTouch(event)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmapRegionDecoder.decodeRegion(rect, options), 0f, 0f, null)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        val iw = imageWidth
        val ih = imageHeight

        println("onmeasure  width $width   height $height  iw $iw ih $ih ")

        //默认直接显示图片的中心区域
        rect.left = iw / 2 - width / 2
        rect.top = (ih - height) / 2
        rect.right = rect.left + width
        rect.bottom = rect.top + height
    }
}