package com.pogreb.leasingshift.carslist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pogreb.leasingshift.carslist.data.CarsApi
import com.pogreb.leasingshift.carslist.data.converter.CarsItemConverter
import com.pogreb.leasingshift.carslist.data.repository.CarsRepositoryImpl
import com.pogreb.leasingshift.carslist.domain.repository.CarRepository
import com.pogreb.leasingshift.carslist.domain.usecase.GetCarsListUseCase
import com.pogreb.leasingshift.carslist.domain.usecase.GetFoundCarsUseCase
import com.pogreb.leasingshift.carslist.presentation.CarsListViewModel
import com.pogreb.leasingshift.network.NetworkModule

object CarsListProvider {

    private val carsListApi: CarsApi = NetworkModule.retrofit.create(CarsApi::class.java)
    private val carsListItemConverter = CarsItemConverter()
    private val carsListRepository: CarRepository =
        CarsRepositoryImpl(carsListApi, carsListItemConverter)
    val getCarsListUseCase = GetCarsListUseCase(carsListRepository)

    val getFoundCarsUseCase = GetFoundCarsUseCase()
}

@Suppress("UNCHECKED_CAST")
class CarsListViewModelFactory(
    private val getCarsListUseCase: GetCarsListUseCase,
    private val getFoundCarsUseCase: GetFoundCarsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarsListViewModel::class.java)) {
            return CarsListViewModel(
                getCarsListUseCase,
                getFoundCarsUseCase
            ) as T
        }
        throw IllegalArgumentException("It can provide only login viewModel")
    }
}