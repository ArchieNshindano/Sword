package com.archie.Sword.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationSharedViewModel @Inject constructor(

    val daoFunctions: DataBaseRepositoryImpl,


): ViewModel() {


    private val _state = MutableStateFlow(BottomNavigationSharedStates())

    val state = _state.asStateFlow()


    val allVerses = Pager(

        config = PagingConfig(

            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200

        ) // PAGING CONFIG ENDS
    ) {

//      when(_state.value.sortType){
//
//          SortType.byBook -> daoFunctions.getVerseByBook()
//          SortType.byDate -> daoFunctions.getVersesByDate()
//          SortType.byTheme -> daoFunctions.getVersesByTheme()
//      }


        daoFunctions.getVersesByDate()


    }// PAGER ENDS


    fun onEvent(event: BottomNavigationScreensSharedEvents) {

        _state.update { it.copy(currentEvent = event) }


    }



    fun searchFor(searchQuery: String) {


        viewModelScope.launch {


            try {

               val verses1 = daoFunctions.searchDatabaseUsingVerseTag("*$searchQuery*")
                val verses2 = daoFunctions.searchDatabaseUsingVerse("*$searchQuery*")
                val verses3 = daoFunctions.searchDatabaseUsingThemeName("*$searchQuery*")
                val verses4 = daoFunctions.searchDatabaseUsingNotes("*$searchQuery*")

                combine(verses1, verses2, verses3, verses4) { v1, v2, v3, v4 ->

                    v1 + v2 + v3 + v4

                }.collectLatest { verses ->

                    _state.update { it.copy(verses = verses) }

                }  // COLLECT LATEST ENDS


            }

            catch (e: Exception) {

              TODO()
                }  // TRY CATCH ENDS

            }  // VIEW MODEL SCOPE ENDS

        } // SEARCH FOR ENDS


        fun showPopUpMenu() {


            _state.update { it.copy(isPopupMenuShowing = true) }


        }

        fun hidePopUpMenu() {


            _state.update { it.copy(isPopupMenuShowing = false) }


        }


        fun showMenuSideBar() {

            _state.update { it.copy(isMenuSideBarShowing = false) }

        }


        fun hideMenuSideBar() {

            _state.update { it.copy(isMenuSideBarShowing = false) }

        }


        fun expandSearchBar() {

            _state.update { it.copy(isSearchBarActive = true) }

        }


        fun collapseSearchBar() {

            _state.update { it.copy(isSearchBarActive = false) }

        }


        fun showAddingVerseFloatingButton() {

            _state.update { it.copy(isAddingVerseFloatingButtonShowing = true) }

        }

        fun hideAddingVerseFloatingButton() {

            _state.update { it.copy(isAddingVerseFloatingButtonShowing = false) }

        }


        fun changeSortTypeTo(sortType: SortType) {


            _state.update { it.copy(sortType = sortType) }

        }


        fun updateUiThemeTo(theme: String) {


            _state.update { it.copy(lastOpenedTheme = theme) }

        }


        fun setVerse(verse: Verse) = _state.update { it.copy(verse = verse) }


        fun EmptyVerseListVerse() = _state.update { it.copy(verses = emptyList()) }

        fun upDateVerse(verse: Verse) {

            viewModelScope.launch {

                daoFunctions.addVerse(verse)
            }

        }


        fun deleteVerse(verse: Verse) {

            viewModelScope.launch {

                daoFunctions.deleteVerse(verse)
            }

        }

        fun isSwipeToDeleteEnabled(isSwipeToDeleteEnabled: Boolean) =
            _state.update { it.copy(isSwipeToDeleteEnabled = isSwipeToDeleteEnabled) }



}


