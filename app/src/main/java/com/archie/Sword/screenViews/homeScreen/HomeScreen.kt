@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.R
import com.example.Sword.ui.theme.SwordTheme

//viewModel: BottomNavigationSharedViewModel,state: BottomNavigationSharedStates,pagingData: T,events: BottomNavigationScreensSharedEvents,context: Context

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(context: Context, onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, pagingItems: LazyPagingItems<Verse>, contentPadding: PaddingValues){


    val overscrollEffect =  ScrollableDefaults.overscrollEffect()



    CompositionLocalProvider(
        LocalOverscrollConfiguration provides OverscrollConfiguration(
            glowColor = Color.Gray,
            drawPadding = PaddingValues()
        )
    )

    {


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),

            modifier = Modifier
                .fillMaxSize()
                .overscroll(overscrollEffect),
            contentPadding = contentPadding,

            content = {


                item {


                    Column(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {


                        Row(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.outline_wb_sunny_24),
                                contentDescription = ""
                            )
                            Text(
                                text = "Good morning, Archie",
                                fontFamily = FontFamily.Serif,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp, bottom = 10.dp)
                                    .align(Alignment.CenterVertically)

                            )


                        } // Row ENDs


                        Text(
                            text = "When you were slaves of sin you were free" +
                                    " from righteousness what did you gain from " +
                                    "the things that you are now ashamed of ?" +
                                    " the result of those things is death",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Thin,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 50.dp)
                                .align(Alignment.CenterHorizontally)
                                .width(280.dp)
                        )



                        Text(
                            text = "Guess which verse? (Click)",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 50.dp, top = 10.dp)
                                .align(Alignment.CenterHorizontally)
                                .width(280.dp)
                        )


                    }


                }


//            item {
//
//                themeSelection()
//            }


                item {

                    Row(
                        modifier = Modifier.padding(top= 30.dp)
                    ){
                        Text(
                            text = "Let's",

                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                top = 30.dp, end = 10.dp
                            )
                        )

                        Text(
                            text = "Go",

                            fontSize = 50.sp,
                            modifier = Modifier.padding(
                                bottom = 20.dp
                            )
                        )

                    }
                }




                item {


                    Card(
                        modifier = Modifier.width(200.dp),
                        colors = CardDefaults.cardColors(

                            containerColor = Color.White
                        )

                    ) {

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "Memorised",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Text(
                                text = buildAnnotatedString {

                                    withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                        append("5")

                                    }

                                    append(" Verses")

                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                            )

                            Text(
                                text = buildAnnotatedString {

                                    append("Crown: ")
                                    withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){

                                        append("Rooky")
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                //.padding(end = 40.dp)
                            )
                        }

                    }
                }

                items(pagingItems.itemCount) { index ->


                    var item = pagingItems[index]


                    Card(
                        modifier = Modifier
                            .width(362.dp)
                            .padding(top = 15.dp,),
                        // .height(100.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(

                            defaultElevation = 0.dp
                        ),
                        colors = CardDefaults.cardColors(

                            containerColor = Color.White
                        )

                    ) {

                        Column {

                            Text(
                                text = "${item?.bookName}",
                                fontSize = 30.sp,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier.padding(start = 10.dp)

                            )



                            Text(
                                text = "${item?.verse}",
                                fontFamily = FontFamily.Serif,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Text(
                                text = buildAnnotatedString {

                                    append("Memorised \n")

                                    withStyle(style = SpanStyle(fontSize = 28.sp)) {

                                        append("4 times")


                                    }


                                },

                                modifier = Modifier.align(Alignment.End)

                            )











                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                IconButton(onClick = { /*TODO*/ }) {

                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null
                                    )

                                }

                                IconButton(onClick = { /*TODO*/ }) {

                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null
                                    )

                                }


                                Row(

                                    modifier = Modifier.padding(start = 90.dp)
                                ) {

                                    Text(
                                        text = "Memorise Today",
                                        modifier = Modifier.padding(top = 15.dp)
                                    )
                                    Checkbox(
                                        checked = state.isCheckBoxTicked,
                                        onCheckedChange = { isCheckBoxTicked ->

                                            Log.d("CheckBoxContent", isCheckBoxTicked.toString())


                                            onEvent(BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse(isCheckBoxTicked))
                                        }

                                    )

                                }


                            }


                        } // COLUMN ENDS

                    } // CARD ENDS

                } // ITEMS ENDS

            } // CONTENT ENDS

        ) // LAZY COLUMN ENDS

    } // COMPOSITIONAL ENDS



}


@ExperimentalMaterial3Api
@Composable
fun experimentalHomeScreen(){


    val topAppBarIcons = listOf<TopAppBarIcon>(

        TopAppBarIcon(
            icon = Icons.Default.Search,
            description = "search"
        ),

        TopAppBarIcon(
            icon = Icons.Default.MoreVert,
            description = "More"
        )


        )



    val displayDropdownMenu = remember {

        mutableStateOf(false)
    }


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {

            TopAppBar(
                title = {

                    Text(
                        text = "Truth",
                        fontWeight = FontWeight.Bold,
                        fontSize = 33.sp
                    )
                },

                actions = {

                     topAppBarIcons.forEachIndexed { index, iconInfo->




                         IconButton(onClick = {

                             if(iconInfo.description == "More")
                                 displayDropdownMenu.value = true

                         }
                         ) {

                             Icon(
                                 imageVector = iconInfo.icon,
                                 contentDescription = iconInfo.description,
                                 modifier = Modifier.size(28.dp))

                         }


                     }


                    DropdownMenu(
                        expanded = displayDropdownMenu.value,
                        onDismissRequest = { displayDropdownMenu.value = false },
                        modifier = Modifier.width(100.dp)
                    ) {

                        DropdownMenuItem(text = { Text(text = "Hello") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "Hello") }, onClick = { /*TODO*/ })

                    }
                },

              scrollBehavior = scrollBehavior




            )




        },


    ) {


        experimentalHomeScreenContent(contentPading = it)

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun experimentalHomeScreenContent(contentPading: PaddingValues){



    var a = ""


    repeat(30){



        a += " In"


    }


    val list = listOf(a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a)



    val overscrollEffect =  ScrollableDefaults.overscrollEffect()



    CompositionLocalProvider(
        LocalOverscrollConfiguration provides OverscrollConfiguration(
            glowColor = Color.Gray,
            drawPadding = PaddingValues()
        )
    )

    {


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),

            modifier = Modifier
                .fillMaxSize()
                .overscroll(overscrollEffect),
            contentPadding = contentPading,

            content = {


                item {


                    Column(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {


                        Row(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.outline_wb_sunny_24),
                                contentDescription = ""
                            )
                            Text(
                                text = "Good morning, Archie",
                                fontFamily = FontFamily.Serif,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(start = 10.dp, bottom = 10.dp)
                                    .align(Alignment.CenterVertically)

                            )


                        } // Row ENDs


                        Text(
                            text = "When you were slaves of sin you were free" +
                                    " from righteousness what did you gain from " +
                                    "the things that you are now ashamed of ?" +
                                    " the result of those things is death",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Thin,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 50.dp)
                                .align(Alignment.CenterHorizontally)
                                .width(280.dp)
                        )



                        Text(
                            text = "Guess which verse? (Click)",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(start = 50.dp, top = 10.dp)
                                .align(Alignment.CenterHorizontally)
                                .width(280.dp)
                        )


                    }


                }


//            item {
//
//                themeSelection()
//            }


                item {

                    Row(
                        modifier = Modifier.padding(top= 30.dp)
                    ){
                        Text(
                            text = "Let's",

                            fontSize = 20.sp,
                            modifier = Modifier.padding(
                                top = 30.dp, end = 10.dp
                            )
                        )

                        Text(
                            text = "Go",

                            fontSize = 50.sp,
                            modifier = Modifier.padding(
                                bottom = 20.dp
                            )
                        )

                    }
                }




                item {


                    Card(
                        modifier = Modifier.width(200.dp),
                        colors = CardDefaults.cardColors(

                            containerColor = Color.White
                        )

                    ) {

                        Column(
                            modifier = Modifier.fillMaxWidth()  
                        ) {

                            Text(
                                text = "Memorised",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Text(
                                text = buildAnnotatedString {

                                      withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){
                                          append("5")

                                      }

                                    append(" Verses")

                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                )

                            Text(
                                text = buildAnnotatedString {

                                      append("Crown: ")
                                      withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)){

                                          append("Rooky")
                                      }
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    //.padding(end = 40.dp)
                            )
                        }

                    }
                }

                items(list) { item ->


                    Card(
                        modifier = Modifier
                            .width(362.dp)
                            .padding(top = 15.dp,),
                        // .height(100.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(

                            defaultElevation = 0.dp
                        ),
                        colors = CardDefaults.cardColors(

                            containerColor = Color.White
                        )

                    ) {

                        Column {

                            Text(
                                text = "Genesis 1:1",
                                fontSize = 30.sp,
                                fontFamily = FontFamily.Cursive,
                                modifier = Modifier.padding(start = 10.dp)

                            )



                            Text(
                                text = "${item}",
                                fontFamily = FontFamily.Serif,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Text(
                                text = buildAnnotatedString {

                                    append("Memorised \n")

                                    withStyle(style = SpanStyle(fontSize = 28.sp)) {

                                        append("4 times")


                                    }


                                },

                                modifier = Modifier.align(Alignment.End)

                            )











                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                IconButton(onClick = { /*TODO*/ }) {

                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null
                                    )

                                }

                                IconButton(onClick = { /*TODO*/ }) {

                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null
                                    )

                                }


                                Row(

                                    modifier = Modifier.padding(start = 90.dp)
                                ) {

                                    Text(
                                        text = "Memorise Today",
                                        modifier = Modifier.padding(top = 15.dp)
                                    )
                                    Checkbox(checked = false, onCheckedChange = {})

                                }


                            }


                        } // COLUMN ENDS

                    } // CARD ENDS

                } // ITEMS ENDS

            } // CONTENT ENDS

        ) // LAZY COLUMN ENDS

    } // COMPOSITIONAL ENDS



}










@ExperimentalFoundationApi
@Composable
fun overScrollEffect(){

    var a = ""


    repeat(30){



        a += " In"


    }



    val list = listOf(a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a,a)

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides OverscrollConfiguration(
            glowColor = Color.Gray,
            drawPadding = PaddingValues()
        )
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {

                items(list){

                    Text(text = it)
                }
            }
        )


    }


}



@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 33)
@Composable
fun GreetingPreview() {

    SwordTheme {

         experimentalHomeScreen()
    }
}



