@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.themeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.archie.Sword.screenViews.homeScreenBottomNavigation.Screens
import com.archie.Sword.states.BottomNavigationSharedStates
import kotlinx.coroutines.flow.flowOf


data class ThemeScreenTabRow(

    val title: String = ""
)


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ThemeScreen(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, contentPadding: State<PaddingValues>, pagingItems: LazyPagingItems<Verse>){




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

        modifier = Modifier.padding(contentPadding.value)
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

                0 -> themeGridScreenTabItem(onEvent)
                1 -> versesLayoutWithStickHeader(onEvent = onEvent, state = state, pagingItems = pagingItems, screen = Screens.ThemeScreen)
            }

        } // HORIZONTAL PAGER ENDS









    } // COLUMN ENDS

        }


@Composable
fun themeGridScreenTabItem(onEvent: (BottomNavigationScreensSharedEvents) -> Unit) {

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

                            shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp),
                            onClick = {

                                if(theme.name != "None")
                                onEvent(BottomNavigationScreensSharedEvents.UpdateUiThemeTo(theme.name))

                            }

                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//
//                            ) {
//
//                                Column(modifier = Modifier.fillMaxSize()) {
//
//                                    Text(
//                                        text = "",
//                                        modifier = Modifier
//                                            .align(Alignment.CenterHorizontally)
//                                            .padding(top = 40.dp),
//                                        fontSize = 20.sp,
//                                        fontWeight = FontWeight.Bold
//
//                                    ) // TEXT ENDS
//
//                                } // COLUMN ENDS
//
//                            } // BOX ENDS

                            Image(
                                painter = painterResource(id = theme.pictureId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Text(
                            text = theme.name,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    brush = Brush.verticalGradient(theme.color),
                                    shape = RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp)
                                )
                                .width(150.dp)
                                ,
                            fontSize = 20.sp
                        )
                    }

                }

            }

        }


    )


}












@Preview(showBackground = true, wallpaper = Wallpapers.NONE, showSystemUi = true, apiLevel = 33)
@Composable
fun showThis(){


   ThemeScreen(
       onEvent = {},
       state = BottomNavigationSharedStates(),
       contentPadding = rememberUpdatedState(newValue = PaddingValues()),
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






