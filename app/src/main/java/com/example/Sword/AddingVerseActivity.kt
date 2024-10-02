package com.example.Sword

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.archie.Sword.screenViews.addingVerseScreen.addingVerseScreen
import com.archie.Sword.screenViews.addingVerseScreen.eventHandlerForAddingVerse
import com.archie.Sword.viewModels.AddingVerseScreenViewModel
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddingVerseActivity : ComponentActivity() {

    private val viewModel: AddingVerseScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)






        setContent {

            val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)




            val state = viewModel.state.collectAsStateWithLifecycle()

            val theme = sharedPreferences.getString("theme", "Light") ?: "Light"
            val contrast =sharedPreferences.getString("contrast", "Normal") ?: "Normal"
            val lastOpenedTheme = sharedPreferences.getString("lastOpenedTheme", "Light") ?: "Light"
            val isDarkThemeEnabled = sharedPreferences.getBoolean("darkTheme", false)

            val darkTheme = if(theme == "Dark") true else if(theme == "System")  isSystemInDarkTheme() else false

            viewModel.setThemeName(lastOpenedTheme,false)
            viewModel.getSharedPreferences(sharedPreferences)







            SwordTheme(
                verseTheme = if(state.value.isAThemeSelected) state.value.themeName else lastOpenedTheme!!,
                darkTheme = darkTheme,
                contrast = contrast

            ) {

                eventHandlerForAddingVerse(viewModel = viewModel, state = state.value)

                addingVerseScreen(onEvent = viewModel::onEvent, state = state.value)

            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {

}