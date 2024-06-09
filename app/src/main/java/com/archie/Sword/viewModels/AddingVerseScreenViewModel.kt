package com.archie.Sword.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.archie.Sword.events.AddingVerseScreenEvents
import com.archie.Sword.repositories.database.DataBaseRepositoryImpl
import com.archie.Sword.repositories.database.Verse
import com.archie.Sword.states.AddingVerseScreenStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddingVerseScreenViewModel @Inject constructor(

    private val daoFunctions: DataBaseRepositoryImpl

): ViewModel() {





    private val _state = MutableStateFlow(AddingVerseScreenStates())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddingVerseScreenStates())




    fun saveVerse() {

//        if (_state.value.bookName.isBlank() || _state.value.verse.isBlank() || _state.value.themeName.isBlank() || _state.value.photoFilePath.isBlank() || _state.value.note.isBlank())
//            return


        val verseTag = _state.value.bookName +" "+_state.value.chapter+":"+_state.value.verseNumber

        val verse =  Verse(
            verseTag = verseTag,
            verse = _state.value.verse,
            date = System.currentTimeMillis(),
            themeName = _state.value.themeName,
            bookPosition = _state.value.bookPosition,
            photoFilePath = _state.value.photoFilePath,
            themeColor = state.value.themeColour,
            note = state.value.note,
            isPartOfFavorites = 0,
            memorisedToday = 0,
            memorisedCount = 0,
            memorisedTodayDate = null


        ) // VERSE ENDS

        viewModelScope.launch {


            daoFunctions.addVerse(verse)

        } // SCOPE ENDS


//        _state.update {
//
//            it.copy(
//
//                bookName = "",
//                chapterAndVerseNumber = "",
//                verse = "",
//                bookPosition = 0,
//
//                note = "",
//
//                themeName = "",
//                themeColour = "",
//
//                photoFilePath = "",
//
//            ) // COPY ENDS
//
//        } // UPDATE ENDS





    }




    fun onEvent(event: AddingVerseScreenEvents){

        when(event){
            AddingVerseScreenEvents.hidePopUpMenu ->  hidePopUpMenu()
            AddingVerseScreenEvents.saveVerse ->   saveVerse()
            AddingVerseScreenEvents.showPopUpMenu ->  showPopUpMenu()
            AddingVerseScreenEvents.ShowBookSelectionDialog -> showBookSelectionDialog()
            AddingVerseScreenEvents.HideBookSelectionDialog -> hideBookSelectionDialog()
            AddingVerseScreenEvents.HideChapterSelectionDialog -> hideChapterSelectionDialog()
            AddingVerseScreenEvents.HideVerseSelectionDialog ->  hideVerseSelectionDialog()
            is AddingVerseScreenEvents.SetBookName ->  setBookName(event.bookName)
            is AddingVerseScreenEvents.SetChapter ->  setChapter(event.chapter)
            is AddingVerseScreenEvents.SetNote ->  setNote(event.note)
            is AddingVerseScreenEvents.SetThemeColor -> setThemeColour(event.color)
            is AddingVerseScreenEvents.SetThemeName -> setThemeName(event.theme, event.isThemeSelected)
            is AddingVerseScreenEvents.SetVerse -> setVerse(event.verse)
            is AddingVerseScreenEvents.SetVerseNumber -> setVerseNumber(event.verseNumber)
            AddingVerseScreenEvents.ShowChapterSelectionDialog -> showChapterSelectionDialog()
            AddingVerseScreenEvents.ShowVerseSelectionDialog ->showVerseSelectionDialog()
            is AddingVerseScreenEvents.SetBookPosition -> setBookPosition(event.position)
        }

    }








    fun showPopUpMenu(){


        _state.update { it.copy(showingPopupMenu = true) }


    }

    fun hidePopUpMenu(){


        _state.update {    it.copy(showingPopupMenu = false)    }


    }



    fun setBookName(book: String){

        _state.update {    it.copy(bookName = book)     }


    }



    fun showBookSelectionDialog(){

        _state.update {    it.copy(isBookSelectionDialogShowing = true)     }
    }





    fun hideBookSelectionDialog(){

        _state.update {    it.copy(isBookSelectionDialogShowing = false)     }
    }







    fun showChapterSelectionDialog(){

        _state.update {    it.copy(isChapterSelectionDialogShowing = true)     }
    }





    fun hideChapterSelectionDialog(){

        _state.update {    it.copy(isChapterSelectionDialogShowing = false)     }
    }




    fun showVerseSelectionDialog(){

        _state.update {    it.copy(isVerseSelectionDialogShowing = true)     }
    }





    fun hideVerseSelectionDialog(){

        _state.update {    it.copy(isVerseSelectionDialogShowing = false)     }
    }






    fun setChapter(chapter: String){

        _state.update {    it.copy(chapter = chapter)     }

    }







    fun setVerseNumber(verseNumber: String){

        _state.update {    it.copy(verseNumber = verseNumber)     }

    }





    fun setVerse(verse: String){

        _state.update {    it.copy(verse = verse)     }

    }


    fun setNote(note: String){

        _state.update {    it.copy(note = note)     }
    }


    fun setThemeName(themeName: String, isThemeSelected: Boolean){

        _state.update {    it.copy(themeName = themeName, isAThemeSelected = isThemeSelected)     }


 }


    fun setThemeColour(colour: String){

        _state.update {    it.copy(themeColour = colour)     }

    }


    fun setPhotoFilePath(path: String){

        _state.update {    it.copy(photoFilePath = path)     }

    }



    fun setConditionForThemeExistence(condition: Boolean){

        _state.update {    it.copy(isAThemeSelected = condition)     }
    }


    fun setBookPosition(bookPosition: Byte) = _state.update { it.copy(bookPosition = bookPosition) }


















}