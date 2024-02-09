package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.events.SettingsScreenEvents
import com.archie.Sword.states.SettingsScreenStates
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
class SettingsScreenViewModel @Inject constructor(): ViewModel() {

    val _state = MutableStateFlow(SettingsScreenStates())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsScreenStates())


    private val eventsChannel = Channel<SettingsScreenEvents>()
    val eventFlow = eventsChannel.receiveAsFlow()



    fun triggerSetThemeToEvent(thisTheme: String){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.setThemeTo(thisTheme))

        }


    }







    fun triggerTurnOnDarkModeEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.turnOnDarkMode)

        }


    }







    fun triggerShowVerseOfTheDayEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.showVerseOfTheDay)

        }


    }










    fun triggerhideVerseOfTheDayEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.hideVerseOfTheDay)

        }


    }














    fun triggerShowThemesEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.showThemes)

        }


    }













    fun triggerHideThemesEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.hideThemes)

        }


    }












    fun triggerShowDynamicHeaderEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.showDynamicHeader)

        }


    }











    fun triggerHideDynamicHeaderEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.hideDynamicHeader)

        }


    }











    fun triggerShowEncouragementEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.showEncouragement)

        }


    }











    fun triggerHideEncouragementEvent(){

        viewModelScope.launch {

            eventsChannel.send(SettingsScreenEvents.hideEncouragement)

        }


    }






    fun dynamicThemeButtonToggled(){


        _state.update {

            it.copy(

                isDynamicThemeButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun dynamicThemeButtonUntoggled(){


        _state.update {

            it.copy(

                isDynamicThemeButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun darkThemeButtonToggled(){


        _state.update {

            it.copy(

                isDarkThemeButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun darkThemeButtonUntoggled(){


        _state.update {

            it.copy(

                isDarkThemeButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }










    fun showThemesButtonToggled(){


        _state.update {

            it.copy(

                isShowThemesButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun showThemesButtonUntoggled(){


        _state.update {

            it.copy(

                isShowThemesButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }








    fun showVerseOfTheDayButtonToggled(){


        _state.update {

            it.copy(

                isShowVerseOfTheDayButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun showVerseOfTheDayButtonUntoggled(){


        _state.update {

            it.copy(

                isShowVerseOfTheDayButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }








    fun showDynamicHeaderButtonToggled(){


        _state.update {

            it.copy(

                isShowDynamicHeaderButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }








    fun showDynamicHeaderButtonUntoggled(){


        _state.update {

            it.copy(

                isShowDynamicHeaderButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }









    fun showDynamicEncouragementButtonToggled(){


        _state.update {

            it.copy(

                isShowDynamicEncouragementButtonToggled = true
            )  // COPY ENDS

        } // UPDATE ENDS


    }








    fun showDynamicEncouragementButtonUntoggled(){


        _state.update {

            it.copy(

                isShowDynamicEncouragementButtonToggled = false
            )  // COPY ENDS

        } // UPDATE ENDS


    }















}