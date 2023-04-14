package com.koko.interview.ui.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import com.koko.interview.ui.theme.*

/**
 * 仿iOS计算器
 * Created by huanggang on 2022/9/30
 */
class CalculatorActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface(modifier = Modifier.background(color = caBlack).fillMaxSize()) {

            }
        }
    }
}

@Preview
@Composable
fun Calculator(){
    ConstraintLayout(modifier = Modifier.fillMaxSize().background(color = caBlack).padding(12.dp)) {
        val (btlayout,resulttetext) = remember { createRefs() }
        var result by remember { mutableStateOf("0") }

        val guideline = createGuidelineFromTop(0.4f)
        Text(
            text = result,
            style = MaterialTheme.typography.h1,
            color = caWhite,
            modifier = Modifier.constrainAs(resulttetext){
                width = Dimension.preferredWrapContent
                height = Dimension.preferredWrapContent
                bottom.linkTo(guideline)
                end.linkTo(parent.end)
            }
        )
        Column(modifier = Modifier.constrainAs(btlayout){
            top.linkTo(guideline)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
            height = Dimension.fillToConstraints
        }, verticalArrangement = Arrangement.SpaceBetween) {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CaButton("C", textColor = caBlack){

                }

                CaButton("+/-", textColor = caBlack){

                }

                CaButton("%", textColor = caBlack){

                }

                CaButton("÷", textBgColor = caYellow){

                }
            }

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CaButton("7", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("8", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("9",  textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("×", textBgColor = caYellow){

                }
            }

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CaButton("4", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("5", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("6",  textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("-", textBgColor = caYellow){

                }
            }

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                CaButton("1", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("2", textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("3",  textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("+", textBgColor = caYellow){

                }
            }

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){

                Button(
                    modifier = Modifier.clip(caCircle).height(85.dp).width(175.dp),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = caDeepGray)
                ){
                    Text(
                        text = "0",
                        color = caWhite,
                        style = MaterialTheme.typography.h4,
                    )
                }

                CaButton(".",  textColor = caWhite, textBgColor = caDeepGray){

                }

                CaButton("=", textBgColor = caYellow){

                }
            }
        }
    }
}

@Composable
fun CaButton(text:String = "", textBgColor: Color = caGray, textColor:Color = caWhite, modifier: Modifier = Modifier, onClick: () -> Unit){
    Button(
        modifier = modifier.clip(caCircle).size(85.dp),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = textBgColor)
    ){
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.h4
        )
    }
}