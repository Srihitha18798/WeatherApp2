package com.example.newweather.Model

data class City(
    val weather: ArrayList<Weather>,
    val main: Main,
    val wind: Wind,
    val name: String
)