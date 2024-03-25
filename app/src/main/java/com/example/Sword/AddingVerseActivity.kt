package com.example.Sword

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.archie.Sword.events.AddingVerseScreenEvents
import com.archie.Sword.screenViews.addingVerseScreen.addingVerseScreen
import com.archie.Sword.screenViews.addingVerseScreen.addingVerseScreenLaunched
import com.archie.Sword.screenViews.addingVerseScreen.eventHandler
import com.archie.Sword.viewModels.AddingVerseScreenViewModel
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddingVerseActivity : ComponentActivity() {

    private val viewModel: AddingVerseScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {



            SwordTheme {
                // A surface container using the 'background' colorLevelValues from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                     val state = viewModel.state.collectAsStateWithLifecycle()



                  addingVerseScreen(onEvent = viewModel::onEvent, state = state.value)


//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SwordTheme {
        Greeting("Android")
    }
}