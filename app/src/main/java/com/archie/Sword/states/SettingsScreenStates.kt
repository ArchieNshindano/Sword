package com.archie.Sword.states

import com.archie.Sword.events.SettingsScreenEvents

data class SettingsScreenStates(

    val isDynamicThemeSelected: Boolean = false,

    val  contrast: String = "",

    val isShowVerseOfTheDayButtonToggled: Boolean = false,
    val isShowDynamicSentenceButtonToggled: Boolean = false,

    val theme: String = "",


    val sortType: String = "",

    val event: SettingsScreenEvents = SettingsScreenEvents.NoEvent

)
