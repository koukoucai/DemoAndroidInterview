package com.koko.interview.ui.democompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koko.interview.R
import com.koko.interview.ui.theme.*

/**
 * 主页
 * Created by huanggang on 2022/9/30
 */

@Preview
@Composable
fun HomePage() {
    Scaffold(bottomBar = {
        BottomBar()
    }) {
        Column(Modifier.padding(it).fillMaxSize().background(White).padding(horizontal = 16.dp)) {
            SearchBar()
            BloomRowBanner()
            BloomInfoList()
        }
    }
}

@Composable
fun BloomRowBanner() {
    Column {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = "Browse themes",
                style = H1,
                color = Gray,
                modifier = Modifier.fillMaxWidth().paddingFromBaseline(top = 32.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.height(136.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bloomBarrnerList.size) {
                PlantCard(bloomBarrnerList[it])
            }
        }
    }
}


@Composable
fun BloomInfoList(){
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Design your home garden",
            style = H1,
            color = Gray,
            modifier = Modifier.paddingFromBaseline(top = 40.dp))
            Icon(imageVector = Icons.Filled.Filter1,"filter", modifier = Modifier.padding(top = 24.dp).size(24.dp))
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 56.dp)) {
            items(bloomInfoList.size){
                if (it !=0){
                    Spacer(modifier = Modifier.height(8.dp))
                }

                DesignCard(bloomInfoList[it])
            }
        }
    }
}

@Composable
fun DesignCard(plant: ImageItem) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(plant.resId),
            contentScale = ContentScale.Crop,
            contentDescription = "image",
            modifier = Modifier.size(64.dp).clip(small)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Column {
                    Text(
                        text = plant.name,
                        style = H2,
                        color = Gray,
                        modifier = Modifier.paddingFromBaseline(top = 24.dp)
                    )
                    Text(
                        text = "This is a description",
                        style = Body1,
                        color = Gray,
                        modifier = Modifier.paddingFromBaseline(bottom = 24.dp)
                    )
                }

                Checkbox(modifier = Modifier.padding(top = 24.dp).size(24.dp),
                checked = false, onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.Red
                ))

            }
            Divider(color = Gray, modifier = Modifier.padding(top = 16.dp), thickness = 0.5.dp)
        }
    }
}

@Composable
fun PlantCard(plant: ImageItem) {
    Card(
        modifier = Modifier.size(136.dp).clip(small)
    ) {
        Column {
            Image(
                painter = painterResource(id = plant.resId),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(96.dp)
            )
            Box(Modifier.fillMaxWidth().padding(start = 16.dp)) {
                Text(
                    text = plant.name, style = H2, color = Gray,
                    modifier = Modifier.fillMaxWidth()
                        .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    Box {
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth().height(56.dp).clip(small)
                .border(BorderStroke(1.dp, Color.Black)),
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Filled.Search),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            placeholder = {
                Text(
                    text = "Search",
                    style = Body1,
                    color = Gray
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = White,
                unfocusedBorderColor = White,//未选中时下边框颜色
                focusedBorderColor = White//选中时下边框颜色
            )
        )
    }
}

@Composable
fun BottomBar() {
    BottomNavigation(modifier = Modifier.fillMaxWidth().height(56.dp).background(Pink100)) {
        navList.forEach {
            BottomNavigationItem(
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(it.resId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = it.name,
                        style = Caption,
                        color = Gray
                    )
                },
                selected = ("home" == it.name)
            )
        }
    }
}

data class ImageItem(val name: String, val resId: Int)

val bloomBarrnerList = listOf(
    ImageItem("Demo pic 0", R.drawable.demo_pic_0),
    ImageItem("Demo pic 1", R.drawable.demo_pic_1),
    ImageItem("Demo pic 2", R.drawable.demo_pic_2)
)


val bloomInfoList = listOf(
    ImageItem("Demo pic 3", R.drawable.demo_pic_3),
    ImageItem("Demo pic 4", R.drawable.demo_pic_4),
    ImageItem("Demo pic 5", R.drawable.demo_pic_5),
    ImageItem("Demo pic 6", R.drawable.demo_pic_6),
    ImageItem("Demo pic 2", R.drawable.demo_pic_2),
    ImageItem("Demo pic 1", R.drawable.demo_pic_1),
    ImageItem("Demo pic 0", R.drawable.demo_pic_0),
)

val navList = listOf(
    ImageItem("home", R.drawable.home),
    ImageItem("favorite", R.drawable.favorite),
    ImageItem("profile", R.drawable.profile),
    ImageItem("cart", R.drawable.cart),
)