package com.archie.Sword.states

import androidx.collection.arrayMapOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.archie.Sword.enums.SortType
import com.archie.Sword.events.BottomNavigationScreensSharedEvents
import com.archie.Sword.repositories.database.Verse

data class BottomNavigationSharedStates(

    val isPopupMenuShowing: Boolean = false,

    val showingVerseOfTheDay: Boolean = true,
    val showingDynamicWords: Boolean = true,
    val showingVerseGuesser: Boolean = true,

    val isCheckBoxTicked: MutableMap<String, Boolean> = arrayMapOf(),

    val verse: Verse = Verse(
        "",
        "",
        0,
        0,
        "",
        "",
        "",
        0,
        "",
        0,
        0,
        ""),

    val isMenuSideBarShowing: Boolean = false,

    val isSearchBarActive: Boolean = false,

    val isAddingVerseFloatingButtonShowing: Boolean = true,

    val lastOpenedTheme: String = "",
    val sortType: SortType = SortType.byDate,

    val selectedIndex: Byte = 0,

    val contentPaddingTopDp: Dp = 0.dp,
    val contentPaddingBottomDp: Dp = 0.dp,

    //EVENTS
    val currentEvent: BottomNavigationScreensSharedEvents = BottomNavigationScreensSharedEvents.UpdateUiThemeTo("Theme"),

    val isSwipeToDeleteEnabled: Boolean = false,

    val verses: List<Verse> = listOf(),





    )
