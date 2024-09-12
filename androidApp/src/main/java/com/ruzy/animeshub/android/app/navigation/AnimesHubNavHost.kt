package com.ruzy.animeshub.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ruzy.animeshub.android.ui.home.HomeNavigation
import com.ruzy.animeshub.android.ui.home.home
import kotlin.reflect.KFunction1

@Composable
fun AnimesHubNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeNavigation.getRoute(),
    onBackClickWithDestination: KFunction1<String, Unit>,
    navigate: (AnimesHubNavigation, String?) -> Unit,
    navigateAndDestroy: (String) -> Unit,
    navigateAndDestroyAll: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        //Home
        home()
    }
}