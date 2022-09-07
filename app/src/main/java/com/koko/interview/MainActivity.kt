package com.koko.interview

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.koko.interview.ui.theme.DemoAndroidInterviewTheme

/**
 *
 * Created by huanggang on 2022/9/2
 */
val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: compose created" )
        setContent {
            DemoAndroidInterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Log.e(TAG, "Greeting: compose created" )
    Text(text = "Hello12 $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoAndroidInterviewTheme {
        Greeting("Android")
    }
}