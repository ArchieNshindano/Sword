package com.archie.Sword.screenViews.themeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.Sword.ui.theme.SwordTheme


@Composable
fun stickyHeader(title: String){


    SwordTheme(verseTheme = title, containerDoesNotAffectStatusBar = true) {

        Column(
            modifier = Modifier
        ) {
            Text(
                text = " "+ title,
                fontSize = 25.sp,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface

            )

        }

    }



}