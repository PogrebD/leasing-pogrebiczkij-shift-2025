package com.pogreb.leasingshift.main.ui

import androidx.navigation.NavController


fun NavController.navigateToRoot(route: Any) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}