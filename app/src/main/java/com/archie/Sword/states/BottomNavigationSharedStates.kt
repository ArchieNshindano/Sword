package com.archie.Sword.states

import com.archie.Sword.enums.SortType

data class BottomNavigationSharedStates(

    val isPopupMenuShowing: Boolean = false,

    val showingVerseOfTheDay: Boolean = true,
    val showingDynamicWords: Boolean = true,
    val showingVerseGuesser: Boolean = true,

    val isCheckBoxTicked: Boolean = false,

    val isMenuSideBarShowing: Boolean = true,

    val isSearchBarExpanded: Boolean = true,

    val showingAddingVerseFloatingButton: Boolean = true,

    val lastOpenedTheme: String = "",
    val sortType: SortType = SortType.byDate,

    val selectedIndex: Byte = 0



)
