package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.archie.Sword.events.HomeScreenEvents
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.DataBaseRepository
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.HomeScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

    val daoFunctions: DataBaseRepositoryImpl

): ViewModel() {




//    val verses = daoFunctions.getVersesByDateFlow()
//




  private val _state = MutableStateFlow(HomeScreenStates())

//      var a =  _state.update {
//
//          verses = verses
//      }



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


    private val eventsChannel = Channel<HomeScreenEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()


    fun triggerShowingPopUpMenuEvent(){

         viewModelScope.launch {

             eventsChannel.send(HomeScreenEvents.showPopUpMenu)

         }

    }





    fun triggerHidingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.hidePopUpMenu)

        }

    }






    fun triggerShowingMenuSideBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.showMenuSideBar)

        }

    }





    fun triggerHidingMenuSideBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.hideMenuSideBar)

        }

    }






    fun triggerExpandingSearchBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.expandSearchBar)

        }

    }





    fun triggerCollapsingSearchBarEvent(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.collapseSearchBar)

        }

    }







    fun triggerShowingAddVerseFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.showAddingVerseFloatingButton)

        }

    }





    fun triggerHidingAddVerseFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.showAddingVerseFloatingButton)

        }

    }






    fun triggerChangingSortTypeEvent(sortType: SortType){

        viewModelScope.launch {

            eventsChannel.send(HomeScreenEvents.changeSortTypeOfVersesTo(sortType))

        }


    }















    fun handleEvent(event: HomeScreenEvents){

//        when(event){
//
//            is HomeScreenEvents.showPopUpMenu -> updateState(   _it.copy(showingPopupMenu = true)   )
//            is HomeScreenEvents.hidePopUpMenu -> updateState(   _state.value.copy(showingPopupMenu  =  false)    )
//
//            is HomeScreenEvents.showMenuSideBar -> updateState(   _state.value.copy(showingMenuSideBar = true)  )
//            is HomeScreenEvents.hideMenuSideBar -> updateState(   _state.value.copy(showingMenuSideBar = false )  )
//
//            is HomeScreenEvents.expandSearchBar -> updateState(    _state.value.copy(expandedSearchBar = true)  )
//            is HomeScreenEvents.collapseSearchBar -> updateState(   _state.value.copy(expandedSearchBar = false)  )
//
//            is HomeScreenEvents.showAddingVerseFloatingButton -> updateState(   _state.value.copy(showingAddingVerseFloatingButton = true)  )
//            is HomeScreenEvents.hideAddingVerseFloatingButton -> updateState(   _state.value.copy(showingAddingVerseFloatingButton = false))
//
//            is HomeScreenEvents.changeSortTypeOfVersesTo -> updateState(     _state.value.copy(sortType = event.sortType)         )
//            is HomeScreenEvents.updateUiThemeTo -> updateState(        _state.value.copy( lastOpenedTheme = event.theme )      )
//
//        } // WHEN ENDS
//


    } // EVENT ENDS





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



    fun changeSortTypeOfVersesTo(sortType: SortType){


        _state.update {      it.copy(sortType =  sortType)     }

    }


    fun updateUiThemeTo(theme: String){


        _state.update {      it.copy(lastOpenedTheme = theme)     }

    }
























}