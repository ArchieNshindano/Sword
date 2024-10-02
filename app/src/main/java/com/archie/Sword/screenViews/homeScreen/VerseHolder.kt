package com.archie.Sword.screenViews.homeScreen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import com.example.Sword.ui.theme.SwordTheme
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun themedVerseHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?){

    SwordTheme(
        verseTheme = verse?.themeName ?: "",
        containerDoesNotAffectStatusBar = true,
        darkTheme = state.isSystemDarkTheme,
        contrast = state.contrast
    ) {

        verseHolder(onEvent = onEvent, state = state, verse = verse)

    }
}



@Composable
fun verseHolder(onEvent:(BottomNavigationScreensSharedEvents) -> Unit, state: BottomNavigationSharedStates, verse: Verse?){


    val verseTag = verse?.verseTag ?: ""










    val context = LocalContext.current

    val checkBoxState = remember {

        mutableStateOf(false)
    }

    val currentDate = remember {

        LocalDate.now()
    }


    LaunchedEffect(key1 = verse?.memorisedToday, key2 = verseTag,key3 = {"${currentDate.dayOfMonth} ${currentDate.monthValue} ${currentDate.year}"} ) {


        if(verse?.memorisedToday == 1 && !verse.memorisedTodayDate.isNullOrBlank()){


            val from = LocalDate.parse(verse.memorisedTodayDate, DateTimeFormatter.ofPattern("yyyy-MM-dd") )

            // calculate the period between those two
            val period = Period.between(from, currentDate)
            // Get the current date

            Log.d("Dates", "${verse.verseTag}  Today: ${currentDate}  Yesterday: ${from} ${period.days}")

            if (period.days == 1) {
                // If the difference is 1 day or more, reset memorisedToday to 0

                var memorised = verse.memorisedCount

                onEvent(
                    BottomNavigationScreensSharedEvents.UpdateVerse( verse.copy(memorisedToday = 0, memorisedCount = ++memorised) )
                )
            }

        }


        checkBoxState.value = verse?.memorisedToday == 1


    }



    val haptics = LocalHapticFeedback.current








    Card(
        modifier = Modifier
            .width(362.dp)
            .padding(top = 15.dp,)
            .pointerInput(Unit) {

                detectTapGestures(
                    onLongPress = {

                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)

                        onEvent(BottomNavigationScreensSharedEvents.IsSwipeToDeleteEnabled(true))

                    },

                    onDoubleTap = {

                        if ((verse?.themeName != "None") || !verse.themeName.isNullOrBlank())
                            onEvent(
                                BottomNavigationScreensSharedEvents.UpdateUiThemeTo(
                                    verse?.themeName ?: ""
                                )
                            )
                    },

                    onTap = {

                        if ((verse?.themeName != "None") || !verse.themeName.isNullOrBlank())
                            onEvent(
                                BottomNavigationScreensSharedEvents.UpdateUiThemeTo(
                                    verse?.themeName ?: ""
                                )
                            )


                    }
                )

            },// A
        // .height(100.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 0.dp
        ),

        colors = CardDefaults.cardColors(
//            MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
        )


    ) {

        Column {

            Text(
                text = verseTag ,
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(start = 10.dp)

            )



            Text(
                text = "${verse?.verse}",
                fontFamily = FontFamily.Serif,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Text(
                text = buildAnnotatedString {

                    append("  Memorised \n")

                    withStyle(style = SpanStyle(fontSize = 28.sp)) {

                        append("${verse?.memorisedCount} times")


                    }


                },

                modifier = Modifier.align(Alignment.End)

            )











            Row(
                modifier = Modifier.fillMaxWidth()
            ) {



                IconButton(
                    onClick = {


                        var isPartOfFavorites = 0



                        if(verse?.isPartOfFavorites == 1)
                            isPartOfFavorites = 0

                        else
                            isPartOfFavorites = 1




                        if(verse != null) {


                            onEvent(
                                BottomNavigationScreensSharedEvents.UpdateVerse(

                                    verse.copy(isPartOfFavorites = isPartOfFavorites)

                                )

                            )

                        }



                    }
                ) {

                    Icon(
                        imageVector = if (verse?.isPartOfFavorites == 1) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites"
                    )

                }


                IconButton(

                    onClick = {

                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${verse?.verseTag} \n ${verse?.verse}")
                            putExtra(Intent.EXTRA_TITLE, "Share Verse")
                            type = "text/plain"

                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }

                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share"
                    )

                }


                Row(

                    modifier = Modifier.padding(start = 115.dp)

                ) {

                    Text(
                        text = "Memorise Today",
                        modifier = Modifier
                            .padding(top = 15.dp)
                    )



                    Checkbox(
                        checked = checkBoxState.value,
                        onCheckedChange = { isCheckBoxTicked ->

                            var memorisedToday = 0
                            var memorisedTodayDate = verse?.memorisedTodayDate

                            if (isCheckBoxTicked) {

                                memorisedToday = 1
                                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

                                memorisedTodayDate = simpleDateFormat.format(Date())

                            }



                            if( verse != null) {
                                onEvent(
                                    BottomNavigationScreensSharedEvents.UpdateVerse(

                                        verse.copy(memorisedToday= memorisedToday, memorisedTodayDate = memorisedTodayDate)


                                    ) // B

                                )

                            }
                        }

                    )











                }


            }


        } // COLUMN ENDS

    } // CARD ENDS
}






