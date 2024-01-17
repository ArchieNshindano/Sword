package com.example.Sword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.archie.Sword.events.HomeScreenEvents
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.screenViews.homeScreen.myHomeScreen
import com.archie.Sword.states.HomeScreenStates

import com.archie.Sword.viewModels.HomeScreenViewModel
import com.example.Sword.ui.theme.SwordTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


         lifecycleScope.launch{



             viewModel.eventFlow.collect{ event->

                 when(event){

                     HomeScreenEvents.showMenuSideBar ->  viewModel.showMenuSideBar()
                     is HomeScreenEvents.changeSortTypeOfVersesTo -> viewModel.changeSortTypeOfVersesTo(
                         SortType.byDate)
                     HomeScreenEvents.collapseSearchBar -> viewModel.collapseSearchBar()
                     HomeScreenEvents.expandSearchBar -> viewModel.expandSearchBar()
                     HomeScreenEvents.hideAddingVerseFloatingButton -> viewModel.hideAddingVerseFloatingButton()
                     HomeScreenEvents.hideMenuSideBar ->  viewModel.hideMenuSideBar()
                     HomeScreenEvents.hidePopUpMenu -> viewModel.hidePopUpMenu()
                     HomeScreenEvents.showAddingVerseFloatingButton -> viewModel.showAddingVerseFloatingButton()
                     HomeScreenEvents.showPopUpMenu -> viewModel.showPopUpMenu()
                     is HomeScreenEvents.updateUiThemeTo -> viewModel.updateUiThemeTo("")
                 }



             }
         }


        setContent {

            val state = viewModel.state.collectAsStateWithLifecycle()
            val pagingItems = viewModel.allVerses.flow.collectAsLazyPagingItems()


            myHomeScreen(context = this, viewModel = viewModel, state = state.value , pagingItems = pagingItems)


        }
    }

} 

@Composable
fun hi(){



    Text(text = "HI")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    SwordTheme {
     hi()
    }
}