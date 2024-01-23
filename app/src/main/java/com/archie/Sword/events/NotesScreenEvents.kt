package com.archie.Sword.events

import com.archie.Sword.enums.SortType

sealed class NotesScreenEvents{

    object addNote: NotesScreenEvents()
    object showAllNotes: NotesScreenEvents()
    object showRecentNotes: NotesScreenEvents()

    data class changeSortTypeOfNotesTo(val sortType: SortType): NotesScreenEvents()

    object sortNotesByTheme: NotesScreenEvents()
    object sortNotesByDate: NotesScreenEvents()

    object showPopUpMenu: NotesScreenEvents()
    object hidePopUpMenu: NotesScreenEvents()

    object attachNoteToVerse: NotesScreenEvents()

    object showAddingNotesButton: NotesScreenEvents()
    object hideAddingNotesButton: NotesScreenEvents()








}
