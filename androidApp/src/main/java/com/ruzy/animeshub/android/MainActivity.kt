package com.ruzy.animeshub.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.ruzy.animeshub.app.AnimesHubApp
import com.ruzy.animeshub.designsystem.theme.AnimesHubTheme
import com.ruzy.animeshub.presentation.HomeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)

            AnimesHubTheme {
                AnimesHubApp(windowSizeClass = windowSizeClass)
            }
        }
    }
}