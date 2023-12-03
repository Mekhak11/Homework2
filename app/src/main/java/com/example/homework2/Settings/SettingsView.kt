package com.example.homework2.Settings

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.homework2.DTO.Temperature

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsView(navController: NavController, context: Context, viewModel: SettingsViewModel = viewModel()) {
    var selectedTemperatureUnit by remember { mutableStateOf(viewModel.getTemperatureUnitPreference(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Temperature Unit Preference", style = MaterialTheme.typography.headlineLarge)

        SwitchItem(
            text = "Celsius (°C)",
            isSelected = selectedTemperatureUnit == Temperature.C,
            onToggle = {
                selectedTemperatureUnit = Temperature.C
                viewModel.saveTemperatureUnitPreference(context, Temperature.C)
            }
        )

        SwitchItem(
            text = "Fahrenheit (°F)",
            isSelected = selectedTemperatureUnit == Temperature.F,
            onToggle = {
                selectedTemperatureUnit = Temperature.F
                viewModel.saveTemperatureUnitPreference(context, Temperature.F)
            }
        )

        // Display the selected unit
        Text(
            text = "Selected Unit: ${if (selectedTemperatureUnit == Temperature.C) "Celsius (°C)" else "Fahrenheit (°F)"}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("welcome_screen")
                viewModel.updateTemperatureUnit(selectedTemperatureUnit)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Home")
        }
    }
}

@Composable
fun SwitchItem(
    text: String,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Switch(
            checked = isSelected,
            onCheckedChange = { onToggle.invoke() },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}
