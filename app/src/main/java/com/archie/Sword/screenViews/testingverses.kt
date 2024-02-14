package com.archie.Sword.screenViews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Sword.ui.theme.SwordTheme

@Composable
fun listOfVerses(){



    AlertDialog(

        onDismissRequest = { /*TODO*/ },

        confirmButton = { /*TODO*/ },

        modifier = Modifier.width(200.dp)
            .height(300.dp),

         text = {

             Column(

                 modifier = Modifier.fillMaxSize()
             ) {


                 Text(text = "Book")

                 Divider()


                 LazyColumn(

                     content = {


                         for (i in 1..180){

                             item { Text(text = i.toString()) }


                         }








                     } // contents ends

                 ) // Lazy ends

             }




         }
    )








}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SwordTheme {
        listOfVerses()
    }
}


