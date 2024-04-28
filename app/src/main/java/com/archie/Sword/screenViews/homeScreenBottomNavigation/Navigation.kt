package com.archie.Sword.screenViews.homeScreenBottomNavigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.screenViews.favouritesScreen.favoritesScreen
import com.archie.Sword.screenViews.homeScreen.homeScreenContent
import com.archie.Sword.states.BottomNavigationSharedStates
import com.archie.Sword.viewModels.BottomNavigationSharedViewModel


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues){

    val viewModel: BottomNavigationSharedViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()

    eventHandler(state = state.value, viewModel = viewModel)


    val contentPadding = remember {

        paddingValues
    }



    NavHost(navController = navController , startDestination = Screens.HomeScreen.route ){


        composable(route = Screens.HomeScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()




            homeScreenContent (state = state.value,viewModel::onEvent,pagingItems,contentPadding)


        }



        composable(route = Screens.ThemeScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()


//            eventHandler(state = state.value, viewModel = viewModel)

            ThemeScreen(onEvent = viewModel::onEvent , state = state.value,contentPadding, pagingItems)

        }


        composable(route = Screens.FavoritesScreen.route){

//            val viewModel = it.sharedViewModel<BottomNavigationSharedViewModel>(navController = navController)
//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()
//
//
//
//            eventHandler(state = state.value, viewModel = viewModel)
            favoritesScreen(onEvent = viewModel::onEvent , state = state.value,contentPadding,pagingItems)
        }





    }




}



@Composable
inline fun eventHandler(state: BottomNavigationSharedStates,viewModel: BottomNavigationSharedViewModel){

    LaunchedEffect(state.currentEvent){

        val event = state.currentEvent

        when(event){
            is BottomNavigationScreensSharedEvents.ChangeSortTypeTo -> viewModel.changeSortTypeTo(event.sortType)
            BottomNavigationScreensSharedEvents.CollapseSearchBar -> viewModel.collapseSearchBar()
            BottomNavigationScreensSharedEvents.ExpandSearchBar -> viewModel.expandSearchBar()
            BottomNavigationScreensSharedEvents.HideAddingVerseFloatingButton -> viewModel.hideAddingVerseFloatingButton()
            BottomNavigationScreensSharedEvents.HideMenuSideBar -> viewModel.hideMenuSideBar()
            BottomNavigationScreensSharedEvents.HidePopUpMenu -> viewModel.hidePopUpMenu()
            BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton -> viewModel.showAddingVerseFloatingButton()
            BottomNavigationScreensSharedEvents.ShowMenuSideBar -> viewModel.showMenuSideBar()
            BottomNavigationScreensSharedEvents.ShowPopUpMenu -> viewModel.showPopUpMenu()
            is BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse -> viewModel.tickOrUntickMemoriseVerseCheckBox(event.verseTag, event.isCheckBoxTicked)
            is BottomNavigationScreensSharedEvents.UpdateUiThemeTo -> viewModel.updateUiThemeTo(event.theme)
            is BottomNavigationScreensSharedEvents.getContentPading -> {

                Log.d("EventLaunched", "${event.topDp}  ${event.bottomDp}")

                viewModel.getContentPadding(event.topDp, event.bottomDp)

            }

            is BottomNavigationScreensSharedEvents.SetVerse -> viewModel.setVerse(event.verse)
            is BottomNavigationScreensSharedEvents.UpdateVerse -> viewModel.updateVerse(event.verse)
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