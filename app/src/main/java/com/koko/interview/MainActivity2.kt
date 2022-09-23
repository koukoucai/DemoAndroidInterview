package com.koko.interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koko.interview.ui.theme.DemoAndroidInterviewTheme

/**
 * 部分样式
 * Created by huanggang on 2022/9/23
 */
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoAndroidInterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting2("Android")
                }
            }
        }
    }
}

@Preview
@Composable
fun TestCard() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(12.dp).fillMaxWidth(), elevation = 10.dp
    ) {
        Column (Modifier.padding(12.dp)){ //margin
            Text(
                text = "Jetpack Compose 是什么？",
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.padding(vertical = 5.dp))
            Text(
                text = "Jetpack Compose 是什么？是未来的趋势，是单向数据流的趋势，是MVI",
            )
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Favorite,contentDescription = null)
                    }
                )
                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Chat,contentDescription = null)
                    }
                )

                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Share,contentDescription = null)
                    }
                )
            }
        }
    }
}


@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    DemoAndroidInterviewTheme {
        Greeting2("Android")
    }
}