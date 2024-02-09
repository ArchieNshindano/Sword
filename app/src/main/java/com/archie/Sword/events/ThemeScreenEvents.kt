package com.archie.Sword.events

sealed class ThemeScreenEvents{

    object switchToCurrentThemeSection: ThemeScreenEvents()
    object switchToMyThemesSection: ThemeScreenEvents()

    object showAddingButton: ThemeScreenEvents()
    object hideAddingButton: ThemeScreenEvents()



    object showPopUpMenu: ThemeScreenEvents()
    object hidePopUpMenu: ThemeScreenEvents()








}
