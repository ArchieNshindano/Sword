@file:OptIn(ExperimentalMaterial3Api::class)

package com.archie.Sword.screenViews.settingsScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.archie.Sword.events.SettingsScreenEvents
import com.archie.Sword.states.SettingsScreenStates
import com.example.Sword.R
import com.example.Sword.ui.theme.SwordTheme


data class SettingOptions(

    val title: String,
   // val icon: ImageVector,
    val subtitle: String? = null,

    val iconId: Int? = null

)


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SettingsScreen(
    onEvent: (SettingsScreenEvents) -> Unit = {},
    state: SettingsScreenStates = SettingsScreenStates(),
    sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("settings", Context.MODE_PRIVATE)
) {





        val settingsOptions = listOf(
            SettingOptions(
                title = "Theme",
                subtitle = state.theme,
                iconId = R.drawable.day_night
            ),
            SettingOptions(
                title = "Dynamic Theme",
                iconId = R.drawable.theme
            ),
            SettingOptions(
                title = "Dynamic Sentences",
                iconId = R.drawable.dynamic_sentence
            ),
            SettingOptions(
                title = "Contrast",
                subtitle = state.contrast,
                iconId = R.drawable.contrast
            ),
            SettingOptions(
                title = "Verse of the Day",
                iconId = R.drawable.outline_wb_sunny_24
            ),
            SettingOptions(
                title = "Sort Type",
                subtitle = state.sortType,
                iconId = R.drawable.sort
            )
        )

        var dialog by remember { mutableStateOf("") }
        var isDialogEnabled by remember { mutableStateOf(false) }
        val startDpForOptions = 20.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Settings", fontSize = MaterialTheme.typography.displayMedium.fontSize)
                    },
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {


                if (isDialogEnabled )
                     isDialogEnabled = SettingsDialog(
                        dialog = dialog,
                        onEvent = onEvent,
                         sharedPreferences = sharedPreferences
                    )


                settingsOptions.forEachIndexed { index, option ->



                    if (option.title == "Theme" || option.title == "Contrast" || option.title == "Sort Type") {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    dialog = option.title
                                    isDialogEnabled = true
                                }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = option.iconId ?: R.drawable.day_night),
                                contentDescription = null,
                                modifier = Modifier.padding(start = 20.dp, top = 3.dp)
                            )
                            Column(
                                modifier = Modifier.padding(start = startDpForOptions)
                            ) {
                                Text(
                                    text = option.title,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                                )
                                Text(
                                    text = option.subtitle ?: "",
                                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                                    fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                        }
                    } else {

                        val selectedIndex = remember { mutableStateOf(0) }

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = option.iconId ?: R.drawable.day_night),
                                contentDescription = null,
                                modifier = Modifier.padding(start = startDpForOptions, top = 12.dp)
                            )
                            Text(
                                text = option.title,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 12.dp, start = startDpForOptions),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize
                            )
                            Switch(
                                checked =
                                if (option.title == "Dynamic Theme") state.isDynamicThemeSelected
                                else if (option.title == "Dynamic Sentences") state.isShowDynamicSentenceButtonToggled
                                else state.isShowVerseOfTheDayButtonToggled,

                                onCheckedChange = {


                                    sharedPreferences.edit().apply {

                                        when (option.title) {

                                            "Dynamic Theme" -> {
                                                putBoolean("isDynamicThemeEnabled", it)
                                                apply()
                                                onEvent( SettingsScreenEvents.EnableOrDisableDynamicTheme(it) )
                                            } // END OF DYNAMIC THEME

                                            "Dynamic Sentences" -> {
                                                putBoolean("isDynamicSentencesEnabled", it)
                                                apply()
                                                onEvent( SettingsScreenEvents.EnableOrDisableDynamicSentence(it) )
                                            }  // END OF DYNAMIC SENTENCES

                                            "Verse of the Day" -> {
                                                putBoolean("isVerseOfTheDayEnabled", it)
                                                apply()
                                                onEvent( SettingsScreenEvents.EnableOrDisableVerseOfTheDay(it) )
                                            } // END OF VERSE OF THE DAY
                                        }
                                    }




                                },
                                modifier = Modifier.padding(end = 20.dp)
                            )
                        }
                    }
                }
            }
        }

}




fun handleDialogEvent(dialog: String, item: String, onEvent: (SettingsScreenEvents) -> Unit, sharedPreferences: SharedPreferences) {

    val editor = sharedPreferences.edit()




    editor.apply {

        when (dialog) {
            "Theme" -> {

                putString("theme", item)
                apply()

                onEvent(SettingsScreenEvents.SetSystemThemeTo(item))

                Log.d("handleEvent", "Theme: ${sharedPreferences.getString("theme", "Light")}")


            }
            "Contrast" -> {

                putString("contrast", item)
                apply()

                onEvent(SettingsScreenEvents.SetContrastTo(item))
            }

            "Sort Type" -> {

                putString("sortType", item)
                apply()

                onEvent(SettingsScreenEvents.SetSortTypeTo(item))
            }
        }

    }


}


@Composable
fun SettingsDialog(
    dialog: String = "Theme",
    onEvent: (SettingsScreenEvents) -> Unit = {},
    state: SettingsScreenStates = SettingsScreenStates(
        
        theme = "Light"
    ),
    sharedPreferences: SharedPreferences ,
): Boolean {



    val options = when (dialog) {

        "Theme" -> listOf("Light", "Dark", "System")
        "Contrast" -> listOf("Normal", "Medium", "High")
        else -> listOf("Sort by Book", "Sort by Date", "Sort by Theme")


    }


    var isDialogEnabled by remember { mutableStateOf(true) }
    val selectedIndex = remember { mutableStateOf(7) }

    Column {
        Dialog(
            onDismissRequest = {
                isDialogEnabled = false
            },
        ) {
            Card(
                modifier = Modifier.size(300.dp)
            ) {
                Text(
                    text = dialog,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                )

                HorizontalDivider()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    options.forEachIndexed { index, item ->


                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = if (index == 0) 0.dp else 30.dp)
                                .clickable {

                                    if (selectedIndex.value == index)
                                        selectedIndex.value = 7
                                    else
                                        selectedIndex.value = index

                                    handleDialogEvent(dialog, item, onEvent, sharedPreferences = sharedPreferences)
                                },
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = item,
                                fontSize = 30.sp,
                            )

                            if (selectedIndex.value == index)
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 15.dp)
                                ) // END OF ICON
                        } // END OF ROW
                    } // END OF FOREACH
                } // END OF COLUMN
            }  // END OF CARD
        } // END OF DIALOG
    } // END OF COLUMN

    return isDialogEnabled
}









