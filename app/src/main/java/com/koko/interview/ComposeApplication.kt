package com.koko.interview

import android.app.Application
import android.content.Context

/**
 *
 * Created by huanggang on 2022/10/24
 */
class ComposeApplication:Application() {

    companion object {
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}