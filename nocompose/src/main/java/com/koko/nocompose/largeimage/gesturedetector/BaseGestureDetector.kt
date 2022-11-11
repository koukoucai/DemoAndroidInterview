package com.koko.nocompose.largeimage.gesturedetector

import android.content.Context
import android.view.MotionEvent

/**
 * 手势基类
 * Created by huanggang on 2022/9/3
 */
abstract class BaseGestureDetector(val context: Context) {
    var gestureInProgress:Boolean = false
    var preMotionEvent:MotionEvent ?= null
    var currentMotionEvent:MotionEvent ?= null

    fun onTouch(event: MotionEvent):Boolean{
        if (!gestureInProgress){
            handleStartProgressEvent(event)
        } else{
            handleInProgressEvent(event)
        }

        return true
    }



    protected abstract fun handleInProgressEvent(event: MotionEvent)
    protected abstract fun handleStartProgressEvent(event: MotionEvent)
    protected abstract fun updateStateByEvent(event: MotionEvent)

    protected fun resetState(){
        preMotionEvent?.let {
            it.recycle()
            preMotionEvent = null
        }

        currentMotionEvent?.let {
            it.recycle()
            currentMotionEvent = null
        }

        gestureInProgress = false
    }
}