package com.koko.interview.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import com.koko.interview.ui.theme.*

/**
 * 登录页面
 * Created by huanggang on 2022/9/29
 */
@Preview
@Composable
fun LoginInPage() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(White).padding(horizontal = 16.dp)
    ) {
        val (logintitle, loginusername, loginpwd, loginexplain, loginbt) = remember { createRefs() }

        Text(
            text = "Login with email",
            style = H1,
            modifier = Modifier.constrainAs(logintitle) {
                width = Dimension.wrapContent
                top.linkTo(parent.top, 184.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        LoginTextField(
            "Email Address",
            Modifier.height(56.dp).clip(small).constrainAs(loginusername) {
                width = Dimension.matchParent
                top.linkTo(logintitle.bottom,8.dp)
            })

        LoginTextField(
            "Password(8+Characters)",
            Modifier.height(56.dp).clip(small).constrainAs(loginpwd) {
                width = Dimension.matchParent
                top.linkTo(loginusername.bottom,8.dp)
            })
    }
}

@Composable
fun LoginTextField(placeholder: String, modifier: Modifier) {
    OutlinedTextField(
        value = "",
        onValueChange = {

        },
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                style = Body1,
                color = Gray
            )
        }
    )
}