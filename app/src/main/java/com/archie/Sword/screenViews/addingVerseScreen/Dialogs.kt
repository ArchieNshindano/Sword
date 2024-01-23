package com.archie.Sword.screenViews.addingVerseScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.archie.Sword.enums.Books
import com.archie.Sword.states.AddingVerseScreenStates
import com.archie.Sword.viewModels.AddingVerseScreenViewModel


@Composable
fun bookSelectionDialog(viewModel: AddingVerseScreenViewModel){


    val books = remember {

        Books.values().toList()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Book",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {

                        items(books){book->


                            Text(
                                text = book.bookName,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(

                                        onClick = {

                                            viewModel.setBookName(book.bookName)
                                            viewModel.hideBookSelectionDialog()
                                            viewModel.showChapterSelectionDialog()



                                        }
                                    ),
                                textAlign =  TextAlign.Center


                            )



                        }

                    }
                )

            }


        }

    }






}














@Composable
fun chapterSelectionDialog(viewModel: AddingVerseScreenViewModel,state: AddingVerseScreenStates){


    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Chapter",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {



                        repeat(150) { index ->

                            item {

                                val chapter = index+1

                                Text(
                                    text = "${state.bookName} $chapter",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(

                                            onClick = {


                                                viewModel.setChapter( chapter.toString() )
                                                viewModel.hideChapterSelectionDialog()
                                                viewModel.showVerseSelectionDialog()


                                            } // on click ends
                                        ),
                                    textAlign = TextAlign.Center

                                )

                            } // item ends

                        } // repeat ends





                    } // Content ends
                ) // Lazy column ends

            } // Card ends


        } // Dialogs ends

    } //  Column ends


}










@Composable
fun verseSelectionDialog(viewModel: AddingVerseScreenViewModel,state: AddingVerseScreenStates){


    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Verse",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {



                        repeat(150) { index ->

                            item {

                                val verse = index+1

                                Text(
                                    text = "${state.bookName} ${state.chapter}:$verse",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(

                                            onClick = {

                                                viewModel.setVerseNumber( verse.toString() )
                                                viewModel.hideVerseSelectionDialog()




                                            } // on click ends
                                        ),
                                    textAlign = TextAlign.Center

                                )

                            } // item ends

                        } // repeat ends





                    } // Content ends
                ) // Lazy column ends

            } // Card ends


        } // Dialogs ends

    } //  Column ends


}















@Composable
fun experimentalBookSelectionDialog(){


    val books = remember {

        Books.values().toList()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Book",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {

                        items(books){book->


                            Text(
                                text = book.bookName,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(

                                        onClick = {


                                        }
                                    ),
                                textAlign =  TextAlign.Center


                            )



                        }

                    }
                )

            }


        }

    }






}














@Composable
fun experimentalChapterSelectionDialog(){


    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Chapter",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {



                        repeat(150) { index ->

                            item {
                                Text(
                                    text = "Genesis ${index + 1}",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(

                                            onClick = {


                                            } // on click ends
                                        ),
                                    textAlign = TextAlign.Center

                                )

                            } // item ends

                        } // repeat ends





                    } // Content ends
                ) // Lazy column ends

            } // Card ends


        } // Dialogs ends

    } //  Column ends


}










@Composable
fun experimentalVerseSelectionDialog(){


    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        Dialog(
            onDismissRequest = { /*TODO*/ },
        ){


            Card(
                modifier = Modifier.size(300.dp)
            ){


                Text(
                    text ="Verse",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )


                Divider()


                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    content = {



                        repeat(150) { index ->

                            item {
                                Text(
                                    text = "Genesis 1:${index+1}",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(

                                            onClick = {


                                            } // on click ends
                                        ),
                                    textAlign = TextAlign.Center

                                )

                            } // item ends

                        } // repeat ends





                    } // Content ends
                ) // Lazy column ends

            } // Card ends


        } // Dialogs ends

    } //  Column ends


}
















@Preview(showBackground = true)
@Composable
fun GreetinggPreview() {


}