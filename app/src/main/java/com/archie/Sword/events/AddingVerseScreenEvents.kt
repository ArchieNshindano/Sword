package com.archie.Sword.events

sealed class AddingVerseScreenEvents {

    object showPopUpMenu: AddingVerseScreenEvents()
    object hidePopUpMenu: AddingVerseScreenEvents()

    object saveVerse: AddingVerseScreenEvents()


}