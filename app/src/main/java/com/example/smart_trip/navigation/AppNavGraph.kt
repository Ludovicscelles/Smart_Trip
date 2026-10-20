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

<<<<<<< Updated upstream
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
=======
        NavHost(
            navController = navController,
            startDestination = "intro",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("intro") {
                IntroScreen(
                    onMesVoyagesClick = {
                        navController.navigate("home") {
                            popUpTo("intro") {
                                inclusive = true
                            }
                        }

                    },


                )

            }

            composable("home") {
                HomeScreen(
                    onAddATrip = {
                        navController.navigate("newTrip")
                    },
                    onAddPlace = {
                        navController.navigate("newPlace")
                    },
                    onAddExpense = {
                        navController.navigate("newExpense")
                    },
                    onAddPhoto = {
                        navController.navigate("newPhoto")
                    },
                    onViewAllTrips = {
                        navController.navigate("allTrips")
                    },
                    onViewWeather = {
                        navController.navigate("weather")
                    }

                )
            }

            composable ( "voyages" ) {
                Text("Page Voyages")
            }

            composable ( "parametres" ) {
                Text("Page paramètres")
            }
>>>>>>> Stashed changes


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

