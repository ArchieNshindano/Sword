package com.archie.Sword.states

import android.widget.PopupMenu
import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.Verse

data class HomeScreenStates(

    val showingPopupMenu: Boolean = false,
    val hidingPopupMenu: Boolean = true,

    val showingVerseOfTheDay: Boolean = true,
    val showingDynamicWords: Boolean = true,
    val showingDynamicWord: Boolean = true,

    val hidingMenuSideBar: Boolean = true,
    val showingMenuSideBar: Boolean = true,

    val expandedSearchBar: Boolean = true,

    val showingAddingVerseFloatingButton: Boolean = true,

    val lastOpenedTheme: String = "",
    val sortType: SortType = SortType.byDate,

    val selectedIndex: Byte = 0



)
