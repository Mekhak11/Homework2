package com.example.homework2.CityList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun CityListView(navController: NavHostController) {
    val cityList = CityViewModel().cityList.value ?: emptyList()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("List of Cities", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            cityList.forEach {
                item {
                    CityListItem(city = it)
                }

            }
        }
    }
}

@Composable
fun CityListItem(city: CityModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = city.name, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = city.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

