package com.archie.Sword.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.states.BottomNavigationSharedStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationSharedViewModel @Inject constructor(

    val daoFunctions: DataBaseRepositoryImpl

): ViewModel() {




  private val _state = MutableStateFlow(BottomNavigationSharedStates())


  val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BottomNavigationSharedStates())


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


    private val eventsChannel = Channel<BottomNavigationScreensSharedEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()



    fun onEvent(event: BottomNavigationScreensSharedEvents){


        when(event){

            is BottomNavigationScreensSharedEvents.ChangeSortTypeTo -> triggerChangingSortTypeEvent(event.sortType)
            BottomNavigationScreensSharedEvents.CollapseSearchBar -> triggerCollapsingSearchBarEvent()
            BottomNavigationScreensSharedEvents.ExpandSearchBar -> triggerExpandingSearchBarEvent()
            BottomNavigationScreensSharedEvents.HideAddingVerseFloatingButton -> triggerHidingAddVerseFloatingButton()
            BottomNavigationScreensSharedEvents.HideMenuSideBar -> triggerHidingMenuSideBarEvent()
            BottomNavigationScreensSharedEvents.HidePopUpMenu -> triggerHidingPopUpMenuEvent()
            BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton -> triggerShowingAddVerseFloatingButton()
            BottomNavigationScreensSharedEvents.ShowMenuSideBar -> triggerShowingMenuSideBarEvent()
            BottomNavigationScreensSharedEvents.ShowPopUpMenu -> triggerShowingPopUpMenuEvent()
            is BottomNavigationScreensSharedEvents.UpdateUiThemeTo -> TODO()
            is BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse -> {
                Log.d("CheckBoxOnEvent", event.isCheckBoxTicked.toString())

                triggerTickOrUntickCheckBoxToMemoriseVerseEvent(event.isCheckBoxTicked)
            }
        }


    }


    private fun triggerShowingPopUpMenuEvent(){

         viewModelScope.launch {

             eventsChannel.send(BottomNavigationScreensSharedEvents.ShowPopUpMenu)

         }

    }





    private fun triggerHidingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.HidePopUpMenu)

        }
    }






    private fun triggerShowingMenuSideBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.ShowMenuSideBar)

        }

    }





    private fun triggerHidingMenuSideBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.HideMenuSideBar)

        }

    }






    private fun triggerExpandingSearchBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.ExpandSearchBar)

        }

    }





    private fun triggerCollapsingSearchBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.CollapseSearchBar)

        }

    }







    private fun triggerShowingAddVerseFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton)

        }

    }





    private fun triggerHidingAddVerseFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.ShowAddingVerseFloatingButton)

        }

    }






    private fun triggerChangingSortTypeEvent(sortType: SortType){

        viewModelScope.launch {

            eventsChannel.send(BottomNavigationScreensSharedEvents.ChangeSortTypeTo(sortType))

        }


    }




    fun triggerTickOrUntickCheckBoxToMemoriseVerseEvent(isCheckBoxTicked: Boolean)
    = viewModelScope.launch {

            Log.d("CheckBoxTrigger", isCheckBoxTicked.toString())

            eventsChannel.send(BottomNavigationScreensSharedEvents.TickOrUntickCheckBoxToMemoriseVerse(isCheckBoxTicked))

        }































     fun showPopUpMenu(){


        _state.update { it.copy(isPopupMenuShowing = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(isPopupMenuShowing = false)    }


    }


    fun showMenuSideBar(){

        _state.update {     it.copy(isMenuSideBarShowing = false)    }

    }


    fun hideMenuSideBar(){

        _state.update {     it.copy(isMenuSideBarShowing = false)     }

    }


    fun expandSearchBar(){

        _state.update {      it.copy(isSearchBarExpanded = true)     }

    }


    fun collapseSearchBar(){

        _state.update {      it.copy(isSearchBarExpanded = false)     }

    }



    fun showAddingVerseFloatingButton(){

        _state.update {      it.copy(showingAddingVerseFloatingButton = true)     }

    }

    fun hideAddingVerseFloatingButton(){

        _state.update {      it.copy(showingAddingVerseFloatingButton = false)     }

    }



    fun changeSortTypeTo(sortType: SortType){


        _state.update {      it.copy(sortType =  sortType)     }

    }


    fun updateUiThemeTo(theme: String){


        _state.update {      it.copy(lastOpenedTheme = theme)     }

    }


    fun tickOrUntickMemoriseVerseCheckBox(isCheckBoxTicked: Boolean){


        Log.d("CheckBoxTrigger", isCheckBoxTicked.toString())


        _state.update {      it.copy(isCheckBoxTicked = isCheckBoxTicked)     }

    }
























}