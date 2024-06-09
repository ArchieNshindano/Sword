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

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.archie.Sword.enums.VerseThemes
import com.archie.Sword.events.AddingVerseScreenEvents
import com.archie.Sword.helperFunctions.checkLengthThenReduceSize
import com.archie.Sword.states.AddingVerseScreenStates
import com.archie.Sword.viewModels.AddingVerseScreenViewModel


@Composable
fun addingVerseScreenLaunched(viewModel: AddingVerseScreenViewModel, state: AddingVerseScreenStates){





  //  eventHandler(state = state, viewModel = viewModel )
    addingVerseScreen(onEvent = viewModel::onEvent, state = state)

}



@Composable
fun addingVerseScreen(onEvent:(AddingVerseScreenEvents) -> Unit, state: AddingVerseScreenStates) {










    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {


        if (state.isBookSelectionDialogShowing)
            bookSelectionDialog(onEvent)

        if (state.isChapterSelectionDialogShowing)
            chapterSelectionDialog(onEvent, state = state)

        if (state.isVerseSelectionDialogShowing)
            verseSelectionDialog(onEvent, state = state)


        LazyColumn(

            contentPadding = it,


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


                    items(themes) { theme->


                        Column(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clickable {


                                    if(state.themeName == theme.name)
                                        onEvent( AddingVerseScreenEvents.SetThemeName("None", false) )

                                    else
                                        onEvent(AddingVerseScreenEvents.SetThemeName(theme.name, true))





//                                    if(state.isAThemeSelected)
//                                       onEvent(AddingVerseScreenEvents.SetThemeName("", false))
//
//                                    else
//                                        onEvent(AddingVerseScreenEvents.SetThemeName(theme.name, true))

                                }
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

                                shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)

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
                                        shape = RoundedCornerShape(
                                            bottomEnd = 5.dp,
                                            bottomStart = 5.dp
                                        )
                                    )
                                    .width(150.dp)
                                ,
                                fontSize = 20.sp
                            )

                            }

                    } // ITEMS ENDS


                }, // CONTENT ENDS


            ) // LAZY ROW ENDS


        }








    } // COULUMN1 ENDS












}






@Composable
fun bottomScreen(onEvent:(AddingVerseScreenEvents) -> Unit, state: AddingVerseScreenStates) {

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {




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




            TextField(
                value = state.note,
                onValueChange = {

                                onEvent( AddingVerseScreenEvents.SetNote(it) )
                },
                label = {

                    Text(text = "What would you want to say or add?")
                },
               colors = TextFieldDefaults.colors().copy(

                     focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                   focusedContainerColor = Color.Transparent,
                   unfocusedContainerColor = Color.Transparent,

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

                    onEvent(AddingVerseScreenEvents.saveVerse)

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




