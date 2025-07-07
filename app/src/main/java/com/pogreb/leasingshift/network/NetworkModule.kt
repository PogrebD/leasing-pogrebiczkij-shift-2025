package com.pogreb.leasingshift.network


import com.pogreb.leasingshift.carinfo.data.CarInfoApi
import com.pogreb.leasingshift.carinfo.data.converter.CarInfoConverter
import com.pogreb.leasingshift.carslist.data.CarsApi
import com.pogreb.leasingshift.carslist.data.converter.CarsItemConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://shift-intensive.ru"
private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

object NetworkModule {


	private val converterFactory = Json { ignoreUnknownKeys = true }.asConverterFactory("application/json; charset=UTF8".toMediaType())


	private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}

	private val okHttpClient = OkHttpClient.Builder()
		.addInterceptor(httpLoggingInterceptor)
		.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
		.build()

	val retrofit = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl(BASE_URL)
		.addConverterFactory(converterFactory)
		.build()
}