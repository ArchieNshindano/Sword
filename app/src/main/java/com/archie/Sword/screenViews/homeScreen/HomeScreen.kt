@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.helperFunctions.VerticalOverscroll
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.R
import com.example.Sword.ui.theme.SwordTheme
import kotlinx.coroutines.flow.flowOf

//viewModel: BottomNavigationSharedViewModel,state: BottomNavigationSharedStates,pagingData: T,event: BottomNavigationScreensSharedEvents,context: Context

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)




val list: List<Verse> = listOf(

    Verse(
        bookName = "Matthew",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  ssv",
        date = System.currentTimeMillis(),
        themeName = "Love",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0


    ),

    Verse(
        bookName = "Job",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Job",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Job",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Job",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Job",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Luke",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Laziness",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Song Of Solomon",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Orange",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    ),
    Verse(
        bookName = "Acts",
        chapterAndVerseNumber = "1:1",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Romance",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorised = 0

    )
)


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(state: BottomNavigationSharedStates, onEvent: (BottomNavigationScreensSharedEvents) -> Unit, pagingItems: LazyPagingItems<Verse>, contentPadding: PaddingValues){


    val corountineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val overscrollEffect = remember(corountineScope) { VerticalOverscroll(corountineScope) }





    LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),

            modifier = Modifier
//                .fillMaxSize()

                .overscroll(overscrollEffect)
                .scrollable(
                    orientation = Orientation.Vertical,
                    reverseDirection = true,
                    state = listState,
                    overscrollEffect = overscrollEffect
                ),
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


                items(

                    items =  pagingItems.itemSnapshotList,
                    key = { verse ->

                        verse?.id  ?: 0
                    }

//                    list

                ) { verse->



                    verseHolder(onEvent = onEvent, state = state , verse = verse)



                } // ITEMS ENDS


                items(

                    list

                ) { verse->



                    verseHolder(onEvent = onEvent, state = state , verse = verse)



                } // ITEMS ENDS

            } // CONTENT ENDS

        ) // LAZY COLUMN ENDS



}







@Composable
fun verseHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?){



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
                text = "${verse?.bookName}",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(start = 10.dp)

            )



            Text(
                text = "${verse?.verse}",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Text(
                text = buildAnnotatedString {

                    append("  Memorised \n")

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

                    modifier = Modifier.padding(start = 115.dp)
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
}







@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 33)
@Composable
fun GreetingPreview(
) {


    SwordTheme {

         homeScreenContent(
             state =  BottomNavigationSharedStates(),
             onEvent = {},
             pagingItems = flowOf(PagingData.from(emptyList<Verse>()))
                 .collectAsLazyPagingItems(),
             PaddingValues()

         )
    }
}



