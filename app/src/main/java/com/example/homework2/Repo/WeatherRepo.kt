package com.example.homework2.Repo


import com.example.homework2.DTO.WeatherDTO
import retrofit2.Response
import com.example.homework2.Retrofit.RetrofitConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/current.json")
    suspend fun fetchWeather(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ): Response<WeatherDTO>
}

class WeatherRepository {
    suspend fun loadWeather(q: String, apiKey: String): Response<WeatherDTO> {
        val apiService = RetrofitConfig.getRetrofit().create(WeatherApiService::class.java)
        return apiService.fetchWeather(
            q,
            apiKey,
        )
    }
}