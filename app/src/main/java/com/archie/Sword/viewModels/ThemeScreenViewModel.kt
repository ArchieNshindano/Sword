package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.events.ThemeScreenEvents
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.states.ThemeScreenStates
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
class ThemeScreenViewModel @Inject constructor(
    daoFunctions: DaoFunctions
): ViewModel() {

    val _state = MutableStateFlow(ThemeScreenStates())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ThemeScreenStates())


    private val eventsChannel = Channel<ThemeScreenEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()



    fun triggerShowingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(ThemeScreenEvents.showPopUpMenu)

        }

    }





    fun triggerHidingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(ThemeScreenEvents.hidePopUpMenu)

        }

    }






    fun triggerShowingAddingButtonEvent(){

        viewModelScope.launch {

            eventsChannel.send(ThemeScreenEvents.showAddingButton)

        }

    }





    fun triggerHidingAddingButtonEvent(){

        viewModelScope.launch {

            eventsChannel.send(ThemeScreenEvents.showAddingButton)

        }

    }






    fun showPopUpMenu(){


        _state.update { it.copy(isPopUpMenuShowing = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(isPopUpMenuShowing = false)    }


    }





    fun showAddingButton(){

        _state.update {      it.copy(isAddingButtonShowing = true)     }

    }

    fun hideAddingButton(){

        _state.update {      it.copy(isAddingButtonShowing = false)     }

    }




}