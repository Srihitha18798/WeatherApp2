package com.example.newweather.Repository

import com.example.newweather.Model.City
import com.example.newweather.Network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getCityData(city: String): Flow<City> = flow {
        val response = apiServiceImpl.getCity(city, "18051feb4c8a1cafb718d2170ed92a10")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}