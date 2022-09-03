package com.koko.nocompose.largeimage.gesturedetector

import android.content.Context
import android.graphics.PointF
import android.view.MotionEvent

/**
 * 移动手势
 * Created by huanggang on 2022/9/3
 */
class MoveGestureDetector(context: Context,val onMoveGestureListener: OnMoveGestureListener):BaseGestureDetector(context) {
    private var currentPointF:PointF ?= null
    private var prePointF:PointF ?= null

    private var deltaPointF = PointF() //减少内存
    private var externalPointF = PointF() //记录结果 最终移动距离


    override fun handleInProgressEvent(event: MotionEvent) {
        when(event.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_CANCEL,MotionEvent.ACTION_UP -> {
                onMoveGestureListener.onMoveEnd(this)
                resetState()
            }
            MotionEvent.ACTION_MOVE -> {
                updateStateByEvent(event)
                val update = onMoveGestureListener.onMove(this)
                if (update){
                    preMotionEvent?.recycle()
                    preMotionEvent = MotionEvent.obtain(event)
                }
            }
        }
    }

    override fun handleStartProgressEvent(event: MotionEvent) {
        when(event.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN ->{
                resetState()
                preMotionEvent = MotionEvent.obtain(event)
                updateStateByEvent(event)
            }
            MotionEvent.ACTION_MOVE ->{
                gestureInProgress = onMoveGestureListener.onMoveBegin(this)
            }
        }
    }

    override fun updateStateByEvent(event: MotionEvent){
        val pre = preMotionEvent

        prePointF = calculateFocalPointer(pre?: MotionEvent.obtain(event))
        currentPointF = calculateFocalPointer(event)

        val skipThisMoveEvent = pre?.pointerCount != event.pointerCount

//        println("updateStateByEvent skipthis move event $skipThisMoveEvent ${ ((currentPointF?.x?:0f).minus(prePointF?.x?:0f))} ")

        externalPointF.x = if (skipThisMoveEvent) 0f else ((currentPointF?.x?:0f) .minus(prePointF?.x?:0f))
        externalPointF.y = if (skipThisMoveEvent) 0f else ((currentPointF?.y?:0f ).minus(prePointF?.y?:0f))
    }

    /**
     * 根据event 计算多指中心
     * @param
     */
    private fun calculateFocalPointer(event: MotionEvent):PointF{
        val count = event.pointerCount
        var x = 0f
        var y = 0f

        for (i in 0 until count){
            x += event.getX(i)
            y += event.getY(i)
        }

        x /= count
        y /= count

        return PointF(x,y)
    }
    fun getMoveX():Float = externalPointF.x

    fun getMoveY():Float = externalPointF.y

}

open class SimpleOnMoveGestureListener :OnMoveGestureListener{
    override fun onMoveBegin(detector: MoveGestureDetector): Boolean  = true

    override fun onMove(detector: MoveGestureDetector): Boolean  = false

    override fun onMoveEnd(detector: MoveGestureDetector) {
    }

}