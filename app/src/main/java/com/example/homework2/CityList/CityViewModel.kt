package com.example.homework2.CityList

import com.example.homework2.DTO.WeatherDTO
import com.example.homework2.Repo.WeatherRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {

    private val weatherRepository = WeatherRepository()
    private val _weatherDataLiveData = MutableLiveData<HashMap<String, retrofit2.Response<WeatherDTO>>>()
    val weatherDataLiveData: LiveData<HashMap<String, retrofit2.Response<WeatherDTO>>> = _weatherDataLiveData

    init {
        _weatherDataLiveData.value = HashMap()
    }

    private val cityList = listOf(
        CityModel("Yerevan", description = ""),
        CityModel("Washington", description = ""),
        CityModel("Madrid", description = ""),
        CityModel("Paris", description = ""),
    )

    fun fetchWeather(cityName: String, apiKey: String) {
        try {
            viewModelScope.launch {
                val weatherResponse = weatherRepository.loadWeather(cityName, apiKey)
                var newWeatherDataMap = HashMap<String, retrofit2.Response<WeatherDTO>>()
                if (_weatherDataLiveData.value != null) {
                    newWeatherDataMap = _weatherDataLiveData.value?.let { HashMap(it) }!!
                }

                newWeatherDataMap[cityName] = weatherResponse
                _weatherDataLiveData.postValue(newWeatherDataMap)
            }
        } catch (ex: Exception) {
            Log.e("CoroutineException", "Exception: ${ex.message}")
        }
    }

    public fun getCities(): List<CityModel> {
        return cityList
    }
}