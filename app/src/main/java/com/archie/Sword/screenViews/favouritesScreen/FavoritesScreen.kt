@file:OptIn(ExperimentalFoundationApi::class)

package com.archie.Sword.screenViews.favouritesScreen

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.homeScreenBottomNavigation.Screens
import com.archie.Sword.screenViews.themeScreen.versesLayoutWithStickHeader
import com.archie.Sword.states.BottomNavigationSharedStates

@ExperimentalMaterial3Api

data class FavoritesScreenScreenTabRow(

    val title: String = ""
)



@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun favoritesScreen(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, contentPadding: State<PaddingValues>, pagingItems: LazyPagingItems<Verse>){




    val tabRowItems = remember {

        listOf(

            FavoritesScreenScreenTabRow(
                "Favorites"
            ),

            FavoritesScreenScreenTabRow(
                "Notes"
            ),

            FavoritesScreenScreenTabRow(
                "Practiced"
            ),

            )
    }








    var selectedTabIndex by  remember {

        mutableIntStateOf(0)
    }


    val pagerState =  rememberPagerState {

        tabRowItems.size
    }


    LaunchedEffect(selectedTabIndex){

        pagerState.animateScrollToPage(selectedTabIndex)
    }


    LaunchedEffect(pagerState.currentPage,pagerState.isScrollInProgress){

        if(!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage

    }








    Column(

        modifier = Modifier.padding(contentPadding.value)
    ) {


        TabRow(
            selectedTabIndex = selectedTabIndex,
            divider = {


            }
        ) {

            tabRowItems.forEachIndexed { index, item ->


                Tab(
                    selected = index == selectedTabIndex,
                    text = { Text(
                        text = item.title,
                    ) },
                    onClick = {

                        selectedTabIndex = index
                    } // ON CLICK ENDS
                ) // TAB ENDS

            } // FOR EACH INDEX ENDS


        } // TAB ROW ENDS



        HorizontalPager(
            state =  pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->



            val modifier = Modifier.fillMaxSize()

            when(index){

                0 -> {

                    versesLayoutWithStickHeader(onEvent = onEvent, state = state , pagingItems = pagingItems , Screens.FavoritesScreen)
                }
                //1 -> versesLayoutWithStickHeader(onEvent = onEvent, state = state, pagingItems = pagingItems)
            }

        } // HORIZONTAL PAGER ENDS









    } // COLUMN ENDS


}

@Composable
fun notesHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?, isContentANote: Boolean = true){






    val context = LocalContext.current






    Card(
        modifier = Modifier
            .width(362.dp)
            .padding(top = 15.dp,),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 0.dp
        ),


        ) {

        Column {


       Column(
                Modifier.fillMaxWidth(),
            ) {

           if(!isContentANote)
            Text(
                text = verse?.verseTag ?: "" ,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(start = 10.dp)

            )



            Text(
                text = "${verse?.verse}",

                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp, top = if(isContentANote) 20.dp else 0.dp)

            )

           }






            Row(
                Modifier.align(Alignment.End)
                    .padding(end = 10.dp)

            ){


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





            }


        } // COLUMN ENDS

    } // CARD ENDS
}





@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun previewThis(){

    notesHolder(onEvent = {}, state = BottomNavigationSharedStates(), verse =  Verse(
        verseTag = "Genesis 1:1",
        verse = "In the beginning God created the heavens and the earth",
        date = System.currentTimeMillis(),
        themeName = "Creation",
        bookPosition = 0,
        photoFilePath = "",
        themeColor = "",
        note = "",
        isPartOfFavorites = 0,
        memorisedToday = 0,
        memorisedCount = 0,
        memorisedTodayDate = null

    ),

    )

}






