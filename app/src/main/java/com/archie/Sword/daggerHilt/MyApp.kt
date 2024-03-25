package com.archie.Sword.daggerHilt

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class MyApp: Application() {


    val topDp = mutableStateOf(0.dp)
    val bottomDp = mutableStateOf(0.dp)
}

