package com.archie.Sword.helperFunctions


import androidx.compose.ui.graphics.Color


val String.color
    get() = Color(android.graphics.Color.parseColor(this))



