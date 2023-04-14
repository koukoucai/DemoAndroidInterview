package com.koko.interview

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.koko.interview.ui.theme.DemoAndroidInterviewTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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


    val scope = rememberCoroutineScope()

//    scaffoldState.snackbarHostState.showSnackbar()
    DisposableEffect("1"){

        this.onDispose {

        }
    }
    var selectedItemabs by remember { mutableStateOf(0) }
    SideEffect {
        selectedItemabs = 2
    }

    val rememberupdatedstate1 = rememberUpdatedState(1)
    var rememberupdatedstate2 = rememberUpdatedState(2)



    LaunchedEffect("1"){
        snapshotFlow {

        }.collect(){

        }
    }

//    produceState()

    //derivedStateOf写法
    val postList = remember { mutableStateListOf<String>() }
    var keyword by remember { mutableStateOf("") }

    val result by remember { derivedStateOf {
        postList.filter {
            it.contains(keyword)
        }
    } }

    //耗时写法
    val postlist2 by remember { mutableStateOf(listOf("1","2")) }

//    val result1 by remember(postlist2,keyword){
//        postlist2.filter {
//            it.contains(keyword)
//        }
//    }

    val scaffoldState = rememberScaffoldState()
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
                TestComposableLocal()
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


@Composable
fun TestComposableLocal(){
    var LocalString = staticCompositionLocalOf { "i am your father baby" }
    Column {
        Text(
            text = LocalString.current,
            color = Color.Black
        )
        CompositionLocalProvider(LocalString provides "i am your father 2"){
            Text(
                text = LocalString.current,
                color = Color.Red
            )
        }
        CompositionLocalProvider(LocalString provides "i am 3" ){
            Text(
                text = LocalString.current,
                color = Color.Yellow
            )
        }

        Text(
            text = LocalString.current,
            color = Color.Black
        )
    }
}

@Composable
fun TestComposableLocal2() {
    val currentLocalColor = compositionLocalOf { Color.Green }
//    val currentLocalColor = staticCompositionLocalOf { Color.Green }
    var color by remember { mutableStateOf(Color.Red) }

    CompositionLocalProvider(currentLocalColor provides(color)){
//        ProvideTextStyle()
    }
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
//        val test = rememberSaveable()
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

@Composable
fun rememberUpdateCompose(ontime:() -> Unit){
    val currentOnTimeout by rememberUpdatedState(ontime)
    LaunchedEffect(Unit){
        delay(1000000)
        Log.e(TAG, "rememberUpdateCompose: " )
        currentOnTimeout()
    }
}

/**
 * 自定义text 从baseline 到顶部的高度
 */
fun Modifier.firstBaseLineToTop(firstBaselineToTop:Dp) = this.layout { measurable, constraints ->
    //采用布局约束对该组件完成测量，测量结果保存在Placeable
    val placeable = measurable.measure(constraints)
    //检测该组件是否存在内容基线
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    //获取基线高度 (内容到基线的高度)
    val firstBaseline = placeable[FirstBaseline]
    //摆放位置 = 设置顶部高度 - 实际文本顶部到基线的高度
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    //该组件占有的高度为摆放的顶部高度+ 时间内容的高度
    val height = placeable.height + placeableY
    //仅高度发生变化
    layout(placeable.width,height){
        placeable.placeRelative(0,placeableY)
    }
}