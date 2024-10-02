package com.archie.Sword.screenViews.homeScreenBottomNavigation

import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.favouritesScreen.favoritesScreen
import com.archie.Sword.screenViews.homeScreen.homeScreenContent
import com.archie.Sword.screenViews.themeScreen.ThemeScreen
import com.archie.Sword.states.BottomNavigationSharedStates
import com.archie.Sword.viewModels.BottomNavigationSharedViewModel


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues, state: BottomNavigationSharedStates, onEvent:(BottomNavigationScreensSharedEvents) -> Unit, pagingItems: LazyPagingItems<Verse>){



    val contentPadding = rememberUpdatedState(newValue = paddingValues)



    NavHost(navController = navController , startDestination = Screens.HomeScreen.route ){


        composable(route = Screens.HomeScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()




            homeScreenContent (state = state,onEvent,pagingItems,contentPadding)


        }



        composable(route = Screens.ThemeScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()


//            eventHandlerForBottomNavigation(state = state.value, viewModel = viewModel)

            ThemeScreen(onEvent = onEvent , state = state,contentPadding, pagingItems)

        }


        composable(route = Screens.FavoritesScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()
//
//
//
//            eventHandlerForBottomNavigation(state = state.value, viewModel = viewModel)
            favoritesScreen(onEvent = onEvent , state = state,contentPadding,pagingItems)
        }





    }




}



@Composable
inline fun eventHandlerForBottomNavigation(state: BottomNavigationSharedStates, viewModel: BottomNavigationSharedViewModel, sharedPreferences: SharedPreferences){

    LaunchedEffect(state.currentEvent){

        val event = state.currentEvent

        when(event){
            is BottomNavigationScreensSharedEvents.ShowSortDialog -> viewModel.showSortTypeDialog(event.showDialog)
            is BottomNavigationScreensSharedEvents.CollapseSearchBar -> viewModel.collapseSearchBar()
            is BottomNavigationScreensSharedEvents.ExpandSearchBar -> viewModel.expandSearchBar()
            is BottomNavigationScreensSharedEvents.HideAddingVerseFloatingButton -> viewModel.hideAddingVerseFloatingButton()
            is BottomNavigationScreensSharedEvents.HideMenuSideBar -> viewModel.hideMenuSideBar()
            is BottomNavigationScreensSharedEvents.HidePopUpMenu -> viewModel.hidePopUpMenu()
            is BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton -> viewModel.showAddingVerseFloatingButton()
            is BottomNavigationScreensSharedEvents.ShowMenuSideBar -> viewModel.showMenuSideBar()
            is BottomNavigationScreensSharedEvents.ShowPopUpMenu -> viewModel.showPopUpMenu()
            is BottomNavigationScreensSharedEvents.UpdateUiThemeTo -> {

                sharedPreferences.edit().apply{

                    putString("lastOpenedTheme",event.theme)
                    apply()
                }

                viewModel.updateUiThemeTo(event.theme)
            }
            is BottomNavigationScreensSharedEvents.SearchFor -> viewModel.searchFor(event.searchQuery)
            is BottomNavigationScreensSharedEvents.SetVerse -> viewModel.setVerse(event.verse)
            is BottomNavigationScreensSharedEvents.UpdateVerse -> viewModel.upDateVerse(event.verse)
            is BottomNavigationScreensSharedEvents.DeleteVerse -> viewModel.deleteVerse(event.verse)
            is BottomNavigationScreensSharedEvents.IsSwipeToDeleteEnabled -> viewModel.isSwipeToDeleteEnabled(event.isSwipeToDeleteEnabled)
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