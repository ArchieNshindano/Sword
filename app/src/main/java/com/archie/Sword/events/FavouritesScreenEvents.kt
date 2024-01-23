package com.archie.Sword.events

sealed class FavouritesScreenEvents{

    object showRecentlyAddedItems: FavouritesScreenEvents()
    object showAllItems: FavouritesScreenEvents()
    object showPopUpMenu: FavouritesScreenEvents()
    object hidePopUpMenu: FavouritesScreenEvents()
    object addItemToFavourites: FavouritesScreenEvents()
    object removeItemFromFavourites: FavouritesScreenEvents()
    object showAddingButton: FavouritesScreenEvents()
    object hideAddingButton: FavouritesScreenEvents()




}
