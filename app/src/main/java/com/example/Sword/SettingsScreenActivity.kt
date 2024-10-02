package com.example.Sword

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.archie.Sword.events.SettingsScreenEvents
import com.archie.Sword.screenViews.settingsScreen.SettingsScreen
import com.archie.Sword.screenViews.settingsScreen.eventHandlerForSettings
import com.archie.Sword.viewModels.SettingsScreenViewModel
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map



private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class SettingsScreenActivity : ComponentActivity() {

    private val viewModel: SettingsScreenViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)









//        CoroutineScope(Dispatchers.Default).launch{
//
//
//
//        }



        setContent {

            val sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)





            val theme = sharedPreferences.getString("theme", "Light") ?: "Light"
            val contrast =sharedPreferences.getString("contrast", "Normal") ?: "Normal"
            val sortType =sharedPreferences.getString("sortType", "By book") ?: "By book"
            val isDynamicThemeEnabled = sharedPreferences.getBoolean("isDynamicThemeEnabled", false)
            val isDynamicSentencesEnabled = sharedPreferences.getBoolean("isDynamicSentencesEnabled", false)
            val isVerseOfTheDayEnabled =sharedPreferences.getBoolean("isVerseOfTheDayEnabled", false)
            val lastOpenedTheme = sharedPreferences.getString("lastOpenedTheme", "Light") ?: "Light"

            viewModel.setSystemThemeTo(theme)
            viewModel.setContrastTo(contrast)
            viewModel.setSortTypeTo(sortType)

            viewModel.toggleDynamicThemeButton(isDynamicThemeEnabled)

            viewModel.toggleDynamicSentenceButton(isDynamicSentencesEnabled)
            viewModel.toggleVerseOfTheDayButton(isVerseOfTheDayEnabled)


            val state = viewModel.state.collectAsStateWithLifecycle().value
            val darkTheme = if(theme == "Dark") true else if(theme == "System")  isSystemInDarkTheme() else false


            SwordTheme(
                verseTheme = lastOpenedTheme,
                darkTheme = darkTheme,
                contrast = state.contrast,
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    eventHandlerForSettings(state = state, viewModel = viewModel)



                    SettingsScreen(viewModel::onEvent, state, sharedPreferences)

                }
            }
        }
    }




    suspend fun writeToKey(keyName: String, valueOfKey: Any){


        this.dataStore.edit { settings ->

            when(valueOfKey){

                is String -> {
                    val keyPreference = stringPreferencesKey(keyName)
                    settings[keyPreference] = valueOfKey
                }
                is Int -> {

                    val keyPreference = intPreferencesKey(keyName)
                    settings[keyPreference] = valueOfKey
                }

                is Boolean -> {

                    val keyPreference = booleanPreferencesKey(keyName)
                    settings[keyPreference] = valueOfKey

                }


            }

        }


    }


    fun readFromKey(key: String): String{

        val keyPreference = stringPreferencesKey(key)
        var value = ""

        this.dataStore.data.map { settings ->

               value =  settings[keyPreference] ?: ""

            }


        return value

        }




}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {


    SwordTheme("Love") {
        // A surface container using the 'background' color from the theme


            SettingsScreen(sharedPreferences = LocalContext.current.getSharedPreferences("settings", Context.MODE_PRIVATE))


}

}