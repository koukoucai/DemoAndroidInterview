package com.koko.interview.ui.democompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koko.interview.R
import com.koko.interview.ui.theme.*

/**
 * 欢迎页
 * Created by huanggang on 2022/9/28
 */
@Preview
@Composable
fun WelComePage() {
    Box(modifier = Modifier.fillMaxSize().background(Pink100)) {
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_welcome_bg)),
            contentDescription = "welcome_bg",
            modifier = Modifier.fillMaxSize()
        )

        WelcomeContent()
    }
}

@Composable
fun WelcomeContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(72.dp))
        LeafImage()
        Spacer(Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(Modifier.height(40.dp))
        WelcomeButton()
    }
}

@Composable
fun LeafImage() {
    Image(
        painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_welcome_illos)),
        contentDescription = "welcom_illos",
        modifier = Modifier.wrapContentSize().padding(start = 88.dp)
    )
}

@Composable
fun WelcomeTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(R.drawable.light_logo)),
            contentDescription = "welcome_logo",
            modifier = Modifier.wrapContentWidth().height(32.dp)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "Beautiful home garden solutions",
                textAlign = TextAlign.Center,
                style = Subtitle1,
                color = Gray
            )
        }
    }
}
@Composable
fun WelcomeButton(){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            ){
        Button(onClick = {

        },
        modifier = Modifier.height(48.dp).padding(horizontal = 16.dp).fillMaxWidth().clip(medium),
        colors = ButtonDefaults.buttonColors(backgroundColor = Pink900)){
            Text(
                text = "Create account",
                style = Button,
                color = White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = {}){
            Text(text = "Login in",
                style = Button,
            color = Pink900)


        }
    }
}

