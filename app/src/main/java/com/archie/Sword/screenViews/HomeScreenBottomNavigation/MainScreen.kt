package com.archie.Sword.screenViews.HomeScreenBottomNavigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.screenViews.homeScreen.TopAppBarIcon
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.AddingVerseActivity


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
fun mainScreen(context: Context,navController: NavHostController,state: BottomNavigationSharedStates,onEvent:(BottomNavigationScreensSharedEvents) -> Unit){


    var selectedIndex by rememberSaveable {

        mutableStateOf(0)

    } // SELECTED INDEX ENDS






    val listOfItems = listOf<BottomNavigationItem>(

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
            screenId = Screens.NotesScreen.route
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




    val topAppBarIcons = listOf<TopAppBarIcon>(

        TopAppBarIcon(
            icon = Icons.Default.Search,
            description = "search"
        ),
        TopAppBarIcon(
            icon = Icons.Default.MoreVert,
            description = "more"
        ),


        ) // LIST ENDS


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())




    Scaffold(

        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

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
                                onEvent(BottomNavigationScreensSharedEvents.ShowPopUpMenu)

                        }
                        ) {

                            Icon(
                                imageVector = iconInfo.icon,
                                contentDescription = iconInfo.description,
                                modifier = Modifier.size(28.dp))

                        }


                    }


                    DropdownMenu(
                        expanded = state.isPopupMenuShowing,
                        onDismissRequest = {  onEvent(BottomNavigationScreensSharedEvents.HidePopUpMenu) },
//                        modifier = Modifier.width(100.dp)
                    ) {

                        DropdownMenuItem(text = { Text(text = "Hello") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "Hello") }, onClick = { /*TODO*/ })

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

          Navigation(
              context = context,
              contentPadding = padding,
              navController = navController
          )

    } // SCAFFOLD ENDS




}