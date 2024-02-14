@file:OptIn(ExperimentalFoundationApi::class)

package com.example.Sword

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.screenViews.homeScreen.overScrollEffect
import com.archie.Sword.screenViews.mainScreen

import com.archie.Sword.viewModels.BottomNavigationSharedViewModel
import com.example.Sword.ui.theme.SwordTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: BottomNavigationSharedViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        lifecycleScope.launch {

                viewModel.eventFlow.collect { event ->

                    Log.d("CheckBox","here")


                    when (event) {

                        BottomNavigationScreensSharedEvents.ShowMenuSideBar -> viewModel.showMenuSideBar()
                        is BottomNavigationScreensSharedEvents.ChangeSortTypeTo -> viewModel.changeSortTypeTo(SortType.byDate)
                        BottomNavigationScreensSharedEvents.CollapseSearchBar -> viewModel.collapseSearchBar()
                        BottomNavigationScreensSharedEvents.ExpandSearchBar -> viewModel.expandSearchBar()
                        BottomNavigationScreensSharedEvents.HideAddingVerseFloatingButton -> viewModel.hideAddingVerseFloatingButton()
                        BottomNavigationScreensSharedEvents.HideMenuSideBar -> viewModel.hideMenuSideBar()
                        BottomNavigationScreensSharedEvents.HidePopUpMenu -> viewModel.hidePopUpMenu()
                        BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton -> viewModel.showAddingVerseFloatingButton()
                        BottomNavigationScreensSharedEvents.ShowPopUpMenu -> viewModel.showPopUpMenu()
                        is BottomNavigationScreensSharedEvents.UpdateUiThemeTo -> viewModel.updateUiThemeTo(
                            ""
                        )

                        is BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse -> {

                            Log.d("CheckBoxMain", event.isCheckBoxTicked.toString())
                            viewModel.tickOrUntickMemoriseVerseCheckBox(event.isCheckBoxTicked)
                        }


                    } // WHEN ENDS

                } // COLLECT ENDS







        }


        setContent {

            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()
            val navController = rememberNavController()


          mainScreen(
              context = this,
              navController = navController,
              state.value,
              viewModel::onEvent)


        }
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SwordTheme {
        Text(text = "HI")

    }
}