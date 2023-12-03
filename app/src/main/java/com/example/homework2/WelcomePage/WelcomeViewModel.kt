package com.example.homework2.WelcomePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework2.DTO.WeatherDTO
import com.example.homework2.Repo.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class WelcomeViewModel : ViewModel() {
    private val repository = WeatherRepository()
    private val _liveDataWeather = MutableLiveData<Response<WeatherDTO>>()
    val liveDataWeather: LiveData<Response<WeatherDTO>> = _liveDataWeather

    fun loadWeatherFromCurrentCity(q: String, apiKey: String) {
        try {
            viewModelScope.launch {
                val res = repository.loadWeather(q, apiKey)
                _liveDataWeather.postValue(if (res.isSuccessful) res else null)
            }
        } catch (ex: Exception) {
            Log.e("CoroutineException", "Exception: ${ex.message}")
        }
    }
}
