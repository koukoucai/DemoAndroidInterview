package com.koko.interview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//                    Greeting("Android")
                    ModifierTest()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierTest(){
    val verticalGradientBrush = Brush.verticalGradient(colors = listOf(
        Color.Yellow,Color.Gray,Color.Blue
    ))

    val context = LocalContext.current
    Column {
        Row {
            Box(Modifier.size(20.dp).background(color =  Color.Red))
            Spacer(Modifier.width(5.dp))
            Box(Modifier.size(100.dp).background(color =  Color.Cyan).padding(2.dp)
                .border(5.dp, color =  Color.Green, shape = RectangleShape)
                .padding(5.dp)
                .background(brush = verticalGradientBrush)){
                Text("渐变色")
            }
        }

        Text(
            text =  stringResource(R.string.hello_text),
            color = colorResource(R.color.teal_700),
            modifier = Modifier.width(200.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color.Red,
                textDecoration = TextDecoration.LineThrough
            )
        )


        SelectionContainer {
            Text(
                text = "material design",
                style = MaterialTheme.typography.overline,
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 15.sp), block = {append("我是第一句")})
//                pushStringAnnotation("tag","https://www.bing.com")
//                withStyle(style = SpanStyle(fontSize = 15.sp), block = {
//                    append("我是跳转地址")
//                })
//                pop()
                    append("\n我是第二句")
                    withStyle(style = ParagraphStyle(lineHeight = 15.sp), block = {append("\n 我是第三句")})

                }
            )
        }


        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 15.sp), block = {append("我不是跳转地址1\n")})
            pushStringAnnotation("url","https://www.bing.com")
            withStyle(style = SpanStyle(fontSize = 15.sp, color =  Color.Yellow), block = {
                append("我是跳转地址")
            })
            pop()
            withStyle(style = SpanStyle(fontSize = 15.sp, color = Color.Magenta), block = {append("我不是跳转地址2\n")})
        }
        ClickableText(
            text = annotatedText,
            onClick = {
                annotatedText.getStringAnnotations(tag = "url", start = it, end =  it).firstOrNull()?.let {
                    Toast.makeText(context,"我是点击后效果",Toast.LENGTH_LONG).show()
                }
            }
        )
        Box(modifier =  Modifier.size(18.dp).background(color = Color.Blue).offset(x = 10.dp,y = 10.dp).background(color =Color.Magenta)){}

        TextFieldSample()
    }
}

@Preview
@Composable
fun TextFieldSample(){
    var text by remember { mutableStateOf("") }

    TextField(
        value = "text",
        onValueChange = {

        },
        label = {
            Text(text="用户名")
        }
    )
}



@Composable
fun Greeting(name: String) {
    Log.e(TAG, "Greeting: compose created" )
    Text(text = "Hello112 $name!", modifier = Modifier.size(400.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoAndroidInterviewTheme {
        Greeting("Android")
    }
}