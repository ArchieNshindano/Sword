package com.archie.Sword.screenViews.addingVerseScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.archie.Sword.events.AddingVerseScreenEvents
import com.archie.Sword.states.AddingVerseScreenStates
import com.archie.Sword.viewModels.AddingVerseScreenViewModel



@Composable
fun eventHandlerForAddingVerse(state: AddingVerseScreenStates, viewModel: AddingVerseScreenViewModel){

//    val state = remember {
//
//        _state
//    }
//
//    val viewModel = remember {
//
//        _viewModel
//    }

    LaunchedEffect(state.event){



        val event = state.event

        Log.d("Events Here", event.toString())
        Log.d("States Event", state.event.toString())

        when(event){
            AddingVerseScreenEvents.hidePopUpMenu ->  viewModel.hidePopUpMenu()
            AddingVerseScreenEvents.saveVerse -> viewModel.saveVerse()
            AddingVerseScreenEvents.showPopUpMenu -> viewModel.showPopUpMenu()
            AddingVerseScreenEvents.ShowBookSelectionDialog -> viewModel.showBookSelectionDialog()
            AddingVerseScreenEvents.HideBookSelectionDialog -> viewModel.hideBookSelectionDialog()
            AddingVerseScreenEvents.HideChapterSelectionDialog -> viewModel.hideChapterSelectionDialog()
            AddingVerseScreenEvents.HideVerseSelectionDialog -> viewModel.hideVerseSelectionDialog()
            is AddingVerseScreenEvents.SetBookName -> viewModel.setBookName(event.bookName)
            is AddingVerseScreenEvents.SetChapter -> viewModel.setChapter(event.chapter)
            is AddingVerseScreenEvents.SetNote -> viewModel.setNote(event.note)
            is AddingVerseScreenEvents.SetThemeColor -> viewModel.setThemeColour(event.color)
            is AddingVerseScreenEvents.SetThemeName -> viewModel.setThemeName(event.theme, event.isThemeSelected)
            is AddingVerseScreenEvents.SetVerse -> viewModel.setVerse(event.verse)
            is AddingVerseScreenEvents.SetVerseNumber -> viewModel.setVerseNumber(event.verseNumber)
            AddingVerseScreenEvents.ShowChapterSelectionDialog -> viewModel.showChapterSelectionDialog()
            AddingVerseScreenEvents.ShowVerseSelectionDialog -> viewModel.showVerseSelectionDialog()
            is AddingVerseScreenEvents.SetBookPosition -> viewModel.setBookPosition(event.position)
        }



    }
}



