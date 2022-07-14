package com.sukajee.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ListComposable (){
    /**
     * Example 1
     * This can be used if we have few things to display in scrollable column
     * if we have many items, then this approach is not optimal. We need to
     * use LazyColumn for that.
     *

    val scrollState = rememberScrollState()
    Column(
    modifier = Modifier
    .fillMaxSize()
    .verticalScroll(
    state = scrollState
    )
    ) {
    for (i in 1..50) {
    Text(
    text = "This is item $i",
    modifier = Modifier
    .fillMaxWidth()
    .padding(24.dp),
    textAlign = TextAlign.Center,
    fontSize = 24.sp
    )
    }
    }
     */


    /**
     * Example 2
     * This is the best way to load multiple items. With LazyColumn the items
     * are loaded lazily when the screen is scrolled.
     * We can use itemIndexed as well. Which is shown in next example.
     *

    LazyColumn {
    items(count = 5000) {
    Text(
    text = "This is item $it",
    modifier = Modifier
    .fillMaxWidth()
    .padding(24.dp),
    textAlign = TextAlign.Center,
    fontSize = 24.sp
    )

    }
    }
     */


    /**
     * Example 3
     * This way is used to load multiple items and pass our own list
     * from outside. This is how recyclerview in xml looks like.
     * So, easy so concise.
     *

    val list = (2000..5000).toList()
    LazyColumn {
    itemsIndexed(
    list
    ) { index, num ->
    Text(
    text = "The item is $num and the index is $index",
    modifier = Modifier
    .fillMaxWidth()
    .padding(24.dp),
    textAlign = TextAlign.Center,
    fontSize = 24.sp
    )
    }
    }
     */


    /**
     * Example 4
     * This ways is a typical way to replace recyclerview from xml.
     * We pass list from outside and use our custom composable.
     */

    var backGroundColor by remember {
        mutableStateOf(Color.Yellow)
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = backGroundColor
                )
        ) {
            itemsIndexed(
                itemList
            ) { index, _ ->
                ListItem(
                    title = itemList[index].title,
                    body = itemList[index].body,
                    painter = painterResource(id = itemList[index].image),
                    contentDescription = itemList[index].contentDescription,
                    doSomethingOnClick = {
                        /** We are changing the background color of LazyColumn on click.*/
                        backGroundColor = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1.0f
                        )
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "You clicked on item: $index",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }
        }
    }
}

private val itemList: List<SampleItem> by lazy {
    val items = mutableListOf<SampleItem>()
    for (i in 1..500) {
        val item = SampleItem(
            title = "This is the title: $i",
            body = "This is the body: $i",
            image = R.drawable.ic_launcher_background,
            contentDescription = "Content description $i"
        )
        items.add(item)
    }
    items
}


data class SampleItem(
    val title: String,
    val body: String,
    val image: Int,
    val contentDescription: String
)

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    painter: Painter,
    contentDescription: String,
    doSomethingOnClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                doSomethingOnClick()
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.None,
                modifier = modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = body,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp),
                    fontSize = 18.sp,
                )
            }
        }
    }
}


