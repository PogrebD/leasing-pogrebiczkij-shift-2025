package com.pogreb.leasingshift.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pogreb.leasingshift.R
import com.pogreb.leasingshift.carinfo.CarInfoRoute
import com.pogreb.leasingshift.carinfo.di.CarInfoProvider
import com.pogreb.leasingshift.carinfo.di.CarInfoViewModelFactory
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.carinfo.ui.CarInfoScreen
import com.pogreb.leasingshift.carslist.CarsListRoute
import com.pogreb.leasingshift.carslist.di.CarsListProvider
import com.pogreb.leasingshift.carslist.di.CarsListViewModelFactory
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.carslist.ui.CarsListScreen
import com.pogreb.leasingshift.main.entity.enums.NavigationOption
import com.pogreb.leasingshift.orders.OrdersRoute
import com.pogreb.leasingshift.orders.OrdersScreen
import com.pogreb.leasingshift.profile.ProfileRoute
import com.pogreb.leasingshift.profile.ProfileScreen
import com.pogreb.leasingshift.ui.theme.BGSecondary
import com.pogreb.leasingshift.ui.theme.BorderMedium
import com.pogreb.leasingshift.ui.theme.Brand
import com.pogreb.leasingshift.ui.theme.TextQuartenery

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val selectedTab = rememberSaveable { mutableStateOf(NavigationOption.CARSLIST) }

    Scaffold() { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = CarsListRoute,
            ) {
                composable<CarsListRoute> {
                    val carsListViewModel: CarsListViewModel = viewModel(
                        factory = CarsListViewModelFactory(
                            CarsListProvider.getCarsListUseCase,
                            CarsListProvider.getFoundCarsUseCase,
                        )
                    )

                    CarsListScreen(
                        carsListViewModel = carsListViewModel,
                        onItemClick = { carId ->
                            navController.navigate(CarInfoRoute(carId))
                        }
                    )
                }
                composable<CarInfoRoute> {
                    val destination = it.toRoute<CarInfoRoute>()
                    val carInfoViewModel: CarInfoViewModel = viewModel(
                        factory = CarInfoViewModelFactory(
                            CarInfoProvider.getCarInfoUseCase,
                            destination.carId,
                        )
                    )

                    CarInfoScreen(
                        carInfoViewModel = carInfoViewModel,
                        carId = destination.carId,
                        onReserveClick = {},
                        onBackClick = { navController.navigate(route = CarsListRoute) },
                    )
                }
                composable<OrdersRoute> {
                    OrdersScreen()
                }
                composable<ProfileRoute> {
                    ProfileScreen()
                }
            }
            BottomNavigation(
                navigationOptions = NavigationOption.entries,
                selectedNavigationOption = selectedTab.value,
                onItemClicked = { navOption ->
                    when (navOption) {
                        NavigationOption.CARSLIST -> navController.openPoppingAllPrevious(
                            CarsListRoute
                        )

                        NavigationOption.ORDERS -> navController.openPoppingAllPrevious(
                            OrdersRoute
                        )

                        NavigationOption.PROFILE -> navController.openPoppingAllPrevious(
                            ProfileRoute
                        )
                    }

                    selectedTab.value = navOption
                }
            )
        }
    }
}

@Composable
private fun BottomNavigation(
    navigationOptions: List<NavigationOption>,
    selectedNavigationOption: NavigationOption,
    onItemClicked: (NavigationOption) -> Unit,
) {
    NavigationBar(containerColor = BGSecondary
    ) {
        for (option in navigationOptions) {
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
                icon = { Icon(getIcon(option), "") },
                label = { Text(text = getLabel(option)) }
            )
        }
    }
}

private fun getIcon(option: NavigationOption): ImageVector =
    when (option) {
        NavigationOption.CARSLIST -> Icons.Default.ShoppingCart
        NavigationOption.ORDERS -> Icons.Default.Favorite
        NavigationOption.PROFILE -> Icons.Default.AccountCircle
    }

@Composable
private fun getLabel(option: NavigationOption): String = stringResource(
    when (option) {
        NavigationOption.CARSLIST -> R.string.cars
        NavigationOption.ORDERS -> R.string.orders
        NavigationOption.PROFILE -> R.string.profile
    }
)

fun NavController.openPoppingAllPrevious(route: Any) {
    this.navigate(route) {
        popUpTo(graph.startDestinationId)
        launchSingleTop = true
    }
}