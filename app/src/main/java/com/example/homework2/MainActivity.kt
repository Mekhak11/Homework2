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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Homework2Theme {
                NavHost(
                    navController = navController,
                    startDestination = "firstView") {
                    composable("firstView") {
                        FirstViewContent(navController)
                    }
                    composable("cityList") {
                        CityListView(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun FirstViewContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello User!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("cityList")
        }) {
            Text("Click Me!")
        }
    }
}

