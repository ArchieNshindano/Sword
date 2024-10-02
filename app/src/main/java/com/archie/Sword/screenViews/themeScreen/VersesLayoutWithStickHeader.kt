@file:OptIn(ExperimentalFoundationApi::class)

package com.archie.Sword.screenViews.themeScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.enums.VerseThemes
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.homeScreen.list
import com.archie.Sword.screenViews.homeScreen.themedVerseHolder
import com.archie.Sword.screenViews.homeScreenBottomNavigation.Screens
import com.archie.Sword.states.BottomNavigationSharedStates

@Composable
fun versesLayoutWithStickHeader(onEvent: (BottomNavigationScreensSharedEvents) -> kotlin.Unit, state: BottomNavigationSharedStates, pagingItems: LazyPagingItems<Verse>, screen: Screens){

    val verses = remember {

        mutableStateOf(listOf<Verse?>())
    }


    if (screen.route == Screens.ThemeScreen.route)
        verses.value = pagingItems.itemSnapshotList

    else if(screen.route == Screens.FavoritesScreen.route)
        verses.value = pagingItems.itemSnapshotList.filter { verse ->

            verse?.isPartOfFavorites == 1
        }



    val themes =  remember {
        VerseThemes.values().toList()
    }



    val newList = remember {

        list.filter {

            it.isPartOfFavorites == 1
        }
    }

    LazyColumn(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = {


            themes.forEachIndexed{ index, theme ->

                val themeVerses = verses.value.filter { verse ->

                    verse?.themeName == theme.name
                }


                val versesWithNoTheme = verses.value.filter { verse ->

                    verse?.themeName.isNullOrBlank() || theme.name.equals("None")
                }

                val themeVersesDemo = list.filter { verse ->

                    verse.themeName == theme.name
                }

                val versesWithNoThemeDemo = list.filter { verse ->

                    verse.themeName.isNullOrBlank() || theme.name.equals("None")
                }



                val isThemedListNotEmpty  = themeVerses.isNotEmpty() || themeVersesDemo.isNotEmpty()

                val isNoThemeListNotEmpty = versesWithNoTheme.isNotEmpty() || versesWithNoThemeDemo.isNotEmpty()


                Log.d(theme.name, "themed: ${themeVerses.isNotEmpty()} D ${themeVersesDemo.isNotEmpty()} noTheme: ${versesWithNoTheme.isNotEmpty()} D ${versesWithNoThemeDemo.isNotEmpty()}")



                stickyHeader {



                    if( isThemedListNotEmpty && (theme.name != "None") )
                        stickyHeader(title = theme.name)

                    if( isNoThemeListNotEmpty && (theme.name == "None") )
                        stickyHeader(title = theme.name)


                } // STICKY HEADER ENDS

                items( items = themeVerses){ verse ->

                    themedVerseHolder(onEvent = onEvent, state = state, verse = verse)

                }


                items(
                    themeVersesDemo
                ){ verse ->

                    themedVerseHolder(onEvent = onEvent, state = state, verse = verse)



                }






            }




        }

    )


}


