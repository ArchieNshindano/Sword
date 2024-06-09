@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.Sword

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.archie.Sword.enums.ColorStrokes
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        actionBar?.hide()



        setContent {

           val typeWriterIsDone = TypewriterText(texts = listOf("Sword"))

            if(typeWriterIsDone){

                         val intentObj = Intent(this, MainActivity::class.java)
                         startActivity(intentObj)
                         finish()


                
            }



        }
    }
}






@Composable
fun TypewriterText(
    texts: List<String>,
): Boolean {
    var textIndex by remember {
        mutableStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }

    val textLength = texts[0].length

    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[textIndex]
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(250)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){

        Text(
            text = textToDisplay,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 500.dp),
            color = if (isSystemInDarkTheme()) Color(0xFFF6E39C) else Color.Black

        )

    }

    return textLength == textToDisplay.length

}

@Composable
@Preview(showBackground = true)
fun hello(){

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {


        TypewriterText(texts = listOf("Sword"))

    }




}