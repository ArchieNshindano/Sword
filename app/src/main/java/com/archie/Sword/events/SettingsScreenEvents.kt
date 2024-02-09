package com.archie.Sword.events

sealed class SettingsScreenEvents {

    data class setThemeTo(val theme: String): SettingsScreenEvents()
    object turnOnDarkMode: SettingsScreenEvents()
    object showVerseOfTheDay: SettingsScreenEvents()
    object hideVerseOfTheDay: SettingsScreenEvents()
    object showThemes: SettingsScreenEvents()
    object hideThemes: SettingsScreenEvents()
    object showDynamicHeader: SettingsScreenEvents()
    object hideDynamicHeader: SettingsScreenEvents()
    object showEncouragement: SettingsScreenEvents()
    object hideEncouragement: SettingsScreenEvents()




}