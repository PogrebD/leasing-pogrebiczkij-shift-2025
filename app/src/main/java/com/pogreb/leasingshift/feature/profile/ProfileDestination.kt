package com.pogreb.leasingshift.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.profile() {
    composable<ProfileRoute> {
        ProfileScreen()
    }
}