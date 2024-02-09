@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.archie.Sword.screenViews.homeScreen

import android.app.Application
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.foundation.OverscrollEffect
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.helperFunctions.shouldCardSizeBeAdjusted
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.addingVerseScreen.themeSelection
import com.archie.Sword.states.HomeScreenStates
import com.example.Sword.R
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.format.TextStyle

//viewModel: BottomNavigationSharedViewModel,state: HomeScreenStates,pagingData: T,events: BottomNavigationScreensSharedEvents,context: Context

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(context: Context, onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: HomeScreenStates, pagingItems: LazyPagingItems<Verse>, contentPadding: PaddingValues){


            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),

                verticalArrangement = Arrangement.spacedBy(7.dp),
                contentPadding = contentPadding,

                content = {

                    items(pagingItems.itemCount) { index ->


                        val verse = pagingItems[index]


                        Card(
                            modifier = Modifier
                                .width(362.dp)
                                .padding(top = 15.dp,)
                                .height(100.dp),
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(

                                defaultElevation = 4.dp
                            )
                        ){

                            Column {

                                Text(
                                    text = "${verse?.bookName} ${verse?.chapterAndVerseNumber}",
                                    fontSize = 30.sp,
                                    modifier = Modifier.padding(start = 10.dp)


                                )

                                Text(
                                    text = verse?.verse.toString(),
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(start = 10.dp)
                                )


                            } // COLUMN ENDS

                        } // CARD ENDS

                    } // ITEMS ENDS

                } //



            ) // LAZY COLUMN END




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
            description = "more"
        ),


        )


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

                         IconButton(onClick = { /*TODO*/ }) {

                             Icon(
                                 imageVector = iconInfo.icon,
                                 contentDescription = iconInfo.description,
                                 modifier = Modifier.size(28.dp))

                         }


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








    LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.fillMaxSize(),
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

                    Row {
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

            }

        )



}










@ExperimentalFoundationApi
@Composable
fun overScrollEffect(content: @Composable ()-> Unit){



        content


}



@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 33)
@Composable
fun GreetingPreview() {

    SwordTheme {

        experimentalHomeScreen()


    }
}



