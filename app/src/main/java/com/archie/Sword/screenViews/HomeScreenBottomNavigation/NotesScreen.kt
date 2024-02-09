@file:OptIn(ExperimentalMaterial3Api::class)

package com.archie.Sword.screenViews.HomeScreenBottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.states.HomeScreenStates
import com.example.Sword.ui.theme.SwordTheme


@ExperimentalMaterial3Api
@Composable
fun NotesScreen(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, states: HomeScreenStates){

    val list: List<String> = listOf("hvhdvbjhbhj","jhbhbhbb ehbeeuue","hshuibbwfhbhrfbhb","khjkbkbbb")




    LazyColumn(

            verticalArrangement = Arrangement.spacedBy(3.dp),
            content = {


                      items(list){ item ->

                          Card(
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(start = 5.dp, end = 5.dp)
                          ){

                              Text(text = item)
                          } // CARD ENDS

                      } // ITEMS ENDS


        }, // CONTENT ENDS

        modifier = Modifier.background(color = Color.Blue)
            .fillMaxSize()

        )


}








@Composable
fun experimentalNotesScreen(){

    val list: List<String> = listOf("hvhdvbjhbhj","jhbhbhbb ehbeeuue","hshuibbwfhbhrfbhb","khjkbkbbb")


    Scaffold(

        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ }

            ) {

                Icon(imageVector = Icons.Default.Add, contentDescription = "Add more")


            }

        }

    ) {


        LazyColumn(

            verticalArrangement = Arrangement.spacedBy(3.dp),
            content = {


                items(list){ item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp)
                    ){

                        Text(text = item)
                    } // CARD ENDS

                } // ITEMS ENDS


            }, // CONTENT ENDS
            contentPadding = it

        )


    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SwordTheme {
        experimentalNotesScreen()

    }
}


