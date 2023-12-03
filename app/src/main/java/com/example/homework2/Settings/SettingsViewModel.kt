package com.example.homework2.Settings

import com.example.homework2.DTO.Temperature
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val _selectedTemperatureUnit = MutableStateFlow(Temperature.C)
    val selectedTemperatureUnit: MutableStateFlow<Temperature> = _selectedTemperatureUnit

    fun updateTemperatureUnit(unit: Temperature) {
        viewModelScope.launch {
            _selectedTemperatureUnit.emit(unit)
        }
    }

    fun saveTemperatureUnitPreference(context: Context, temperatureUnit: Temperature) {
        val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selected_temperature_unit", temperatureUnit.name)
        editor.apply()
    }

    fun getTemperatureUnitPreference(context: Context): Temperature {
        val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val savedUnit = sharedPreferences.getString("selected_temperature_unit", Temperature.C.name)
        return Temperature.valueOf(savedUnit ?: Temperature.C.name)
    }
}