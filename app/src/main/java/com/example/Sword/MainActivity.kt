@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.archie.Sword.screenViews.homeScreenBottomNavigation.mainScreen

import com.archie.Sword.viewModels.BottomNavigationSharedViewModel
import com.example.Sword.ui.theme.SwordTheme

import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val viewModel: BottomNavigationSharedViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


//        lifecycleScope.launch {
//
//            Log.d("CheckBoxLife",(viewModel.eventFlow).toString())
//
//                viewModel.eventFlow.collect { event ->
//
//                    Log.d("CheckBoxFlow","here")
//
//
//                    when (event) {
//
//                        BottomNavigationScreensSharedEvents.ShowMenuSideBar -> viewModel.showMenuSideBar()
//                        is BottomNavigationScreensSharedEvents.ChangeSortTypeTo -> viewModel.changeSortTypeTo(SortType.byDate)
//                        BottomNavigationScreensSharedEvents.CollapseSearchBar -> viewModel.collapseSearchBar()
//                        BottomNavigationScreensSharedEvents.ExpandSearchBar -> viewModel.expandSearchBar()
//                        BottomNavigationScreensSharedEvents.HideAddingVerseFloatingButton -> viewModel.hideAddingVerseFloatingButton()
//                        BottomNavigationScreensSharedEvents.HideMenuSideBar -> viewModel.hideMenuSideBar()
//                        BottomNavigationScreensSharedEvents.HidePopUpMenu -> viewModel.hidePopUpMenu()
//                        BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton -> viewModel.showAddingVerseFloatingButton()
//                        BottomNavigationScreensSharedEvents.ShowPopUpMenu -> viewModel.showPopUpMenu()
//                        is BottomNavigationScreensSharedEvents.UpdateUiThemeTo -> viewModel.updateUiThemeTo(
//                            ""
//                        )
//
//                        is BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse -> {
//
//                            Log.d("CheckBoxMain", event.isCheckBoxTicked.toString())
//                            viewModel.tickOrUntickMemoriseVerseCheckBox(event.isCheckBoxTicked)
//                        }
//
//                        is BottomNavigationScreensSharedEvents.getContentPading -> TODO()
//                    } // WHEN ENDS
//
//                } // COLLECT ENDS
//
//
//
//
//
//
//
//        }


        setContent {

//            val state = viewModel.state.collectAsStateWithLifecycle()
//            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()
            val navController = rememberNavController()

          mainScreen(navController = navController)


        }
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview(

) {

    SwordTheme {
        Text(text = "HI")

    }
}