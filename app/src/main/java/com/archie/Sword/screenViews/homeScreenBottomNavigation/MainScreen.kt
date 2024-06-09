@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.archie.Sword.screenViews.homeScreenBottomNavigation

import android.content.Intent
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.favouritesScreen.notesHolder
import com.archie.Sword.screenViews.homeScreen.TopAppBarIcon
import com.archie.Sword.screenViews.homeScreen.list
import com.archie.Sword.screenViews.homeScreen.verseHolder
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.AddingVerseActivity
import com.example.Sword.ui.theme.SwordTheme
import kotlinx.coroutines.flow.flowOf


data class BottomNavigationItem(

    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val screenId: String? = null

)

data class TopAppBarIcon(

    val icon: ImageVector,
    val description: String

)

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun mainScreen(navController: NavHostController, state: BottomNavigationSharedStates, onEvent: (BottomNavigationScreensSharedEvents) -> Unit, pagingItems: LazyPagingItems<Verse>){


    val context = LocalContext.current


    var selectedIndex by rememberSaveable {

        mutableStateOf(0)

    } // SELECTED INDEX ENDS







    val listOfItems = listOf(

        BottomNavigationItem(

            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = true,
            screenId = Screens.HomeScreen.route
        ),

        BottomNavigationItem(

            title = "Theme",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false,
            screenId = Screens.ThemeScreen.route
        ),

        BottomNavigationItem(

            title = "Favourites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = true,
            badgeCount = 4,
            screenId = Screens.FavoritesScreen.route
        )




    ) // LIST ENDS




    val topAppBarIcons = listOf(

        TopAppBarIcon(
            icon = Icons.Default.Search,
            description = "Search"
        ),
        TopAppBarIcon(
            icon = Icons.Default.MoreVert,
            description = "More"
        ),


        ) // LIST ENDS


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val query =  remember { mutableStateOf("") }
    
    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {




            if (state.isSearchBarActive)
                SearchBar(
                    query = query.value,
                    onQueryChange = {
                        query.value = it
                        onEvent(BottomNavigationScreensSharedEvents.SearchFor(query.value))
                    },
                    onSearch =  {

                        onEvent(BottomNavigationScreensSharedEvents.SearchFor(it) )
                    },
                    active = state.isSearchBarActive ,
                    onActiveChange = {

                        if(it)
                            onEvent(BottomNavigationScreensSharedEvents.ExpandSearchBar)
                        else
                            onEvent(BottomNavigationScreensSharedEvents.CollapseSearchBar)
                    },

                    placeholder = { Text(text = "Search") },

                    leadingIcon = {

                        IconButton(
                            onClick = {

                            onEvent(BottomNavigationScreensSharedEvents.CollapseSearchBar)

                        }
                        ) {

                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Close Search Bar",
                                modifier = Modifier.size(28.dp)
                            )

                        }   // ICON BUTTON

                    } ,// LEADING ICON

                    trailingIcon = {

                        IconButton(
                            onClick = {

                                query.value = ""


                        }   // ON CLICK ENDS
                        ) {

                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Search Bar",
                                modifier = Modifier.size(28.dp)
                            )

                        }   // ICON BUTTON

                    } // TRAILING ICON

                ) {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        items(
                            state.verses,
                            key = { verse -> "${verse.verseTag} ${verse.id}" }
                        ) { verse ->

                            SwordTheme(verseTheme = state.lastOpenedTheme, isContainerVerseHolder = true) {
                                
                                notesHolder(onEvent = onEvent, state = state, verse = verse, isContentANote = false )
                                
                            }

                        } // ITEMS ENDS


                    } // LAZY COLUMN ENDS

                    BackHandler {

                        onEvent(BottomNavigationScreensSharedEvents.CollapseSearchBar)
                        query.value = ""
                    }

                } // SEARCH BAR ENDS



           else
             TopAppBar(
                title = {

                     Text(
                        text = "Truth",
                        fontWeight = FontWeight.Bold,
                        fontSize = 33.sp
                    )

                },

                actions = {


                        topAppBarIcons.forEachIndexed { index, iconInfo ->


                            IconButton(onClick = {

                            if(iconInfo.description == "Search")
                                onEvent(BottomNavigationScreensSharedEvents.ExpandSearchBar)

                            }
                            ) {

                                Icon(
                                    imageVector = iconInfo.icon,
                                    contentDescription = iconInfo.description,
                                    modifier = Modifier.size(28.dp)
                                )

                            }


                        }


                        DropdownMenu(
                            expanded = false,
                            onDismissRequest = {
//                            onEvent(BottomNavigationScreensSharedEvents.HidePopUpMenu)
                            },
//                        modifier = Modifier.width(100.dp)
                        ) {

                            DropdownMenuItem(
                                text = { Text(text = "Hello") },
                                onClick = { /*TODO*/ })
                            DropdownMenuItem(
                                text = { Text(text = "Hello") },
                                onClick = { /*TODO*/ })

                        }


                },

                scrollBehavior = scrollBehavior




            )





        },

        bottomBar =  {

            NavigationBar {

                listOfItems.forEachIndexed{ index,item ->

                    NavigationBarItem(
                        selected = selectedIndex == index ,

                        onClick = {

                            selectedIndex = index
                            navController.navigate(item.screenId.toString())



                        },

                        label = {   Text(text = item.title)  },

                        icon = {

                            BadgedBox(
                                badge = {

                                    if(item.badgeCount != null)
                                        Badge {  Text(text = item.badgeCount.toString())  }

                                    else if(item.hasNews)
                                        Badge()

                                } // BADGE ENDS
                            ) {

                                Icon(
                                    imageVector = if (index == selectedIndex) item.selectedIcon else  item.unselectedIcon,
                                    contentDescription = item.title,

                                    ) // ICON ENDS


                            } // BADGED BOX
                        } // ICON

                    ) // NAVIGATION ITEM



                } // FOR EACH ITEM ENDS

            } // NAVIGATION BAR ENDS

        }, //BOTTOM BAR ENDS

        floatingActionButton = {

            FloatingActionButton(
                onClick = {

                    val intentObject = Intent(context, AddingVerseActivity::class.java)

                    intentObject.putExtra("lastOpenedTheme", state.lastOpenedTheme)
                    context.startActivity(intentObject)



                },// onClick ENDS

//                     shape = CircleShape,
//
//                     modifier = Modifier.size(65.dp)

            ) {

                Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )

            }   // Floating Action Button



        },


        ) { padding ->





        Navigation( navController = navController, paddingValues = padding , state = state, onEvent = onEvent, pagingItems = pagingItems)

    } // SCAFFOLD ENDS




}



@Preview(showBackground = true, apiLevel = 23)
@Composable
fun GreetingPreview(

) {

    SwordTheme(verseTheme = "Love", darkTheme = true) {
        mainScreen(
            navController = rememberNavController(),
            state = BottomNavigationSharedStates(isSearchBarActive = false, verses = list),
            onEvent ={} ,
            pagingItems = flowOf(PagingData.from(emptyList<Verse>()))
                .collectAsLazyPagingItems())

    }
}