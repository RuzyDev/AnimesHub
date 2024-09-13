package com.ruzy.animeshub.app.navigation

import AnimesHubIcons

data class TopLevelDestination(
    val routeNavigation: String,
    val icon: AnimesHubIcons,
    val text: Int
) : AnimesHubNavigation(routeNavigation)
