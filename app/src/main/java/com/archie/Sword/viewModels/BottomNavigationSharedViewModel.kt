package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.SentencesRecordDataBaseRepositoryImpl
import com.archie.Sword.repositories.database.VersesDataBaseRepositoryImpl
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.BottomNavigationSharedStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationSharedViewModel @Inject constructor(

    val daoFunctionsForVerses: VersesDataBaseRepositoryImpl,
    val daoFunctionsForSentencesRecord: SentencesRecordDataBaseRepositoryImpl



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


//
//      when(_state.value.sortType){
//
//          "By book" -> daoFunctionsForTheme.getVerseByBook()
//          "" -> daoFunctionsForTheme.getVersesByDate()
//          SortType.byTheme -> daoFunctionsForTheme.getVersesByTheme()
//      }


        daoFunctionsForVerses.getVersesByDate()


    }// PAGER ENDS


    fun onEvent(event: BottomNavigationScreensSharedEvents) {

        _state.update { it.copy(currentEvent = event) }


    }



    fun searchFor(searchQuery: String) {


        viewModelScope.launch {


            try {

               val verses1 = daoFunctionsForVerses.searchDatabaseUsingVerseTag("*$searchQuery*")
                val verses2 = daoFunctionsForVerses.searchDatabaseUsingVerse("*$searchQuery*")
                val verses3 = daoFunctionsForVerses.searchDatabaseUsingThemeName("*$searchQuery*")
                val verses4 = daoFunctionsForVerses.searchDatabaseUsingNotes("*$searchQuery*")

                combine(verses1, verses2, verses3, verses4) { v1, v2, v3, v4 ->

                    v1 + v2 + v3 + v4

                }.collectLatest { verses ->

                    _state.update { it.copy(verses = verses) }

                }  // COLLECT LATEST ENDS


            }

            catch (e: Exception) {


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


        fun showSortTypeDialog(showSortTypeDialog: Boolean) {


            _state.update { it.copy(showSortTypeDialog = showSortTypeDialog) }

        }


        fun updateUiThemeTo(theme: String) {


            _state.update { it.copy(lastOpenedTheme = theme) }

        }


        fun setVerse(verse: Verse) = _state.update { it.copy(verse = verse) }


        fun EmptyVerseListVerse() = _state.update { it.copy(verses = emptyList()) }


        fun upDateVerse(verse: Verse) =  viewModelScope.launch {

                daoFunctionsForVerses.addVerse(verse)
            }




        fun deleteVerse(verse: Verse) =  viewModelScope.launch {

                daoFunctionsForVerses.deleteVerse(verse)
            }


        fun isSwipeToDeleteEnabled(isSwipeToDeleteEnabled: Boolean) = _state.update { it.copy(isSwipeToDeleteEnabled = isSwipeToDeleteEnabled) }


        fun enableOrDisableDynamicTheme(isDynamicThemeEnabled: Boolean) = _state.update { it.copy(isDynamicThemeEnabled = isDynamicThemeEnabled) }
        fun enableOrDisableDynamicSentence(isDynamicSentencesEnabled: Boolean) = _state.update { it.copy(isDynamicSentencesEnabled = isDynamicSentencesEnabled) }
        fun enableOrDisableVerseOfTheDay(isVerseOfTheDayEnabled: Boolean) = _state.update { it.copy(isVerseOfTheDayEnabled = isVerseOfTheDayEnabled) }


        fun enableOrDisableDarkTheme(isDarkTheme: Boolean) = _state.update { it.copy(isSystemDarkTheme = isDarkTheme) }
        fun setContrastTo(contrast: String) = _state.update { it.copy(contrast = contrast) }
        fun setSortTypeTo(sortType: String) = _state.update { it.copy(sortType = sortType) }


}


