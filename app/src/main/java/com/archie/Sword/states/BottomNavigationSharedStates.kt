package com.archie.Sword.states

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.BottomNavigationScreensSharedEvents

data class BottomNavigationSharedStates(

    val isPopupMenuShowing: Boolean = false,

    val showingVerseOfTheDay: Boolean = true,
    val showingDynamicWords: Boolean = true,
    val showingVerseGuesser: Boolean = true,

    val isCheckBoxTicked: Boolean = false,

    val isMenuSideBarShowing: Boolean = true,

    val isSearchBarExpanded: Boolean = true,

    val isAddingVerseFloatingButtonShowing: Boolean = true,

    val lastOpenedTheme: String = "",
    val sortType: SortType = SortType.byDate,

    val selectedIndex: Byte = 0,

    val contentPaddingTopDp: Dp = 0.dp,
    val contentPaddingBottomDp: Dp = 0.dp,

    //EVENTS
    val currentEvent: BottomNavigationScreensSharedEvents = BottomNavigationScreensSharedEvents.UpdateUiThemeTo("Theme")






)
