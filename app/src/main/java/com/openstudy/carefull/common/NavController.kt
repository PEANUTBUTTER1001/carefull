package com.openstudy.carefull.common

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination


fun NavController.navigateWithPopUp(
    toRoute: String,   // 이동할 스크린
    fromRoute: String, // 현재 스크린
) {
    this.navigate(toRoute) {
        popUpTo(fromRoute) {
            inclusive = true
        }
    }
}

// 홈 화면 이동(이전 스택 제거)
fun NavController.navigateToHome() {
    this.navigate(NavigationRoute.Home.route) {
        popUpTo(this@navigateToHome.graph.findStartDestination().id) {
            inclusive = true
        }
        launchSingleTop = true
    }
}