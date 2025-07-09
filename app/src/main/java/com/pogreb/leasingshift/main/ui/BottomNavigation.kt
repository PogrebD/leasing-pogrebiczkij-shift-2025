package com.pogreb.leasingshift.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pogreb.leasingshift.main.domain.entity.NavigationOption
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
        containerColor = BGSecondary,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        mainViewModel.tabs.forEach { tabItem ->
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
                selected = selectedNavigationOption == tabItem.option,
                onClick = { onItemClicked(tabItem.option) },
                icon = { Icon(tabItem.icon, "") },
                label = { Text(text = stringResource(tabItem.labelRes)) }
            )
        }
    }
}