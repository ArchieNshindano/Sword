package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.events.FavouritesScreenEvents
import com.archie.Sword.events.HomeScreenEvents
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.states.FavouritesScreenStates
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
class FavouritesScreenViewModel @Inject constructor(

    daoFunctions: DaoFunctions
): ViewModel() {


    private val _state = MutableStateFlow(FavouritesScreenStates())

    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FavouritesScreenStates())

    private val eventsChannel = Channel<FavouritesScreenEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()




    fun triggerShowingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.showPopUpMenu)

        }

    }





    fun triggerHidingPopUpMenuEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.hidePopUpMenu)

        }

    }



    fun triggerShowingRecentlyAddedItemsEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.showRecentlyAddedItems)

        }

    }











    fun triggerShowingAllItemsEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.showAllItems)

        }

    }







    fun triggerShowingAddingButtonEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.showAddingButton)

        }

    }





    fun triggerHidingAddingButtonEvent(){

        viewModelScope.launch {

            eventsChannel.send(FavouritesScreenEvents.hideAddingButton)

        }

    }










    fun showPopUpMenu(){


        _state.update { it.copy(isPopUpMenuShowing = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(isPopUpMenuShowing = false)    }


    }




    fun showAddingVerseFloatingButton(){

        _state.update {      it.copy(isAddingButtonShowing = true)     }

    }

    fun hideAddingVerseFloatingButton(){

        _state.update {      it.copy(isAddingButtonShowing = false)     }

    }








}