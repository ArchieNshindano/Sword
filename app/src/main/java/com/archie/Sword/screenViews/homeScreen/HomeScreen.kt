@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreen

import android.content.Intent
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
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
import com.archie.Sword.helperFunctions.SwipeToDeleteContainer
import com.archie.Sword.helperFunctions.VerticalOverscroll
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.R
import com.example.Sword.ui.theme.SwordTheme
import kotlinx.coroutines.flow.flowOf

import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import java.text.SimpleDateFormat
import java.util.Date

//viewModel: BottomNavigationSharedViewModel,state: BottomNavigationSharedStates,pagingData: T,event: BottomNavigationScreensSharedEvents,context: Context

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)



val list: List<Verse> = emptyList()
//val list: List<Verse> = listOf(
//
//    Verse(
//        verseTag = "Matthew",
//        verse = "hjjhjhrfrjjdndnjnjnv n  ssv",
//        date = System.currentTimeMillis(),
//        themeName = "Love",
//        bookPosition = 1,
//        photoFilePath = "",
//        themeColor = "",
//        note = "",
//        isPartOfFavorites = 1,
//        memorisedToday = 0,
//        memorisedCount = 0,
//        memorisedTodayDate = null
//
//
//    ),
//
//    Verse(
//        verseTag = "Job 1:3",
//        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
//        date = System.currentTimeMillis(),
//        themeName = "Trust",
//        bookPosition = 1,
//        photoFilePath = "",
//        themeColor = "",
//        note = "",
//        isPartOfFavorites = 0,
//        memorisedToday = 0,
//        memorisedCount = 0,
//        memorisedTodayDate = null
//
//    ),
//
//    Verse(
//    verseTag = "Job 1:6",
//    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
//    date = System.currentTimeMillis(),
//    themeName = "Glory",
//    bookPosition = 1,
//    photoFilePath = "",
//    themeColor = "",
//    note = "",
//    isPartOfFavorites = 0,
//    memorisedToday = 0,
//    memorisedCount = 0,
//    memorisedTodayDate = null
//),
//Verse(
//    verseTag = "Job 1:8",
//    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
//    date = System.currentTimeMillis(),
//    themeName = "Romance",
//    bookPosition = 1,
//    photoFilePath = "",
//    themeColor = "",
//    note = "",
//    isPartOfFavorites = 1,
//    memorisedToday = 0,
//    memorisedCount = 0,
//    memorisedTodayDate = null
//),
//Verse(
//    verseTag = "Job 1:9",
//    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
//    date = System.currentTimeMillis(),
//    themeName = "Trust",
//    bookPosition = 1,
//    photoFilePath = "",
//    themeColor = "",
//    note = "",
//    isPartOfFavorites = 0,
//    memorisedToday = 0,
//    memorisedCount = 0,
//    memorisedTodayDate = null
//),
//Verse(
//    verseTag = "Job 1:10",
//    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
//    date = System.currentTimeMillis(),
//    themeName = "Wisdom",
//    bookPosition = 1,
//    photoFilePath = "",
//    themeColor = "",
//    note = "",
//    isPartOfFavorites = 1,
//    memorisedToday = 0,
//    memorisedCount = 0,
//    memorisedTodayDate = null
//),
//)


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(state: BottomNavigationSharedStates, onEvent: (BottomNavigationScreensSharedEvents) -> Unit, pagingItems: LazyPagingItems<Verse>, contentPadding: State<PaddingValues>){





    val corountineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val overscrollEffect = remember(corountineScope) { VerticalOverscroll(corountineScope) }






    LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),

            modifier = Modifier
//                .fillMaxSize()

                .overscroll(overscrollEffect)
                .scrollable(
                    orientation = Orientation.Vertical,
                    reverseDirection = true,
                    state = listState,
                    overscrollEffect = overscrollEffect
                ),
            contentPadding = contentPadding.value,

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

                    items = pagingItems.itemSnapshotList,
                    key = { verse ->
                        "${verse?.verseTag}  ${verse?.id}"
                    }


                ) { verse->



                 if(state.isSwipeToDeleteEnabled) {

                     SwipeToDeleteContainer(
                         item = verse,
                         onEvent = onEvent,
                     ) {


                            themedVerseHolder(onEvent = onEvent, state = state, verse = verse)


                     }

                     BackHandler {

                             onEvent(BottomNavigationScreensSharedEvents.IsSwipeToDeleteEnabled(false))
                     }


                 }

                 else
                        themedVerseHolder(onEvent = onEvent, state = state, verse = verse)


                } // ITEMS ENDS


                items(

                    list.size

                ) { index ->



                    themedVerseHolder(onEvent = onEvent, state = state, verse = list[index])


                } // ITEMS ENDS

            } // CONTENT ENDS

        ) // LAZY COLUMN ENDS



}


@Composable
fun themedVerseHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?){

    SwordTheme(verseTheme = verse?.themeName ?: "", isContainerVerseHolder = true) {

        verseHolder(onEvent = onEvent, state = state, verse = verse)

    }
}



@Composable
fun verseHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?){


    val verseTag = verse?.verseTag ?: ""










    val context = LocalContext.current

    val checkBoxState = remember {

        mutableStateOf(false)
    }

    val currentDate = remember {

        LocalDate.now()
    }


    LaunchedEffect(key1 = verse?.memorisedToday, key2 = verseTag,key3 = {"${currentDate.dayOfMonth} ${currentDate.monthValue} ${currentDate.year}"} ) {


        if(verse?.memorisedToday == 1 && !verse.memorisedTodayDate.isNullOrBlank()){


            val from = LocalDate.parse(verse.memorisedTodayDate,org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy-MM-dd") )

            // calculate the period between those two
            val period = Period.between(from, currentDate)
            // Get the current date

            Log.d("Dates", "${verse.verseTag}  Today: ${currentDate}  Yesterday: ${from} ${period.days}")

            if (period.days == 1) {
                // If the difference is 1 day or more, reset memorisedToday to 0

                var memorised = verse.memorisedCount

                onEvent(
                    BottomNavigationScreensSharedEvents.UpdateVerse( verse.copy(memorisedToday = 0, memorisedCount = ++memorised) )
                )
            }

        }


        checkBoxState.value = verse?.memorisedToday == 1


    }



    val haptics = LocalHapticFeedback.current








    Card(
        modifier = Modifier
            .width(362.dp)
            .padding(top = 15.dp,)
            .pointerInput(Unit) {

                detectTapGestures(
                    onLongPress = {

                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)

                        onEvent(BottomNavigationScreensSharedEvents.IsSwipeToDeleteEnabled(true))

                    }
                )

            },// A
        // .height(100.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 0.dp
        ),

        onClick = {

            onEvent(BottomNavigationScreensSharedEvents.UpdateUiThemeTo(verse?.themeName ?: ""))
        }

    ) {

        Column {

            Text(
                text = verseTag ,
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

                        append("${verse?.memorisedCount} times")


                    }


                },

                modifier = Modifier.align(Alignment.End)

            )











            Row(
                modifier = Modifier.fillMaxWidth()
            ) {



                    IconButton(
                        onClick = {


                            var isPartOfFavorites = 0



                            if(verse?.isPartOfFavorites == 1)
                                isPartOfFavorites = 0

                            else
                                isPartOfFavorites = 1




                            if(verse != null) {


                                onEvent(
                                    BottomNavigationScreensSharedEvents.UpdateVerse(

                                        verse.copy(isPartOfFavorites = isPartOfFavorites)

                                    )

                                )

                            }



                        }
                    ) {

                        Icon(
                            imageVector = if (verse?.isPartOfFavorites == 1) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorites"
                        )

                    }


                IconButton(

                    onClick = {

                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${verse?.verseTag} \n ${verse?.verse}")
                            putExtra(Intent.EXTRA_TITLE, "Share Verse")
                            type = "text/plain"

                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }

                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )

                }


                Row(

                    modifier = Modifier.padding(start = 115.dp)

                ) {

                    Text(
                        text = "Memorise Today",
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )



                        Checkbox(
                            checked = checkBoxState.value,
                            onCheckedChange = { isCheckBoxTicked ->

                                var memorisedToday = 0
                                var memorisedTodayDate = verse?.memorisedTodayDate

                                if (isCheckBoxTicked) {

                                    memorisedToday = 1
                                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

                                    memorisedTodayDate = simpleDateFormat.format(Date())

                                }



                                if( verse != null) {
                                  onEvent(
                                     BottomNavigationScreensSharedEvents.UpdateVerse(

                                       verse.copy(memorisedToday= memorisedToday, memorisedTodayDate = memorisedTodayDate)


                                   ) // B

                               )

                           }
                            }

                        )











                }


            }


        } // COLUMN ENDS

    } // CARD ENDS
}











@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 27)
@Composable
fun GreetingPreview(
) {


    SwordTheme(verseTheme = "Love") {

         homeScreenContent(
             state =  BottomNavigationSharedStates(),
             onEvent = {},
             pagingItems = flowOf(PagingData.from(emptyList<Verse>()))
                 .collectAsLazyPagingItems(),
             rememberUpdatedState(newValue = PaddingValues())

         )
    }
}



