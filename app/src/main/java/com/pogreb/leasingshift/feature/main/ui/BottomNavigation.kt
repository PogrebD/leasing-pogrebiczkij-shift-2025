package com.pogreb.leasingshift.feature.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pogreb.leasingshift.feature.main.domain.entity.NavigationOption
import com.pogreb.leasingshift.feature.main.presentation.TabDataProvider
import com.pogreb.leasingshift.ui.theme.BGSecondary
import com.pogreb.leasingshift.ui.theme.BorderMedium
import com.pogreb.leasingshift.ui.theme.Brand
import com.pogreb.leasingshift.ui.theme.TextQuartenery


@Composable
fun BottomNavigation(
    navController: NavController,
    onItemClicked: (NavigationOption) -> Unit
) {
    NavigationBar(
        containerColor = BGSecondary,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        TabDataProvider.getTabs().forEach { tabItem ->
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
                selected = currentRoute == TabDataProvider.getRouteMap()[tabItem.option],
                onClick = { onItemClicked(tabItem.option) },
                icon = { Icon(tabItem.icon, "") },
                label = { Text(text = stringResource(tabItem.labelRes)) }
            )
        }
    }
}