@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreenBottomNavigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.enums.VerseThemes
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.homeScreen.list
import com.archie.Sword.screenViews.homeScreen.verseHolder
import com.archie.Sword.states.BottomNavigationSharedStates
import kotlinx.coroutines.flow.flowOf


data class ThemeScreenTabRow(

    val title: String = ""
)


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThemeScreen(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, paddingValues: PaddingValues, pagingItems: LazyPagingItems<Verse>){




    val contentPadding = remember {

        paddingValues
    }



    val tabRowItems = remember {

        listOf(

            ThemeScreenTabRow(
                "Manage"
            ),

            ThemeScreenTabRow(
                "View"
            )
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

        modifier = Modifier.padding(contentPadding)
    ) {


        androidx.compose.material3.TabRow(
            selectedTabIndex = selectedTabIndex,
            divider = {


            }
        ) {

            tabRowItems.forEachIndexed { index, item ->


                Tab(
                    selected = index == selectedTabIndex,
                    text = { Text(
                        text = item.title,
                        color = Color.Black,
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

                0 -> manageScreenTabItem()
                1 -> viewScreenTabItem(onEvent = onEvent, state = state, pagingItems = pagingItems)
            }

        } // HORIZONTAL PAGER ENDS









    } // COLUMN ENDS

        }


@Composable
fun manageScreenTabItem() {


    val list: List<String> =
        listOf("hvhdvbjhbhj", "jhbhbhbb ehbeeuue", "hshuibbwfhbhrfbhb", "khjkbkbbb")


    val themes = remember {
        VerseThemes.values().toList()

    }



    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),

        content = {


            themes.forEachIndexed {index, theme ->


                item {

                    Column(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(150.dp),
//                            .graphicsLayer {
//                                // Calculate the absolute offset for the current page from the
//                                // scroll position. We use the absolute value which allows us to mirror
//                                // any effects for both directions
//                                val pageOffset = (
//                                        (pagerState.currentPage - pageIndex) + pagerState
//                                            .currentPageOffsetFraction
//                                        ).absoluteValue
//
//                                // We animate the alpha, between 50% and 100%
//                                alpha = androidx.compose.ui.util.lerp(
//                                    start = 0.5f,
//                                    stop = 1f,
//                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                                )
//                            }

//                            shape = RectangleShape

                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(theme.colorLevelValues.reversed())
                                    )
                            ) {

                                Column(modifier = Modifier.fillMaxSize()) {

                                    Text(
                                        text = "",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 40.dp),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold

                                    ) // TEXT ENDS

                                } // COLUMN ENDS

                            } // BOX ENDS
                        }

                        Text(
                            text = theme.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 0.1.dp,
                                    brush = Brush.verticalGradient(theme.colorLevelValues),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                ,
                            fontSize = 20.sp
                        )
                    }

                }

            }

        }


    )


}







@Composable
fun viewScreenTabItem(onEvent: (BottomNavigationScreensSharedEvents) -> kotlin.Unit, state: BottomNavigationSharedStates, pagingItems: LazyPagingItems<Verse>){


    val themes =  remember {
        VerseThemes.values().toList()
    }

    LazyColumn(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {


            themes.forEachIndexed{ index, theme ->


                stickyHeader {



                    stickyHeader(title = theme.name)


                }

                items(
                   items =  pagingItems.itemSnapshotList,
//                    key = { verse ->
//
//
//
//
//                        (verse?.id   ?: 0)
//                    }

                ){ verse ->

                       if(verse?.themeName == theme.name)
                        verseHolder(onEvent = onEvent, state = state, verse = verse )




                }

                items(
                    list
                ){ verse ->

                    if(verse?.themeName == theme.name)
                        verseHolder(onEvent = onEvent, state = state, verse = verse )




                }





            }


        }

    )


}


@Composable
fun stickyHeader(title: String){

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Text(
            text = " "+ title,
            fontSize = 25.sp,
            textAlign = TextAlign.Start
        )

    }

}




@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 33)
@Composable
fun showThis(){


   ThemeScreen(
       onEvent = {},
       state = BottomNavigationSharedStates(),
       paddingValues = PaddingValues(),
       pagingItems = flowOf(PagingData.from(emptyList<Verse>()))
           .collectAsLazyPagingItems(),
   )


//  LazyColumn(
//      content = {
//
//          stickyHeader {
//
//              stickyHeader(title = "Hello")
//
//          }
//
//      },
//      modifier = Modifier.fillMaxSize())
//




}






