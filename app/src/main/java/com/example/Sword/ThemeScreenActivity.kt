package com.example.Sword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.archie.Sword.viewModels.ThemeScreenViewModel
import com.example.Sword.ui.theme.SwordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeActivity : ComponentActivity() {

    private val viewModel: ThemeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state = viewModel.state.collectAsStateWithLifecycle()
            SwordTheme("Trust") {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {

}