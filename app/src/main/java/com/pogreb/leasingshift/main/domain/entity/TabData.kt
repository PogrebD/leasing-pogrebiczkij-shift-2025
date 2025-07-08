package com.pogreb.leasingshift.main.domain.entity

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val option: NavigationOption,
    val icon: ImageVector,
    @StringRes val labelRes: Int
)