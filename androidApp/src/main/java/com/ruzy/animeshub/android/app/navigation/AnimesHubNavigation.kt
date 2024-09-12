package com.ruzy.animeshub.app.navigation

abstract class AnimesHubNavigation(private val route: String) {
    open fun getRoute() = route
}