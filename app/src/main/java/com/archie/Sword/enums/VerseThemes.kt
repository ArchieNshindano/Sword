package com.archie.Sword.enums

import androidx.compose.ui.graphics.Color

enum class VerseThemes(val colorName: String, val colorLevelValues: List<Color>, val id: Int) {



    Trust(
        colorName = "Green",
        colorLevelValues = ColorStrokes.green.color,
        1,

    ),



    Orange(
        colorName = "Orange",
        colorLevelValues = ColorStrokes.orange.color,
        2
    ),






    Love(
        colorName = "Red",
        colorLevelValues = ColorStrokes.red.color,
        3
    ),



    Romance(
        colorName = "Pink",
        colorLevelValues = ColorStrokes.pink.color,
        3
    ),



    Laziness(
        colorName = "Grey",
        colorLevelValues = ColorStrokes.grey.color,
        4
    ),




















}