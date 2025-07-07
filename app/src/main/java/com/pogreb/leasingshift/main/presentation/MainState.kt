package com.pogreb.leasingshift.main.presentation

import androidx.navigation.NavHostController
import com.pogreb.leasingshift.main.entity.enums.NavigationOption

data class MainState(
    val navController: NavHostController,
    val selectedTab: NavigationOption,
)