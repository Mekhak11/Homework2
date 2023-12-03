package com.example.homework2.DTO
import com.google.gson.annotations.SerializedName

data class WeatherDTO (
    @SerializedName("name") val city:String?,
    @SerializedName("current") val temp: TemperatureDTO?
)
data class TemperatureDTO(
    @SerializedName("temp_c") val degreesC: String?,
    @SerializedName("temp_f") val degreesF: String?
)