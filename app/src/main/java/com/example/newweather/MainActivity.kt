package com.example.newweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newweather.Repository.WeatherRepository
import com.example.newweather.ViewModel.WeatherViewModel
import com.example.newweather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel.getCityData()
        initListener()
        weatherViewModel.weatherResponse.observe(this, Observer { response ->

            if (response.weather[0].description == "clear sky" || response.weather[0].description == "mist") {
                Glide.with(this)
                    .load(R.drawable.clouds)
                    .into(image)
            } else
                if (response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog") {
                    Glide.with(this)
                        .load(R.drawable.haze)
                        .into(image)
                } else
                    if (response.weather[0].description == "rain") {
                        Glide.with(this)
                            .load(R.drawable.rain)
                            .into(image)
                    }
            description.text = response.weather[0].description
            name.text = response.name
            speed.text = response.wind.speed.toString() + "Km/h"
            val temperature = response.main.temp - 273.15
            val roundedValue = Math.rint(temperature).toInt()
            temp.text = roundedValue.toString() + " °C"
            humidity.text = response.main.humidity.toString() + "%"

        })
    }

    private fun initListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("main", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}