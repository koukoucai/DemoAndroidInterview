package com.koko.interview

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.koko.interview.ui.theme.DemoAndroidInterviewTheme
import kotlinx.coroutines.launch

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

                    TestScaffold()

                }
            }
        }
    }
}

data class Item(
    val name: String,
    val icon: ImageVector
)

@Preview
@Composable
fun TestScaffold() {
    val items = listOf(
        Item("主页", Icons.Filled.Home),
        Item("列表", Icons.Filled.List),
        Item("设置", Icons.Filled.Settings)
    )

    var selectedItem by remember { mutableStateOf(0) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(topBar = {
        TopAppBar(
            title = { Text("我是主页") },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }, content = {
                    Icon(imageVector = Icons.Filled.Menu, null)
                })
            }

        )
    }, bottomBar = {
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = { Icon(imageVector = item.icon, null) },
                    alwaysShowLabel = false,
                    label = { Text(text = item.name) }
                )
            }
        }
    },
        drawerContent = {
                 Text("我是侧边栏")
        },
        scaffoldState =  scaffoldState,

//         Since Compose 1.2.0 it's required to use padding parameter, passed into Scaffold content composable. You should apply it to the topmost container/view in content:
        content = {
            Column(modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState())) {
                TestCard()
                TestConstraintlayout()
                TestConstraintLayoutBarrier()
//                LazyColumn {
//                    items(50){
//
//                    }
//                }

            }
        })


    BackHandler (enabled = scaffoldState.drawerState.isOpen, onBack = {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    })
}

@Preview
@Composable
fun TestConstraintLayoutBarrier() {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        val (userNameTextRef, userPwdTextRef, userNameEdtRef, userPwdEdtRef) = remember { createRefs() }
        val barrier = createEndBarrier(userNameTextRef, userPwdTextRef)
        val text = remember { mutableStateOf("") }
        Text(
            text = "用户名用户名用户名用户名",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(userNameTextRef, constrainBlock = {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            })
        )
        Text(
            text = "密码",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(userPwdTextRef, constrainBlock = {
                start.linkTo(userNameTextRef.start)
                top.linkTo(userNameTextRef.bottom, 10.dp)
            })
        )
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            modifier = Modifier.constrainAs(userNameEdtRef) {
                start.linkTo(barrier, 10.dp)
                top.linkTo(userNameTextRef.top)
                width = Dimension.fillToConstraints
            }
        )
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            modifier = Modifier.constrainAs(userPwdEdtRef) {
                start.linkTo(barrier, 10.dp)
                top.linkTo(userPwdTextRef.top)
                width = Dimension.fillToConstraints
            }
        )
    }
}


@Preview
@Composable
fun TestConstraintlayout() {
    ConstraintLayout(
        modifier = Modifier.width(300.dp).padding(10.dp)
    ) {
        val (portraitImageRef, usernameTextRef, desTextRef) = remember { createRefs() }
        createHorizontalChain()
        Image(
            painter = painterResource(R.drawable.maqi),
            contentDescription = null,
            modifier = Modifier.constrainAs(portraitImageRef, constrainBlock = {
                this.start.linkTo(parent.start)
                top.linkTo(parent.top)
            }).clip(CircleShape)
                .border(width = 2.dp, color = Color(0XFF5FB878), shape = CircleShape)
        )

        Text(
            text = "我是contraint标题我是contraint标题我是contraint标题我是contraint标题我是contraint标题",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(usernameTextRef) {
                top.linkTo(portraitImageRef.top)
                start.linkTo(portraitImageRef.end, margin = 10.dp)
                end.linkTo(parent.end, 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        Text(
            text = "我是副标题，个人描述",
            fontSize = 14.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(desTextRef) {
                circular(usernameTextRef, 200f, 150.dp)
            }
        )
    }
}


@Preview
@Composable
fun TestCard(index:Int= 100) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(12.dp).fillMaxWidth(), elevation = 10.dp
    ) {
        Column(Modifier.padding(12.dp)) { //margin
            Text(
                text = "Jetpack Compose 是什么？",
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.padding(vertical = 5.dp))
            Text(
                text = "Jetpack Compose 是$index？是未来的趋势，是单向数据流的趋势，是MVI",
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                )
                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Chat, contentDescription = null)
                    }
                )

                IconButton(
                    onClick = {

                    },
                    content = {
                        Icon(imageVector = Icons.Filled.Share, contentDescription = null)
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