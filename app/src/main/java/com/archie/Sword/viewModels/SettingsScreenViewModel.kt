package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.SettingsScreenEvents
import com.archie.Sword.repositories.database.SettingsDataBaseRepositoryImpl
import com.archie.Sword.states.SettingsScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(

    val daoFunctionsForSettings: SettingsDataBaseRepositoryImpl
): ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenStates())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SettingsScreenStates())





    fun toggleDynamicThemeButton(isDynamicThemeEnabled: Boolean){


        _state.update {

            it.copy(

                isDynamicThemeSelected = isDynamicThemeEnabled
            )  // COPY ENDS

        } // UPDATE ENDS


    }   // FUNCTION ENDS





    fun toggleVerseOfTheDayButton(isVerseOfTheDayEnabled: Boolean) {

        _state.update {

            it.copy(

                isShowVerseOfTheDayButtonToggled = isVerseOfTheDayEnabled
            )  // COPY ENDS

        } // UPDATE ENDS

    } // FUNCTION ENDS


















    fun toggleDynamicSentenceButton(isShowDynamicSentenceEnabled: Boolean){


        _state.update {

            it.copy(

                isShowDynamicSentenceButtonToggled = isShowDynamicSentenceEnabled
            )  // COPY ENDS

        } // UPDATE ENDS


    }



   fun setContrastTo(contrast: String) {

        _state.update {

            it.copy(

                contrast = contrast
            )  // COPY ENDS

        } // UPDATE ENDS

    } // FUNCTION ENDS


    fun  setSortTypeTo(sortType: String) {

        _state.update {

            it.copy(

                sortType = sortType
            )  // COPY ENDS

        } // UPDATE ENDS

    } // FUNCTION ENDS



    fun  setSystemThemeTo(theme: String) {

        _state.update {

            it.copy(

                theme = theme
            )  // COPY ENDS

        } // UPDATE ENDS

    } // FUNCTION ENDS




    fun onEvent(event: SettingsScreenEvents) {

        _state.update { it.copy(event = event) }


    }






















}