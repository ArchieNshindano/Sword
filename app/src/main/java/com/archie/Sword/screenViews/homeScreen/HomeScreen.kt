@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreen

import CustomComponent
import android.content.Intent
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
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
import com.archie.Sword.enums.Sentences
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.helperFunctions.SwipeToDeleteContainer
import com.archie.Sword.helperFunctions.VerticalOverscroll
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates

import com.example.Sword.ui.theme.SwordTheme
import kotlinx.coroutines.flow.flowOf

import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

//viewModel: BottomNavigationSharedViewModel,state: BottomNavigationSharedStates,pagingData: T,event: BottomNavigationScreensSharedEvents,context: Context

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)



//val list: List<Verse> = emptyList()
val list: List<Verse> = listOf(

    Verse(
        verseTag = "Matthew",
        verse = "hjjhjhrfrjjdndnjnjnv n  ssv",
        date = System.currentTimeMillis(),
        themeName = "Love",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 1,
        memorisedToday = 0,
        memorisedCount = 0,
        memorisedTodayDate = null


    ),

    Verse(
        verseTag = "Job 1:3",
        verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
        date = System.currentTimeMillis(),
        themeName = "Trust",
        bookPosition = 1,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorisedToday = 0,
        memorisedCount = 0,
        memorisedTodayDate = null

    ),

    Verse(
    verseTag = "Job 1:6",
    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
    date = System.currentTimeMillis(),
    themeName = "Glory",
    bookPosition = 1,
    photoFilePath = "",
    themeColor = "",
    note = "",
    isPartOfFavorites = 0,
    memorisedToday = 0,
    memorisedCount = 0,
    memorisedTodayDate = null
),
Verse(
    verseTag = "Job 1:8",
    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
    date = System.currentTimeMillis(),
    themeName = "Romance",
    bookPosition = 1,
    photoFilePath = "",
    themeColor = "",
    note = "",
    isPartOfFavorites = 1,
    memorisedToday = 0,
    memorisedCount = 0,
    memorisedTodayDate = null
),
Verse(
    verseTag = "Job 1:9",
    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
    date = System.currentTimeMillis(),
    themeName = "Trust",
    bookPosition = 1,
    photoFilePath = "",
    themeColor = "",
    note = "",
    isPartOfFavorites = 0,
    memorisedToday = 0,
    memorisedCount = 0,
    memorisedTodayDate = null
),
Verse(
    verseTag = "Job 1:10",
    verse = "hjjhjhrfrjjdndnjnjnv n  sbkbskdc ssv",
    date = System.currentTimeMillis(),
    themeName = "Wisdom",
    bookPosition = 1,
    photoFilePath = "",
    themeColor = "",
    note = "",
    isPartOfFavorites = 1,
    memorisedToday = 0,
    memorisedCount = 0,
    memorisedTodayDate = null
),
)


@ExperimentalFoundationApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(state: BottomNavigationSharedStates, onEvent: (BottomNavigationScreensSharedEvents) -> Unit, pagingItems: LazyPagingItems<Verse>, contentPadding: State<PaddingValues>){





    val corountineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val overscrollEffect = remember(corountineScope) { VerticalOverscroll(corountineScope) }



    val randomSentence = remember {

         Sentences.values().random()

    }

    val sentenceComposableValue = remember {

        Random.nextInt(0,4)

    }






   Column(

       modifier = Modifier.padding(contentPadding.value)
   ) {


       LazyColumn(
           state = listState,
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(15.dp),

           modifier = Modifier
                .fillMaxSize()
               .overscroll(overscrollEffect)
               .scrollable(
                   orientation = Orientation.Vertical,
                   reverseDirection = true,
                   state = listState,
                   overscrollEffect = overscrollEffect
               ),

           content = {


               item {


                   Card(
                       modifier = Modifier
                           .width(362.dp)
                           .height(200.dp),

                       colors = CardDefaults.cardColors(
                           MaterialTheme.colorScheme.background,
                       ),

                       elevation = CardDefaults.cardElevation(5.dp)
                   ) {


                       Row(
                           modifier = Modifier.fillMaxWidth(),
                       ) {


                           Column {

                               Text(
                                   text = "Rooky",
                                   fontSize = 20.sp,
                                   fontWeight = FontWeight.Bold,
                                   modifier = Modifier
                                       .align(Alignment.CenterHorizontally)
                                       .padding(top = 20.dp)
                                   //.padding(end = 40.dp)
                               )

                               CustomComponent(
                                   canvasSize = 150.dp,
                                   backgroundIndicatorStrokeWidth = 30f,
                                   foregroundIndicatorStrokeWidth = 60f,
                                   smallText = "Memorised",
                                   bigTextSuffix = "/100",
                                   indicatorValue = 50,
                                   maxIndicatorValue = 100,

                                   )


                           }


                           DynamicSentence(
                               mainText = randomSentence.secondaryText,
                               mainWord = randomSentence.primaryText,
                               sentenceFormat = sentenceComposableValue
                           )

//                            Column(
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//
//
//
//
//                            }

                       }
                   }
               }







               items(

                   items = pagingItems.itemSnapshotList,
                   key = { verse ->
                       "${verse?.verseTag}  ${verse?.id}"
                   }


               ) { verse ->


                   if (state.isSwipeToDeleteEnabled) {

                       SwipeToDeleteContainer(
                           item = verse,
                           onEvent = onEvent,
                       ) {


                           themedVerseHolder(onEvent = onEvent, state = state, verse = verse)


                       }

                       BackHandler {

                           onEvent(BottomNavigationScreensSharedEvents.IsSwipeToDeleteEnabled(false))
                       }


                   } else
                       themedVerseHolder(onEvent = onEvent, state = state, verse = verse)


               } // ITEMS ENDS


               items(

                   list.size

               ) { index ->


                   themedVerseHolder(onEvent = onEvent, state = state, verse = list[index])


               } // ITEMS ENDS

           } // CONTENT ENDS

       ) // LAZY COLUMN ENDS

   } // COLUMN ENDS



}







@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 27)
@Composable
fun GreetingPreview(
) {


    SwordTheme(verseTheme = "Trust") {

         homeScreenContent(
             state =  BottomNavigationSharedStates(),
             onEvent = {},
             pagingItems = flowOf(PagingData.from(emptyList<Verse>()))
                 .collectAsLazyPagingItems(),
             rememberUpdatedState(newValue = PaddingValues())

         )
    }
}



