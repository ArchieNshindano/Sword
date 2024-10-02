@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.Sword.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun SwordTheme(

    verseTheme: String,
    containerDoesNotAffectStatusBar: Boolean = false,
    contrast: String = "Normal",
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic colorLevelValues is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {



    val trustDarkScheme =

        if( contrast == "Medium")
            trustMediumContrastDarkColorScheme

        else if(contrast == "High")
            trustHighContrastDarkColorScheme

        else
            trustDarkScheme


    val trustLightTheme =

        if( contrast == "Medium")
            trustMediumContrastLightColorScheme

        else if(contrast == "High")
            trustHighContrastLightColorScheme

        else
            trustLightScheme







    val strengthDarkScheme =

        if(contrast == "Medium")
            strengthMediumContrastDarkColorScheme

        else if(contrast == "High")
            strengthHighContrastDarkColorScheme

        else
            strengthDarkScheme


    val strengthLightScheme =

        if(contrast == "Medium")
            strengthMediumContrastLightColorScheme

        else if(contrast == "High")
            strengthHighContrastLightColorScheme

        else
            strengthLightScheme






    val loveDarkScheme =

        if(contrast == "Medium")
            loveMediumContrastDarkColorScheme

        else if(contrast == "High")
            loveHighContrastDarkColorScheme

        else
            loveDarkScheme


    val loveLightScheme =

        if(contrast == "Medium")
            loveMediumContrastLightColorScheme

        else if(contrast == "High")
            loveHighContrastLightColorScheme

            else
                loveLightScheme






    val romanceDarkScheme =

            if(contrast == "Medium")
                romanceMediumContrastDarkColorScheme

            else if(contrast == "High")
                romanceHighContrastDarkColorScheme

            else
                romanceDarkScheme



    val romanceLightScheme =

                if(contrast == "Medium")
                    romanceMediumContrastLightColorScheme

                else if(contrast == "High")
                    romanceHighContrastLightColorScheme

                else
                    romanceLightScheme






    val wisdomDarkScheme =

                    if(contrast == "Medium")
                        wisdomMediumContrastDarkColorScheme

                    else if(contrast == "High")
                        wisdomHighContrastDarkColorScheme

                    else
                        wisdomDarkScheme


    val wisdomLightScheme =

       if(contrast == "Medium")
           wisdomMediumContrastLightColorScheme

        else if(contrast == "High")
            wisdomHighContrastLightColorScheme


        else wisdomLightScheme






    val sinDarkScheme =

            if(contrast == "Medium")
                sinMediumContrastDarkColorScheme

            else if(contrast == "High")
                sinHighContrastDarkColorScheme

            else sinDarkScheme


    val sinLightScheme =

                   if(contrast == "Medium")
                       sinMediumContrastLightColorScheme

                    else if(contrast == "High")
                        sinHighContrastLightColorScheme

                    else sinLightScheme







    val gloryDarkScheme =

        if(contrast == "Medium")
            gloryMediumContrastDarkColorScheme

        else if(contrast == "High")
            gloryHighContrastDarkColorScheme

            else gloryDarkScheme


    val gloryLightScheme =

        if(contrast == "Medium")
            gloryMediumContrastLightColorScheme

        else if(contrast == "High")
            gloryHighContrastLightColorScheme


        else gloryLightScheme








    val colorScheme =


        when (verseTheme) {
            "Trust" -> if (darkTheme) trustDarkScheme  else trustLightTheme
            "Strength" -> if (darkTheme) strengthDarkScheme else strengthLightScheme
            "Love" -> if (darkTheme) loveDarkScheme else loveLightScheme
            "Romance" -> if (darkTheme) romanceDarkScheme else romanceLightScheme
            "Wisdom" -> if (darkTheme) wisdomDarkScheme else wisdomLightScheme
            "Sin" -> if (darkTheme) sinDarkScheme else sinLightScheme
            "Glory" -> if (darkTheme) gloryDarkScheme else gloryLightScheme

            else -> if(dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

            }

            else
                if (darkTheme) gloryDarkScheme else gloryLightScheme

        }

    val view = LocalView.current

    if (!view.isInEditMode && !containerDoesNotAffectStatusBar) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}