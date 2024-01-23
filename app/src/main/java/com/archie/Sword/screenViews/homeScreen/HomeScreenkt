@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.archie.Sword.screenViews.homeScreen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.HomeScreenStates
import com.archie.Sword.viewModels.HomeScreenViewModel
import com.example.Sword.AddingVerseActivity
import com.example.Sword.MainActivity
import com.example.Sword.ui.theme.SwordTheme



data class BottomNavigationItem(

    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null

)

//viewModel: HomeScreenViewModel,state: HomeScreenStates,pagingData: T,events: HomeScreenEvents,context: Context
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myHomeScreen(context: Context, viewModel: HomeScreenViewModel, state: HomeScreenStates, pagingItems: LazyPagingItems<Verse>){


    Log.d("Paging Items",pagingItems.itemCount.toString())


    var selectedIndex by rememberSaveable {

        mutableStateOf(0)

    } // SELECTED INDEX ENDS

    val listOfItems = listOf<BottomNavigationItem>(

        BottomNavigationItem(

            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = true
        ),

        BottomNavigationItem(

            title = "Theme",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false
        ),

        BottomNavigationItem(

            title = "Favourites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = true,
            badgeCount = 4
        )




    ) // LIST ENDS


    Row(  modifier = Modifier.fillMaxSize() ) {


        


        Scaffold(

            modifier = Modifier.fillMaxSize(),

            bottomBar =  {

                NavigationBar {

                    listOfItems.forEachIndexed{ index,item ->

                        NavigationBarItem(
                            selected = selectedIndex == index ,

                            onClick = {  selectedIndex = index},

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

                               val intentObject = Intent(context,AddingVerseActivity::class.java)
                                context.startActivity(intentObject)


                 },// onClick ENDS

//                     shape = CircleShape,
//
//                     modifier = Modifier.size(65.dp)
                 ) {

                     Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )

                 }   // Floating Action Button

            },


        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(7.dp),
                contentPadding = it ,

                content = {

                    items(pagingItems.itemCount){ index->




                        val verse = pagingItems[index]


                        Card {

                            Column {

                                Text(
                                    text = "${verse?.bookName} ${verse?.chapterAndVerseNumber}",
                                    fontSize = 30.sp

                                )

                                Text(
                                    text = verse?.verse.toString(),
                                    fontSize = 20.sp
                                )





                            } // COLUMN ENDS

                        } // CARD ENDS

                    } // ITEMS ENDS


//                    items(){ verse ->
//
//
//                        Card {
//
//                            Column {
//
//                                Text(
//                                    text = "${verse?.bookName} ${verse?.chapterAndVerseNumber}",
//                                    fontSize = 30.sp
//
//                                )
//
//                                Text(
//                                    text = verse?.verse.toString(),
//                                    fontSize = 20.sp
//                                )
//
//
//
//
//
//                            } // COLUMN ENDS
//
//                        } // CARD ENDS
//
//
//                    } // ITEM ENDS


                }  //  CONTENT ENDS



            )

        }

    } // ROW ENDS







}


@Composable
fun experimentalBottomNavigationBar(listOfItems: List<BottomNavigationItem>,selectedIndex: Int){

    NavigationBar {

        listOfItems.forEachIndexed{ index,item ->

            NavigationBarItem(
                selected = selectedIndex == index ,
                onClick = {  selectedIndex },
                icon = {

                    BadgedBox(
                        badge = {

                            if(item.badgeCount != null)
                                Badge {  Text(text = item.badgeCount.toString())  }

                            else if(item.hasNews)
                                Badge()

                        }
                    ) {

                        Icon(
                            imageVector = if (index == selectedIndex) item.selectedIcon else  item.unselectedIcon,
                            contentDescription = item.title

                        )


                    }
                }

            )



        }

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun experimentalHomeScreen(){




    var selectedIndex by rememberSaveable {

        mutableStateOf(0)

    } // SELECTED INDEX ENDS

    val listOfItems = listOf<BottomNavigationItem>(

        BottomNavigationItem(

            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = true
        ),

        BottomNavigationItem(

            title = "Theme",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false
        ),

        BottomNavigationItem(

            title = "Favourites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = true,
            badgeCount = 4
        )




    ) // LIST ENDS


    Row(  modifier = Modifier.fillMaxSize() ) {





        Scaffold(

            modifier = Modifier.fillMaxSize(),

            bottomBar =  {

                NavigationBar {

                    listOfItems.forEachIndexed{ index,item ->

                        NavigationBarItem(
                            selected = selectedIndex == index ,

                            onClick = {  selectedIndex = index},

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



                    },// onClick ENDS

//                     shape = CircleShape,
//
//                     modifier = Modifier.size(65.dp)
                ) {

                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )

                }   // Floating Action Button

            },

            content = {

                LazyColumn(contentPadding = it , content = {})
            } // CONTENT ENDS
        ) // SCAFFOLD ENDS

    } // ROW ENDS







}


@Composable
fun bottomNavigationBar(listOfItems: List<BottomNavigationItem>,selectedIndex: Int){

    NavigationBar {

        listOfItems.forEachIndexed{ index,item ->

            NavigationBarItem(
                selected = selectedIndex == index ,
                onClick = {  selectedIndex },
                icon = {

                    BadgedBox(
                        badge = {

                            if(item.badgeCount != null)
                                Badge {  Text(text = item.badgeCount.toString())  }

                            else if(item.hasNews)
                                Badge()

                        }
                    ) {

                        Icon(
                            imageVector = if (index == selectedIndex) item.selectedIcon else  item.unselectedIcon,
                            contentDescription = item.title

                        )


                    }
                }

            )



        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SwordTheme {
        experimentalHomeScreen()

    }
}



