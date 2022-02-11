package com.example.newweather.Network

import com.example.newweather.Model.City
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getCity(city: String, appId: String): City = apiService.getCityData(city, appId)
}