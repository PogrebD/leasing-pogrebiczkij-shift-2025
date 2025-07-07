package com.pogreb.leasingshift.main.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pogreb.leasingshift.main.entity.enums.NavigationOption
import com.pogreb.leasingshift.main.presentation.MainViewModel
import com.pogreb.leasingshift.ui.theme.BGSecondary
import com.pogreb.leasingshift.ui.theme.BorderMedium
import com.pogreb.leasingshift.ui.theme.Brand
import com.pogreb.leasingshift.ui.theme.TextQuartenery


@Composable
fun BottomNavigation(
    mainViewModel: MainViewModel,
    selectedNavigationOption: NavigationOption,
    onItemClicked: (NavigationOption) -> Unit
) {
    NavigationBar(
        containerColor = BGSecondary
    ) {
        for (option in NavigationOption.entries) {
            NavigationBarItem(

                colors = NavigationBarItemColors(
                    selectedIconColor = Brand,
                    selectedTextColor = Brand,
                    selectedIndicatorColor = BGSecondary,
                    unselectedIconColor = BorderMedium,
                    unselectedTextColor = TextQuartenery,
                    disabledIconColor = BorderMedium,
                    disabledTextColor = TextQuartenery,
                ),
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = { Icon(mainViewModel.getIcon(option), "") },
                label = { Text(text =  stringResource(mainViewModel.getLabel(option))) }
            )
        }
    }
}