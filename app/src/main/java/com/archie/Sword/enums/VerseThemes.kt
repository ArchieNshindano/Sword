package com.archie.Sword.enums

import androidx.compose.ui.graphics.Color
import com.example.Sword.R

enum class VerseThemes( val pictureId: Int, val color: List<Color>, val id: Int) {



    Trust(
        pictureId = R.mipmap.trust,
        color = ColorStrokes.green.color,
        1,

    ),



    Strength(
        pictureId = R.mipmap.strength1,
        ColorStrokes.ember.color.reversed(),
        2
    ),



    Love(
        pictureId = R.mipmap.cross,
        ColorStrokes.red.color,
        3
    ),



    Romance(
        pictureId = R.mipmap.romance,
        ColorStrokes.pink.color,
        3
    ),


    Wisdom(
        pictureId = R.mipmap.wisdom,
        ColorStrokes.lightBlue.color,
        0
    ),

    Sin(
        pictureId = R.mipmap.sin3,
        ColorStrokes.grey.color,
        0
    ),

    Glory(
        pictureId = R.mipmap.glory5,
        ColorStrokes.yellow.color,
        0
    ),




















}