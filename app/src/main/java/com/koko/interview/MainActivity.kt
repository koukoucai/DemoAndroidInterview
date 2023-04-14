package com.koko.interview

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.koko.interview.ui.theme.DemoAndroidInterviewTheme

/**
 * 组件的各种尝试
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
    Column  (modifier = Modifier.fillMaxWidth()){
        Row{
            TestSwitch()
            TestCheckBox()
            TestButton()
        }
        TestSlider()
        TestProgress()

        TextBilibili()
        TestRow()
        TextFieldSample()
        TestOther()

    }
}

@Composable
fun TestProgress(){
    var progress by remember { mutableStateOf(0.1f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        finishedListener = {
            Log.e(TAG, "TestProgress: 结束！ $progress" )
        }
    )

    Column {
        Row {
            CircularProgressIndicator(progress = animatedProgress)
            CircularProgressIndicator()
            Spacer(Modifier.requiredHeight(30.dp))
            OutlinedButton(onClick = {
                if (progress < 1f) progress += 0.1f
            }, content = {
                Text(
                    text = "增加进度"
                )
            }, border = BorderStroke(2.dp,MaterialTheme.colors.error))
        }
        LinearProgressIndicator(progress = animatedProgress)
        LinearProgressIndicator()
    }
}
@Composable
fun TestSlider(){
    var sliderValue by remember { mutableStateOf(0f) }
    Text(
        text = "进度 "+"%.1f".format(sliderValue*100)+"%"
    )
    Slider(
        value =  sliderValue,
        onValueChange = {
            sliderValue = it
        },
        steps = 2,
        colors = SliderDefaults.colors(thumbColor = Color.Gray,activeTrackColor = Color.Blue, inactiveTrackColor = Color.Black,inactiveTickColor = Color.Yellow, activeTickColor = Color.Green)
    )
}

@Composable
fun TestSwitch(){
    val checkstate = remember { mutableStateOf(true) }
    Switch(
        checked = checkstate.value,
        onCheckedChange = {
            checkstate.value = it
        },
        colors = SwitchDefaults.colors(checkedThumbColor = Color.Red, checkedTrackAlpha = 1f, checkedTrackColor = Color.Magenta,
        uncheckedThumbColor = MaterialTheme.colors.onError, uncheckedTrackAlpha = 0.5f, uncheckedTrackColor = MaterialTheme.colors.secondary)
    )
}

@Composable
fun TestCheckBox(){
    val checkstate = remember { mutableStateOf(true) }
    Checkbox(
//        enabled = false,
        checked = checkstate.value,
        onCheckedChange = {
            checkstate.value = it
        },
        colors = CheckboxDefaults.colors(checkedColor =  Color.Red, uncheckedColor = Color.Blue)
    )
    RadioButton(
        selected = checkstate.value,
        onClick = {
            checkstate.value = !checkstate.value
        },
        colors = RadioButtonDefaults.colors(selectedColor = Color.Cyan, unselectedColor = Color.Yellow)
    )

}

@Preview
@Composable
fun TestButton(){
    Column {
        val context =  LocalContext.current
        val interaction = remember { MutableInteractionSource() }
        val colorBt = if (interaction.collectIsPressedAsState().value) Color.Green else Color.Cyan

        val openDialog = remember { mutableStateOf(false) }

//        TestDialog(openDialog)
        TextAlertDialog(openDialog)
        Button(
            onClick = {
                Toast.makeText(context,"i am button",Toast.LENGTH_SHORT).show()
                openDialog.value = true
//                TestDialog(openDialog)

            },
            border = BorderStroke(5.dp,colorBt),
            content = {
                Icon(imageVector = Icons.Rounded.Done,contentDescription = null)
                Spacer(modifier =  Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "我是按钮,想显示对话框")
            },
            interactionSource =interaction
        )

    }
}
@Composable
fun TextAlertDialog(openDialog:MutableState<Boolean>){
    if (openDialog.value){
        AlertDialog(
            onDismissRequest = {
              openDialog.value = false
            },
            title = {
              Text(
                  text = "我是标题"
              )
            },
            text = {
                Text(
                    text = "我是对话框内容，你看到了， 赛博朋克！！！"
                )
            },
            confirmButton = {
                TextButton(
                   onClick = {
                       openDialog.value = false
                   },
                    content = {
                        Text( text = "确定",
                            style = MaterialTheme.typography.button)
                    }
                )
            },
            dismissButton = {
                Text(
                    text = "取消",
                    style = MaterialTheme.typography.button.copy(color = Color.Red),
                    modifier = Modifier.clickable {
                        openDialog.value = false
                    }
                )
            }
        )
    }
}

@Composable
fun TestDialog(openDialog:MutableState<Boolean>){
    if (openDialog.value){
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            },
            content = {
                Box(
                    Modifier.width(200.dp).height(100.dp).background(brush = Brush.horizontalGradient(
                        listOf(Color.Red,Color.Gray,Color.Cyan,Color.Transparent)))
                )
            },
            properties = DialogProperties(
                dismissOnBackPress = true,dismissOnClickOutside = true
            )
        )
    }
}

@Preview
@Composable
fun TestIcon(){
    Row{
        Icon(imageVector =  Icons.Outlined.AccountBox,contentDescription = null, tint = Color.Red)
        Icon(imageVector =  Icons.Filled.AccountBox,contentDescription = null)
        Icon(imageVector =  Icons.Sharp.AccountBox,contentDescription = null)
        Icon(imageVector =  Icons.TwoTone.AccountBox,contentDescription = null,tint = Color.Blue)
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground),contentDescription = null)
        Image(painter = painterResource(id = R.drawable.maqi),contentDescription = null, colorFilter = ColorFilter.lighting(Color.Blue,Color.Red))
        Image(painter = painterResource(id = R.drawable.maqi),contentDescription = null, colorFilter = ColorFilter.tint(Color.Blue))
    }
}

@Composable
fun TestOther(){
    val verticalGradientBrush = Brush.verticalGradient(colors = listOf(
        Color.Yellow,Color.Gray,Color.Blue
    ))

    val context = LocalContext.current
    Row {
        Box(Modifier.size(20.dp).background(color =  Color.Red))
        Spacer(Modifier.width(5.dp))
        Box(Modifier.size(100.dp).clip(RoundedCornerShape(20.dp)).background(color =  Color.Cyan).padding(2.dp)
            .border(5.dp, color =  Color.Green, shape = RectangleShape)//shape = RoundedCornerShape(20.dp))
            .padding(5.dp)
            .background(brush = verticalGradientBrush)){
            Text("渐变色")
        }
    }

    Text(
        text =  stringResource(R.string.hello_text),
        color = colorResource(R.color.teal_700),
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

}
@Preview
@Composable
fun TextBilibili(){
    var text by remember { mutableStateOf("111") }
    Box(modifier = Modifier.fillMaxWidth().height(55.dp).background(Color(0xFFD3D3D3)),
    contentAlignment = Alignment.Center){
        BasicTextField(value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.padding(horizontal = 10.dp)
                .background(Color.White, shape = CircleShape).height(45.dp).fillMaxWidth()
//                .matchParentSize()
            ,
            decorationBox = {
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp).fillMaxWidth()){

                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)

                    //hint
                    Box(modifier = Modifier.padding(horizontal = 10.dp).weight(1f)
                        , contentAlignment = Alignment.CenterStart){
                        if (text.isEmpty()){
                            Text(
                                text = "输入点东西瞅瞅",
                                style = TextStyle(
                                    color = Color(0,0,0,128)
                                )
                            )
                        }
                        it()
                    }

                    //clear button
                    if (text.isNotEmpty()){
                        IconButton(
                            onClick = {
                                text = ""
                            },
                            modifier = Modifier.size(16.dp),
                            content = {
                                Icon(imageVector = Icons.Filled.Close,contentDescription = null)
                            }
                        )
                    }

                }

            }

        )
    }

}
@Preview
@Composable
fun TestRow(){
    var text by remember { mutableStateOf("123455") }
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp).fillMaxWidth()){

        Icon(imageVector = Icons.Filled.Search, contentDescription = null)


        //hint
        Box(modifier = Modifier.padding(horizontal = 10.dp).weight(1f)
            , contentAlignment = Alignment.CenterStart){
            if (text.isEmpty()){
                Text(
                    text = "输入点东西瞅瞅",
                    style = TextStyle(
                        color = Color(0,0,0,128)
                    )
                )
            }
        }

        //clear button
        if (text.isNotEmpty()){
            IconButton(
                onClick = {
                    text = ""
                },
                modifier = Modifier.size(16.dp),
                content = {
                    Icon(imageVector = Icons.Filled.Close,contentDescription = null)
                }
            )
        }

    }
}

@Preview
@Composable
fun TextFieldSample(){
    var text by remember { mutableStateOf("") }


    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(text="用户名")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AccountBox,contentDescription = null)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Call,contentDescription = null)
//            Icon(painter = painterResource(R.drawable.ic_launcher_foreground),contentDescription = null)
        },
        placeholder = {
            Text("hint")
        }
    )

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text("outlinedtextfield")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AccountBox,contentDescription = null)
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