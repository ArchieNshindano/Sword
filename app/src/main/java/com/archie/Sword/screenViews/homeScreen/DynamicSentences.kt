package com.archie.Sword.screenViews.homeScreen

import CustomComponent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp







@Composable
fun DynamicSentence(sentenceFormat: Int = 0,mainText: String, mainWord: String) {

    when(sentenceFormat) {
        0 -> sentenceFormat0(mainText, mainWord)
        1 -> sentenceFormat1(mainText, mainWord)
        2 -> sentenceFormat2(mainText, mainWord)
        3 -> sentenceFormat3(mainText, mainWord)
        4 -> sentenceFormat4(mainText, mainWord)
    }


}





@Composable
fun sentenceFormat0(mainText: String, mainWord: String){


            Column(
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        start = 30.dp
                    )
                    .fillMaxWidth()
            ){
                Text(
                    text = mainText,

                    fontSize = 20.sp,
                    modifier = Modifier.padding(
                        top = 30.dp, end = 10.dp
                    )
                )

                Text(
                    text = mainWord,

                    fontSize = 50.sp,
                    modifier = Modifier.padding(
                        bottom = 20.dp, start = 30.dp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }


}


@Composable
fun  sentenceFormat1(mainText: String, mainWord: String){



            Row(
                modifier = Modifier
                    .padding(top = 50.dp, start = 10.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = mainText,

                    fontSize =  50.sp,
                    modifier = Modifier.padding(
                        top = 30.dp, end = 10.dp
                    )

                )

                Text(
                    text = mainWord,

                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        top = 50.dp, start = 5.dp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }



}



@Composable
fun  sentenceFormat2(mainText: String, mainWord: String){



            Column(
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        start = 30.dp
                    )
                    .fillMaxWidth()
            ){
                Text(
                    text = mainText,

                    fontSize =  50.sp,
                    modifier = Modifier.padding(
                        top = 30.dp, end = 10.dp
                    )

                )

                Text(
                    text = mainWord,

                    fontSize = 25.sp,
                    modifier = Modifier.padding(
                        bottom = 20.dp, start = 35.dp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }



}



@Composable
fun  sentenceFormat3(mainText: String, mainWord: String){


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 1.dp)
            ){
                Text(
                    text = mainText,

                    fontSize =  25.sp,
                    modifier = Modifier.padding(
                        top = 70.dp, end = 8.dp
                    )

                )

                Text(
                    text = mainWord,

                    fontSize = 50.sp,
                    modifier = Modifier.padding(
                        top = 50.dp, start = 2.5.dp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }

}




@Composable
fun sentenceFormat4(mainText: String, mainWord: String){


            Column(
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        start = 30.dp
                    )
                    .fillMaxWidth()
            ){
                Text(
                    text = mainText,

                    fontSize = 20.sp,
                    modifier = Modifier.padding(
                        top = 30.dp, start = 50.dp
                    )
                )

                Text(
                    text = mainWord,

                    fontSize = 50.sp,
                    modifier = Modifier.padding(
                        bottom = 20.dp,
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

            }


}