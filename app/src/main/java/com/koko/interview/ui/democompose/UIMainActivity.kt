package com.koko.interview.ui.democompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * demo acitivity
 * Created by huanggang on 2022/9/30
 */
class UIMainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setDecorFitsSystemWindows(false)  //min level is 30
        setContent {
//            WelcomePageForConstraintLayout()
            HomePage()
        }
    }
}