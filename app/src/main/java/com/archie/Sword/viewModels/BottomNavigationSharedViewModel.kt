package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.states.HomeScreenStates
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




  private val _state = MutableStateFlow(HomeScreenStates())


  val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeScreenStates())


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






















     fun showPopUpMenu(){


        _state.update { it.copy(showingPopupMenu = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(showingPopupMenu = false)    }


    }


    fun showMenuSideBar(){

        _state.update {     it.copy(showingMenuSideBar = false)    }

    }


    fun hideMenuSideBar(){

        _state.update {     it.copy(showingMenuSideBar = false)     }

    }


    fun expandSearchBar(){

        _state.update {      it.copy(expandedSearchBar = true)     }

    }


    fun collapseSearchBar(){

        _state.update {      it.copy(expandedSearchBar = false)     }

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
























}