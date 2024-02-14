package com.archie.Sword.screenViews.HomeScreenBottomNavigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.screenViews.favouritesScreen.favoritesScreen
import com.archie.Sword.screenViews.homeScreen.homeScreenContent
import com.archie.Sword.viewModels.BottomNavigationSharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(context: Context, contentPadding: PaddingValues, navController: NavHostController){



    NavHost(navController = navController , startDestination = Screens.HomeScreen.route ){


        composable(route = Screens.HomeScreen.route){

            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
            val state = viewModel.state.collectAsStateWithLifecycle()
            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()


            homeScreenContent(context = context ,  onEvent = viewModel::onEvent , state = state.value , pagingItems = pagingItems, contentPadding = contentPadding)


        }



        composable(route = Screens.NotesScreen.route){

            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
            val state = viewModel.state.collectAsStateWithLifecycle()

            NotesScreen(onEvent = viewModel::onEvent , states = state.value)

        }


        composable(route = Screens.FavoritesScreen.route){

            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
            val state = viewModel.state.collectAsStateWithLifecycle()

            favoritesScreen(onEvent = viewModel::onEvent , states = state.value)
        }





    }




}




@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {

     val navGraphRoute = destination.parent?.route ?: return hiltViewModel()

     val parentEntry = remember(this){

         navController.getBackStackEntry(navGraphRoute )

     }


    return hiltViewModel(parentEntry)


}