package com.example.homework2.CityList

import com.example.homework2.DTO.Temperature
import com.example.homework2.DTO.WeatherDTO
import com.example.homework2.Settings.SettingsViewModel
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import retrofit2.Response

enum class LoadingState { Idle, Loading, Loaded, Error }

@Composable
fun CityListView(
    navController: NavController,
    context: Context,
    viewModel: CityViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    settingsViewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
    val cityWeather = viewModel.weatherDataLiveData.observeAsState()
    val cities = viewModel.getCities()
    var callsAreMade = false

    if (cityWeather.value?.size != cities.size && !callsAreMade) {
        for (city in cities) {
            fetchCity(viewModel, city.name)
        }
        callsAreMade = true
    }

    var loadingState by remember { mutableStateOf(LoadingState.Loaded) }

    LaunchedEffect(Unit) {
        loadingState = LoadingState.Loading

        var success = false
        if (cityWeather.value?.size != cities.size) {
            delay(3500)
            success = cityWeather.value?.size == cities.size
        }

        loadingState = if (success) LoadingState.Loaded else LoadingState.Error
    }

    when (loadingState) {
        LoadingState.Idle -> {
            // Initial state (optional)
        }

        LoadingState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        LoadingState.Loaded -> {
            Column {
                Text(
                    text = "Explore Cities",
                    fontSize = 21.sp,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Green // Change color as needed
                )
                Spacer(modifier = Modifier.size(5.dp))
                Column {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .weight(weight = 1f, fill = false),
                    ) {
                        for (city in cities) {
                            var response: Response<WeatherDTO>? = null
                            cityWeather.value?.let { cities ->
                                response = cities[city.name]
                            }
                            CityListItem(
                                cityData = city,
                                weatherInfo = response?.body(),
                                temperatureUnit = settingsViewModel.getTemperatureUnitPreference(context = context)
                            )
                        }
                    }
                    Button(
                        onClick = { navController.navigate("welcome_screen") },
                        modifier = Modifier
                            .paddingFromBaseline(0.dp, 30.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Blue) // Change color as needed
                    ) {
                        Text(text = "Go Back")
                    }
                }
            }
        }

        else -> {
            Column {
                Text(text = "Oops! Something went wrong", color = Color.Red)
                Button(onClick = { navController.navigate("welcome_screen") }) {
                    Text(text = "Back to Home")
                }
            }
        }
    }
}

fun fetchCity(viewModel: CityViewModel, cityName: String) {
    viewModel.fetchWeather(cityName, "8f60a2eb73324803ac8143512230112")
}

@Composable
fun CityListItem(
    modifier: Modifier = Modifier,
    cityData: CityModel,
    weatherInfo: WeatherDTO?,
    temperatureUnit: Temperature
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = cityData.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black // Change color as needed
        )
        Spacer(modifier = Modifier.size(15.dp))

        weatherInfo?.let { weather ->
            when (temperatureUnit) {
                Temperature.C ->
                    Text(
                        text = "Temperature: ${weather.temp?.degreesC} ${temperatureUnit.formatTemperature()}",
                        color = Color.Gray // Change color as needed
                    )
                Temperature.F ->
                    Text(
                        text = "Temperature: ${weather.temp?.degreesF} ${temperatureUnit.formatTemperature()}",
                        color = Color.Gray // Change color as needed
                    )
            }
        }
        Spacer(modifier = modifier.size(5.dp))
        Text(
            text = cityData.description,
            fontSize = 16.sp,
            color = Color.DarkGray // Change color as needed
        )
        Spacer(modifier = Modifier.size(30.dp))
    }
}

