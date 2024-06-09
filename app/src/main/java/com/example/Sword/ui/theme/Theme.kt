package com.example.Sword.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.archie.Sword.enums.VerseThemes

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
    isContainerVerseHolder: Boolean = false,
    darkTheme: Boolean =isSystemInDarkTheme(),
    // Dynamic colorLevelValues is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {



    val colorScheme =

        when (verseTheme) {
            "Trust" -> if (darkTheme) trustDarkScheme else trustLightScheme
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

    if (!view.isInEditMode && !isContainerVerseHolder) {
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