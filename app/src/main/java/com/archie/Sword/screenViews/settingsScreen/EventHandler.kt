package com.archie.Sword.screenViews.settingsScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.archie.Sword.events.SettingsScreenEvents
import com.archie.Sword.states.SettingsScreenStates
import com.archie.Sword.viewModels.SettingsScreenViewModel


@Composable
inline fun eventHandlerForSettings(state: SettingsScreenStates, viewModel: SettingsScreenViewModel){

    LaunchedEffect(state.event){


        Log.d("SettingsScreenActivity", "Event: ${state.event}")

        val event = state.event

        when(event){

                is SettingsScreenEvents.SetSystemThemeTo -> viewModel.setSystemThemeTo(event.theme)
                is SettingsScreenEvents.SetContrastTo -> viewModel.setContrastTo(event.contrast)
                is SettingsScreenEvents.EnableOrDisableVerseOfTheDay -> viewModel.toggleVerseOfTheDayButton(event.isVerseOfTheDayEnabled)
                is SettingsScreenEvents.EnableOrDisableDynamicSentence -> viewModel.toggleDynamicSentenceButton(event.isDynamicSentenceEnabled)
                is SettingsScreenEvents.EnableOrDisableDynamicTheme -> viewModel.toggleDynamicThemeButton(event.isDynamicThemeEnabled)
                is SettingsScreenEvents.SetSortTypeTo -> viewModel.setSortTypeTo(event.sortType)

                SettingsScreenEvents.NoEvent -> { }
        }



    }
}