
package com.example.smart_trip.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.smart_trip.components.SmartTripFooter
import com.example.smart_trip.ui.screens.home.HomeScreen
import com.example.smart_trip.IntroScreen
import com.example.smart_trip.ui.screens.add_a_trip.AddATripScreen
import com.example.smart_trip.ui.screens.trip_detail.TripDetailScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    // Route actuellement affichée
    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    // Masquer le footer sur l'introduction
    val showFooter =
        currentRoute != null && currentRoute != "intro"

    // Onglet sélectionné dans le footer
    val selectedItem = when (currentRoute) {
        "home" -> 0
        "voyages", "allTrips", "newTrip" -> 1
        "parametres" -> 2
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

            // INTRODUCTION
            composable("intro") {
                IntroScreen(
                    onMesVoyagesClick = {
                        navController.navigate("home") {
                            popUpTo("intro") {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // ACCUEIL
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
                    onViewDetailTrip = {
                        navController.navigate("tripDetail")
                    },
                    onViewAllTrips = {
                        navController.navigate("allTrips")
                    },
                    onViewWeather = {
                        navController.navigate("weather")
                    }
                )
            }

            // DETAIL D'UN VOYAGE
            composable("tripDetail") {
                TripDetailScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }


            // CRÉER UN VOYAGE
            composable("newTrip") {

                var destination by rememberSaveable {
                    mutableStateOf("")
                }

                var budget by rememberSaveable {
                    mutableStateOf("")
                }

                var startDatePickerValue by rememberSaveable {
                    mutableStateOf("")
                }

                var endDatePickerValue by rememberSaveable {
                    mutableStateOf("")
                }

                var selectedTravelType by rememberSaveable {
                    mutableStateOf("")
                }

                var notes by rememberSaveable {
                    mutableStateOf("")
                }

                AddATripScreen(
                    destination = destination,
                    onDestinationChange = {
                        destination = it
                    },
                    startDatePickerValue = startDatePickerValue,
                    onStartDateChange = {
                        startDatePickerValue = it
                    },
                    endDatePickerValue = endDatePickerValue,
                    onEndDateChange = {
                        endDatePickerValue = it
                    },
                    selectedTravelType = selectedTravelType,
                    onTravelTypeChange = {
                        selectedTravelType = it
                    },
                    budget = budget,
                    onBudgetChange = {
                        budget = it
                    },
                    notes = notes,
                    onNotesChange = {
                        notes = it
                    },
                    onSubmit = {
                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // AJOUTER UN LIEU
            composable("newPlace") {
                Text("Ajouter un lieu")
            }

            // AJOUTER UNE DÉPENSE
            composable("newExpense") {
                Text("Ajouter une dépense")
            }

            // AJOUTER UNE PHOTO
            composable("newPhoto") {
                Text("Ajouter une photo")
            }

            // LISTE DES VOYAGES
            composable("allTrips") {
                Text("Tous mes voyages")
            }

            // MÉTÉO
            composable("weather") {
                Text("Météo")
            }

            // ONGLET VOYAGES
            composable("voyages") {
                Text("Page Voyages")
            }

            // ONGLET PARAMÈTRES
            composable("parametres") {
                Text("Page paramètres")
            }
        }
    }
}
