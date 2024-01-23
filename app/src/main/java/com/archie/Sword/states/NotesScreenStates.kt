package com.archie.Sword.states

import com.archie.Sword.enums.SortType

data class NotesScreenStates(

    val note: String = "",
    val themeName: String = "",
    val sortType: SortType = SortType.byDate,


    val isAllButtonSelected: Boolean = true,
    val isRecentButtonSelected: Boolean = false,

    val isPopUpMenuShowing: Boolean = false,
    val isAddingNotesButtonShowing: Boolean = true




)
