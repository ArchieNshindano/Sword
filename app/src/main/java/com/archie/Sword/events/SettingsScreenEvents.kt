package com.archie.Sword.events

import com.archie.Sword.enums.SortType

sealed class SettingsScreenEvents {

    data class SetSystemThemeTo(val theme: String): SettingsScreenEvents()

    data class EnableOrDisableVerseOfTheDay(val isVerseOfTheDayEnabled: Boolean): SettingsScreenEvents()

    data class EnableOrDisableDynamicSentence(val isDynamicSentenceEnabled: Boolean): SettingsScreenEvents()

    data class EnableOrDisableDynamicTheme(val isDynamicThemeEnabled: Boolean): SettingsScreenEvents()

    data class SetContrastTo(val contrast: String): SettingsScreenEvents()


    data class SetSortTypeTo(val sortType: String): SettingsScreenEvents()

    object NoEvent: SettingsScreenEvents()



}