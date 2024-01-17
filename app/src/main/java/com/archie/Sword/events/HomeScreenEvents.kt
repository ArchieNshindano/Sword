package com.archie.Sword.events

import com.archie.Sword.enums.SortType

sealed class HomeScreenEvents{

    data class updateUiThemeTo(val theme: String): HomeScreenEvents()

    object showPopUpMenu: HomeScreenEvents()
    object hidePopUpMenu: HomeScreenEvents()

    object showMenuSideBar: HomeScreenEvents()
    object hideMenuSideBar: HomeScreenEvents()

    object expandSearchBar: HomeScreenEvents()
    object collapseSearchBar: HomeScreenEvents()

    object showAddingVerseFloatingButton: HomeScreenEvents()
    object hideAddingVerseFloatingButton: HomeScreenEvents()

    data class changeSortTypeOfVersesTo(val sortType: SortType): HomeScreenEvents()

//
//    //Fragments
//    object launchHome: HomeScreenEvents
//    object launchCatchUp: HomeScreenEvents
//    object launchFavourites: HomeScreenEvents
//    object launchCurrentThemes: HomeScreenEvents
//    object launchAllThemes: HomeScreenEvents
//    object launchVerseView: HomeScreenEvents
//
//
//    //Activities
//    object launchAddingVerse: HomeScreenEvents
//    object launchSettings: HomeScreenEvents
//    object launchTheme: HomeScreenEvents


//    object saveVerse: HomeScreenEvents
//    data class deleteVerse(val verse: Verse): HomeScreenEvents
//    data class enterBook(val bookName: String): HomeScreenEvents
//    data class enterVerse(val verse: String): HomeScreenEvents
//    data class enterVerseNum(val chapterAndVerseNumber: String): HomeScreenEvents
//    data class sortVerses(val sortType: SortType): HomeScreenEvents
//    data class enterThemeName(val name: String): HomeScreenEvents
//    data class enterThemeColour(val colorLevelValues: Int): HomeScreenEvents



}