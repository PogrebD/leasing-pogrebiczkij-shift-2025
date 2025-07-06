package com.pogreb.leasingshift.carinfo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pogreb.leasingshift.carinfo.data.CarInfoApi
import com.pogreb.leasingshift.carinfo.data.converter.CarInfoConverter
import com.pogreb.leasingshift.carinfo.data.repository.CarInfoRepositoryImpl
import com.pogreb.leasingshift.carinfo.domain.repository.CarInfoRepository
import com.pogreb.leasingshift.carinfo.domain.usecase.GetCarInfoUseCase
import com.pogreb.leasingshift.carinfo.presentation.CarInfoViewModel
import com.pogreb.leasingshift.network.NetworkModule

object CarInfoProvider {

    private val carInfoApi: CarInfoApi = NetworkModule.retrofit.create(CarInfoApi::class.java)
    private val carInfoConverter = CarInfoConverter()
    private val carInfoRepository: CarInfoRepository =
        CarInfoRepositoryImpl(carInfoApi, carInfoConverter)
    val getCarInfoUseCase = GetCarInfoUseCase(carInfoRepository)
}

@Suppress("UNCHECKED_CAST")
class CarInfoViewModelFactory(
    private val getCarInfoUseCase: GetCarInfoUseCase,
    private val id: Long,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarInfoViewModel::class.java)) {
            return CarInfoViewModel(id, getCarInfoUseCase) as T
        }
        throw IllegalArgumentException("It can provide only login viewModel")
    }
}