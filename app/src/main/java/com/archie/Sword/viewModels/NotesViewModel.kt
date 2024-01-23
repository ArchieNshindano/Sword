package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.HomeScreenEvents
import com.archie.Sword.events.NotesScreenEvents
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.states.NotesScreenStates
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
class NotesViewModel @Inject constructor(
    val daoFunctions: DaoFunctions
): ViewModel(){


   private val _state = MutableStateFlow(NotesScreenStates())

    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesScreenStates())

    private val eventsChannel = Channel<NotesScreenEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()


    fun triggerShowingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(NotesScreenEvents.showPopUpMenu)

        }

    }





    fun triggerHidingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(NotesScreenEvents.hidePopUpMenu)

        }

    }




    fun triggerShowingAddNotesFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(NotesScreenEvents.showAddingNotesButton)

        }

    }





    fun triggerHidingAddNotesFloatingButton(){

        viewModelScope.launch {

            eventsChannel.send(NotesScreenEvents.hideAddingNotesButton)

        }

    }






    fun triggerChangingSortTypeEvent(sortType: SortType){

        viewModelScope.launch {

            eventsChannel.send(NotesScreenEvents.changeSortTypeOfNotesTo(sortType))

        }


    }






    fun showPopUpMenu(){


        _state.update { it.copy(isPopUpMenuShowing  = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(isPopUpMenuShowing = false)    }


    }







    fun showAddingNotesFloatingButton(){

        _state.update {      it.copy(isAddingNotesButtonShowing = true)     }

    }

    fun hideAddingNotesFloatingButton(){

        _state.update {      it.copy(isAddingNotesButtonShowing = false)     }

    }




    fun changeSortTypeOfNotesTo(sortType: SortType){


        _state.update {      it.copy(sortType =  sortType)     }

    }


















}