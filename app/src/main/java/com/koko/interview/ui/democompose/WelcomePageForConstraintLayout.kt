package com.koko.interview.ui.democompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.koko.interview.R
import com.koko.interview.ui.theme.*

/**
 * 用constraintlayout再写一遍
 * Created by huanggang on 2022/9/29
 */

@Preview
@Composable
fun WelcomePageForConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Pink100)) {
        val (bgimage, logoimage, titleimg, titletext, loginintext, loginuptext) = remember { createRefs() }
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_welcome_bg)),
            contentDescription = "welcome_bg",
            modifier = Modifier.constrainAs(bgimage) {
                width = Dimension.matchParent
                height = Dimension.matchParent
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_welcome_illos)),
            contentDescription = "welcom_illos",
            modifier = Modifier.wrapContentSize().constrainAs(logoimage) {
                top.linkTo(parent.top, 72.dp)
                end.linkTo(parent.end)
            }
        )

        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_logo)),
            contentDescription = "welcome_logo",
            modifier = Modifier.height(32.dp).constrainAs(titleimg) {
                top.linkTo(logoimage.bottom, 48.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            }
        )

        Text(
            text = "Beautiful home garden solutions",
            textAlign = TextAlign.Center,
            style = Subtitle1,
            color = Gray,
            modifier = Modifier.constrainAs(titletext) {
                top.linkTo(titleimg.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = {
            },
            modifier = Modifier.height(48.dp).padding(horizontal = 16.dp).fillMaxWidth()
                .clip(medium).constrainAs(loginintext) {
                top.linkTo(titletext.bottom, 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Pink900)
        ) {
            Text(
                text = "Create account",
                style = Button,
                color = White
            )
        }
        TextButton(onClick = {}, modifier = Modifier.constrainAs(loginuptext){
            top.linkTo(loginintext.bottom, 24.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }){
            Text(text = "Login in",
                style = Button,
                color = Pink900)


        }

    }
}

fun decoupledConstraints(margin:Dp):ConstraintSet{
    return ConstraintSet{
//        Text(
//            text = "Create account",
//            style = Button,
//            color = White,
//            modifier = Modifier.layoutId("123")
//        )
    }
}