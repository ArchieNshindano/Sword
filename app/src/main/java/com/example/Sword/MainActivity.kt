@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.example.Sword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.screenViews.homeScreenBottomNavigation.eventHandlerForBottomNavigation
import com.archie.Sword.screenViews.homeScreenBottomNavigation.mainScreen
import com.archie.Sword.viewModels.BottomNavigationSharedViewModel
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContent {

            val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)

            val theme = sharedPreferences.getString("theme", "Light") ?: "Light"
            val contrast = sharedPreferences.getString("contrast", "Normal") ?: "Normal"
            val sortType = sharedPreferences.getString("sortType", "By book") ?: "By book"
            val isDynamicThemeEnabled = sharedPreferences.getBoolean("isDynamicThemeEnabled", false)
            val isDynamicSentencesEnabled = sharedPreferences.getBoolean("isDynamicSentencesEnabled", false)
            val isVerseOfTheDayEnabled = sharedPreferences.getBoolean("isVerseOfTheDayEnabled", false)
            val lastOpenedTheme = sharedPreferences.getString("lastOpenedTheme", "Light") ?: "Light"




            val navController = rememberNavController()
            val viewModel: BottomNavigationSharedViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle()
            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()
            val isDarkTheme = if(theme == "Dark") true else if(theme == "System")  isSystemInDarkTheme() else false


            SideEffect {
            viewModel.updateUiThemeTo(lastOpenedTheme)
            viewModel.enableOrDisableDynamicTheme(isDynamicThemeEnabled)
            viewModel.enableOrDisableDynamicSentence(isDynamicSentencesEnabled)
            viewModel.enableOrDisableVerseOfTheDay(isVerseOfTheDayEnabled)
            viewModel.enableOrDisableDarkTheme(isDarkTheme)
            viewModel.setContrastTo(contrast)
            viewModel.setSortTypeTo(sortType)

                }











            eventHandlerForBottomNavigation(state = state.value, viewModel = viewModel, sharedPreferences)

            SwordTheme(
                verseTheme = state.value.lastOpenedTheme,
                darkTheme = state.value.isSystemDarkTheme,
                contrast = state.value.contrast,
                ) {


                mainScreen(navController = navController, state = state.value, onEvent = viewModel::onEvent,pagingItems = pagingItems )




            }

        }
    }

}