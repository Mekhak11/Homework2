package com.example.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.homework2.ui.theme.Homework2Theme
import androidx.navigation.compose.composable
import com.example.homework2.CityList.CityListView
import com.example.homework2.Settings.SettingsView

import com.example.myapplication.view.WelcomeScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


private const val LOCATION_PERMISSION_REQUEST_CODE = 34

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "welcome_screen"
                ) {
                    composable("welcome_screen") {
                        WelcomeScreen(
                            context = this@MainActivity,
                            fusedLocationClient = fusedLocationClient,
                            navController = navController
                        )
                    }
                    composable("second_screen") {
                        CityListView(navController = navController, context = this@MainActivity)
                    }
                    composable("settings_screen") {
                        SettingsView(navController = navController, context = this@MainActivity)
                    }
                }

        }
    }
}

