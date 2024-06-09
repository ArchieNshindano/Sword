package com.archie.Sword.events

import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.AddingVerseScreenStates

sealed class AddingVerseScreenEvents {

    object showPopUpMenu: AddingVerseScreenEvents()
    object hidePopUpMenu: AddingVerseScreenEvents()

    object saveVerse: AddingVerseScreenEvents()

    object ShowBookSelectionDialog: AddingVerseScreenEvents()

    data class SetVerse(val verse: String): AddingVerseScreenEvents()
    data class SetNote(val note: String): AddingVerseScreenEvents()
    data class SetThemeName(val theme: String, val isThemeSelected: Boolean): AddingVerseScreenEvents()
    data class SetThemeColor(val color: String): AddingVerseScreenEvents()
    data class SetVerseNumber(val verseNumber: String): AddingVerseScreenEvents()
    data class SetBookName(val bookName: String): AddingVerseScreenEvents()
    data class SetChapter(val chapter: String): AddingVerseScreenEvents()
    data class SetBookPosition(val position: Byte): AddingVerseScreenEvents()

    object HideBookSelectionDialog: AddingVerseScreenEvents()
    object HideChapterSelectionDialog: AddingVerseScreenEvents()
    object HideVerseSelectionDialog: AddingVerseScreenEvents()

    object ShowChapterSelectionDialog: AddingVerseScreenEvents()
    object ShowVerseSelectionDialog: AddingVerseScreenEvents()


}