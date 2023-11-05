package com.example.homework2.CityList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CityViewModel : ViewModel() {
    private val _cityList = MutableLiveData<List<CityModel>>()
    val cityList: LiveData<List<CityModel>> get() = _cityList

    init {
        _cityList.value = listOf(
            CityModel("Yerevan", "Capital of Armenia"),
            CityModel("Washington", "Capital of the United States"),
            CityModel("Madrid", "Capital of Spain")
        )
    }
}
