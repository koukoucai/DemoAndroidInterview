package com.koko.nocompose.largeimage.gesturedetector

/**
 * 回调接口
 * Created by huanggang on 2022/9/3
 */
interface OnMoveGestureListener {
    fun onMoveBegin(detector: MoveGestureDetector):Boolean

    fun onMove(detector: MoveGestureDetector):Boolean

    fun onMoveEnd(detector: MoveGestureDetector)

}