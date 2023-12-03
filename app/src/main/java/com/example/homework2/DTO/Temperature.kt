package com.example.homework2.DTO

enum class Temperature(val unitName:String) {
    C("°C"),
    F("°F");
    fun formatTemperature(): String {
        return "$unitName"
    }
}