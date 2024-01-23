package com.archie.Sword.states

data class AddingVerseScreenStates(

    val bookName: String = "Genesis",
    val chapter: String = "1",

    val chapterAndVerseNumber: String = "",
    val verseNumber: String = "1",

    val verse: String = "",
    val note: String = "",

    val themeName: String = "",
    val themeColour: String = "",
    val photoFilePath: String = "",


    val isBookSelectionDialogShowing: Boolean = false,
    val isChapterSelectionDialogShowing: Boolean = false,
    val isVerseSelectionDialogShowing: Boolean = false,

    val bookPosition: Byte = 0,

    val doesThemeExist: Boolean = true,

    val showingPopupMenu: Boolean = false,
    val hidingPopupMenu: Boolean = true,



    )