package com.example.smart_trip.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.smart_trip.components.SmartTripFooter
import com.example.smart_trip.ui.screens.home.HomeScreen
import com.example.smart_trip.IntroScreen
import com.example.smart_trip.ui.screens.add_a_trip.AddATripScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    val navBackStackEntry by
        navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

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
    val showFooter =
        currentRoute != null && currentRoute != "intro"

    val selectedItem = when (currentRoute) {
        "home" -> 0
        "voyages" -> 1
        "parametres" -> 2
        "newTrip" -> 1
        else -> -1
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showFooter) {
                SmartTripFooter(
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        val route = when (index) {
                            0 -> "home"
                            1 -> "voyages"
                            2 -> "parametres"
                            else -> null
                        }

                        route?.let { destination ->
                            navController.navigate(destination) {
                                popUpTo("home") {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                        }
                    }
                )
            }
        }
    ) { innerPadding ->

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

    }




