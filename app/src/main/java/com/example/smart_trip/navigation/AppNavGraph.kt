package com.example.smart_trip.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_trip.ui.screens.home.HomeScreen
import com.example.smart_trip.IntroScreen
import com.example.smart_trip.ui.screens.add_a_trip.AddATripScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "intro"
    ) {
        composable("intro") {
            IntroScreen(
                onMesVoyagesClick = {
                    navController.navigate("home")
                }
            )

        }

        composable("home") {
            HomeScreen(
                onBackCLick = {
                    navController.popBackStack()
                },
                onAddATrip = {
                    navController.navigate("newTrip")
                }
            )
        }


            composable("newTrip") {

                var destination by rememberSaveable {
                    mutableStateOf("")
                }

                var budget by rememberSaveable {
                    mutableStateOf("")
                }

                AddATripScreen(
                    destination = destination,
                    onDestinationChange = {
                        destination = it
                    },
                    budget = budget,
                    onBudgetChange = {
                        budget = it
                    },
                    onSubmit = {
                        navController.popBackStack()
                    },

                )

            }

    }
}

