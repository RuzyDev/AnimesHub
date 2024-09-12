package com.ruzy.animeshub.android.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ruzy.animeshub.app.navigation.AnimesHubNavigation

object HomeNavigation: AnimesHubNavigation("home")

fun NavGraphBuilder.home(){
    composable(
        route = HomeNavigation.getRoute()
    ) {
        HomeRoute()
    }
}