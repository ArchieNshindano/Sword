@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.archie.Sword.screenViews.addingVerseScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.archie.Sword.enums.ColorStrokes
import com.archie.Sword.enums.Colors
import com.archie.Sword.enums.VerseThemes
import com.archie.Sword.events.AddingVerseScreenEvents
import com.archie.Sword.helperFunctions.checkLengthThenReduceSize
import com.archie.Sword.states.AddingVerseScreenStates
import com.archie.Sword.viewModels.AddingVerseScreenViewModel
import com.example.Sword.ui.theme.SwordTheme




@Composable
fun addingVerseScreenLaunched(viewModel: AddingVerseScreenViewModel, state: AddingVerseScreenStates){





  //  eventHandler(state = state, viewModel = viewModel )
    addingVerseScreen(onEvent = viewModel::onEvent, state = state)

}



@Composable
fun addingVerseScreen(onEvent:(AddingVerseScreenEvents) -> Unit, state: AddingVerseScreenStates) {


    val themes = remember {

        VerseThemes.values().toList()
    }


    val colors = remember {

        ColorStrokes.values().toList()

    }






    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {


        if (state.isBookSelectionDialogShowing)
            bookSelectionDialog(onEvent)

        if (state.isChapterSelectionDialogShowing)
            chapterSelectionDialog(onEvent, state = state)

        if (state.isVerseSelectionDialogShowing)
            verseSelectionDialog(onEvent, state = state)


        LazyColumn(

            contentPadding = it,
            modifier = Modifier.background(color = Color.White),


            content = {


                item {


                    topScreen(onEvent, state)


                }




                item {


                    bottomScreen(onEvent, state)


                } // ITEM ENDS


            },
            verticalArrangement = Arrangement.spacedBy(20.dp),


            )


    }


}





@Composable
fun topScreen(onEvent:(AddingVerseScreenEvents) -> Unit, state: AddingVerseScreenStates) {



    val themes = remember {

        VerseThemes.values().toList()
    }





    Column(

        modifier = Modifier.fillMaxWidth()

                ) {


        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 100.dp)
        ) {


            Text(

                text = "Book(Tap)",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 3.dp, start = 2.dp)

            ) // TEXT ENDS


            Card(
                onClick = {

                          onEvent(AddingVerseScreenEvents.ShowBookSelectionDialog)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(width = 150.dp, height = 100.dp)
                /* .border(width = 0.5.dp, colorLevelValues = Color.Black, shape = RoundedCornerShape(20.dp)) */,
                elevation = CardDefaults.cardElevation(

                    hoveredElevation = 0.dp
                ),

                colors = CardDefaults.cardColors(

                    containerColor = Colors.milkyWhite.color
                )

            ) {


                Column(modifier = Modifier.fillMaxSize()) {

                    Text(
                        text = state.bookName.checkLengthThenReduceSize,
                        fontSize = 30.sp,
                        fontWeight = Bold,
                        modifier = Modifier.padding(start = 5.dp)
                    ) // TEXT ENDS


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)

                    ) {


                        Text(
                            text = "${state.chapter}:${state.verseNumber}",
                            fontSize = 25.sp,
                            fontWeight = Bold,
                            modifier = Modifier
                                .weight(3f)
                                .padding(start = 10.dp)
                        ) // TEXT ENDS


                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editable",
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 5.dp)

                        ) // ICON ENDS


                    } // ROW ENDS


                } // SUB COLUMN 2 ENDS


            } // CARD WNDS


        } // SUB COLUMN 1 ENDS


        OutlinedTextField(

            label = {

                Text(
                    text = "Verse",
                    fontSize = 20.sp
                )

            },
            value = state.verse,
            onValueChange = { verse ->

                onEvent(AddingVerseScreenEvents.SetVerse(verse))

            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
                .width(350.dp),

            maxLines = 9

        )






        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Theme",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)

            )



            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                content = {


                    items(themes) { theme ->

                        Card(
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .size(width = 105.dp, height = 105.dp),
                            elevation = CardDefaults.cardElevation(


                                defaultElevation = 1.dp
                            ),
                            colors = CardDefaults.cardColors(

                                containerColor = Color.White
                            ),
                            onClick = {

                                onEvent(AddingVerseScreenEvents.SetThemeName(theme.name))
                            }
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
                                        text = theme.name,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 40.dp),
                                        fontSize = 20.sp,
                                        fontWeight = Bold

                                    ) // TEXT ENDS

                                } // COLUMN ENDS

                            } // BOX ENDS


                        } // CARD WNDS


                    } // ITEMS ENDS


                }, // CONTENT ENDS


            ) // LAZY ROW ENDS


        }








    } // COULUMN1 ENDS












}






@Composable
fun bottomScreen(onEvent:(AddingVerseScreenEvents) -> Unit, state: AddingVerseScreenStates) {

    val context = LocalContext.current


    val colors = remember {

        ColorStrokes.values().toList()

    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

//        Column(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .width(370.dp)
//                //  .padding(to = 180.dp)
//                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
//                .padding(top = 10.dp)
//                .background(color = Colors.milkyWhite.color)
//        ) {
//
//            Text(
//                text = "New Theme",
//                fontSize = 20.sp,
//                modifier = Modifier
//                    .padding(start = 10.dp, top = 10.dp),
//            //    style = TextStyle(brush = Brush.horizontalGradient(ColorStrokes.red.color.reversed()))
//
//            )
//
//            OutlinedTextField(
//                value = state.themeName ,
//                onValueChange = {
//
//                                onEvent( AddingVerseScreenEvents.SetThemeName(it) )
//                },
//                label = {
//                    Text(text = "Name Of Theme")
//
//                },
//                modifier = Modifier
//                    .width(350.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .padding(top = 20.dp),
//                shape = RoundedCornerShape(30.dp),
//
//            ) //
//
//
//            Text(
//                text = "Color of Theme",
//                fontSize = 20.sp,
//                modifier = Modifier.padding(top = 30.dp, start = 10.dp, bottom = 15.dp)
//            )
//
//
//            LazyHorizontalGrid(
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//
//
//                rows = GridCells.Fixed(3),
//                userScrollEnabled = true,
//                modifier = Modifier
//                    .height(290.dp)
//                    .padding(10.dp)
//                    .align(Alignment.CenterHorizontally),
//                content =
//                {
//
//                    items(colors) { color ->
//
//
//                        Column {
//
//
//                            Text(text = color.name)
//
//                            Card(
//
//                                shape = CircleShape,
//                                colors = CardDefaults.cardColors(
//
//                                    containerColor = Color.White
//                                ),
//                                modifier = Modifier
//                                    .size(100.dp)
//                                    .padding(end = 30.dp),
//
//                                onClick = {
//
//                                    onEvent( AddingVerseScreenEvents.SetThemeColor(color.name) )
//                                }
//
//
//                                ) {
//
//
//                                Box(
//                                    modifier = Modifier
//                                        .background(
//
//                                            brush = Brush.verticalGradient(
//                                                color.color.reversed()
//                                            ),
//                                        )
//                                        .fillMaxSize()
//
//
//                                ) {}
//
//
//                            } // CARD ENDS
//
//                        }
//
//
//                    } // ITEMS ENDS
//
//
//                } // CONTENT ENDS
//
//
//            ) // GRID ENDS
//
//
//        } // 1 SUB COLUMN ENDS
//


        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp)
        ) {

            Text(
                text = "Add a Note",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

//            BasicTextField(
//                value = state.verse,
//                onValueChange = {},
//                maxLines = 8,
//                modifier = Modifier.border(width = 1.dp, brush = Brush.horizontalGradient(VerseThemes.Laziness.colorLevelValues), shape = RoundedCornerShape(5.dp)),
//                )



            TextField(
                value = state.note,
                onValueChange = {

                                onEvent( AddingVerseScreenEvents.SetNote(it) )
                },
                label = {

                    Text(text = "What would you want to say or add?")
                },
               colors = TextFieldDefaults.textFieldColors(

                   containerColor = Color.Transparent,
                   focusedIndicatorColor = Color.Transparent,
                   unfocusedIndicatorColor = Color.Transparent,
                   disabledIndicatorColor = Color.Transparent
               ),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .width(350.dp),

                maxLines = 9


            )


        }


            OutlinedButton(

                onClick = {


                    Log.d("Button", "PRESSED")

                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp, bottom = 15.dp)
                    .width(100.dp)

            ) {

                Text(text = "Save")

            }


        } // COLUMN ENDS


    }





@Preview(showBackground = true, apiLevel = 33)
@Composable
fun hello() {


        addingVerseScreen(onEvent = {}, state = AddingVerseScreenStates() )

    }




