@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
)

package com.example.Sword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.homeScreenBottomNavigation.eventHandler
import com.archie.Sword.screenViews.homeScreenBottomNavigation.mainScreen
import com.archie.Sword.states.BottomNavigationSharedStates

import com.archie.Sword.viewModels.BottomNavigationSharedViewModel
import com.example.Sword.ui.theme.SwordTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOf


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {

            val navController = rememberNavController()
            val viewModel: BottomNavigationSharedViewModel = hiltViewModel()
            val state = viewModel.state.collectAsStateWithLifecycle()
            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()


            eventHandler(state = state.value, viewModel = viewModel)

         SwordTheme(verseTheme = state.value.lastOpenedTheme) {

             mainScreen(navController = navController, state = state.value, onEvent = viewModel::onEvent,pagingItems = pagingItems )


         }



        }
    }

}



