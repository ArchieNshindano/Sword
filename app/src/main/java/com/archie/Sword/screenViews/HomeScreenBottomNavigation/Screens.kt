package com.archie.Sword.screenViews.HomeScreenBottomNavigation

sealed class Screens(val route: String){

    object HomeScreen: Screens("home_screen")
    object NotesScreen: Screens("notes_screen")
    object FavoritesScreen: Screens("favorites_screen")


}
