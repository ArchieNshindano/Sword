package com.archie.Sword.events

import com.archie.Sword.enums.SortType
import com.archie.Sword.repositories.database.Verse

sealed interface BottomNavigationScreensSharedEvents{

    data class UpdateUiThemeTo(val theme: String): BottomNavigationScreensSharedEvents

    object ShowPopUpMenu: BottomNavigationScreensSharedEvents
    data class SetVerse(val verse: Verse): BottomNavigationScreensSharedEvents



    object HidePopUpMenu: BottomNavigationScreensSharedEvents

    object ShowMenuSideBar: BottomNavigationScreensSharedEvents
    object HideMenuSideBar: BottomNavigationScreensSharedEvents

    object ExpandSearchBar: BottomNavigationScreensSharedEvents
    object CollapseSearchBar: BottomNavigationScreensSharedEvents

    object ShowAddingVerseFloatingButton: BottomNavigationScreensSharedEvents
    object HideAddingVerseFloatingButton: BottomNavigationScreensSharedEvents

//    data class DynamicThemeEnabled(val isEnabled: Boolean): BottomNavigationScreensSharedEvents
//    data class DynamicSentencesEnabled(val isEnabled: Boolean): BottomNavigationScreensSharedEvents
//    data class VerseOfTheDayEnabled(val isEnabled: Boolean): BottomNavigationScreensSharedEvents


    data class SearchFor(val searchQuery: String): BottomNavigationScreensSharedEvents
    data class ShowSortDialog(val showDialog: Boolean): BottomNavigationScreensSharedEvents

    data class UpdateVerse(val verse: Verse): BottomNavigationScreensSharedEvents


    data class DeleteVerse(val verse: Verse): BottomNavigationScreensSharedEvents



    data class IsSwipeToDeleteEnabled(val isSwipeToDeleteEnabled: Boolean): BottomNavigationScreensSharedEvents

//
//    //Fragments
//    object launchHome: BottomNavigationScreensSharedEvents
//    object launchCatchUp: BottomNavigationScreensSharedEvents
//    object launchFavourites: BottomNavigationScreensSharedEvents
//    object launchCurrentThemes: BottomNavigationScreensSharedEvents
//    object launchAllThemes: BottomNavigationScreensSharedEvents
//    object launchVerseView: BottomNavigationScreensSharedEvents
//
//
//    //Activities
//    object launchAddingVerse: BottomNavigationScreensSharedEvents
//    object launchSettings: BottomNavigationScreensSharedEvents
//    object launchTheme: BottomNavigationScreensSharedEvents


//    object saveVerse: BottomNavigationScreensSharedEvents

//    data class enterBook(val bookName: String): BottomNavigationScreensSharedEvents
//    data class enterVerse(val verse: String): BottomNavigationScreensSharedEvents
//    data class enterVerseNum(val chapterAndVerseNumber: String): BottomNavigationScreensSharedEvents
//    data class sortVerses(val sortType: SortType): BottomNavigationScreensSharedEvents
//    data class enterThemeName(val name: String): BottomNavigationScreensSharedEvents
//    data class enterThemeColour(val colorLevelValues: Int): BottomNavigationScreensSharedEvents



}