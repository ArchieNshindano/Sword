package com.archie.Sword.enums

import androidx.compose.ui.graphics.Color

enum class VerseThemes(val colorName: String, val colorLevelValues: List<Color>) {



    Trust(
        colorName = "Green",
        colorLevelValues = ColorStrokes.green.color

    ),



    Orange(
        colorName = "Orange",
        colorLevelValues = ColorStrokes.orange.color
    ),






    Love(
        colorName = "Red",
        colorLevelValues = ColorStrokes.red.color
    ),



    Romance(
        colorName = "Pink",
        colorLevelValues = ColorStrokes.pink.color
    ),



    Laziness(
        colorName = "Grey",
        colorLevelValues = ColorStrokes.grey.color
    ),




















}