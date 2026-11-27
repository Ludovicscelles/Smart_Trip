
package com.example.smart_trip.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument

import com.example.smart_trip.components.SmartTripFooter
import com.example.smart_trip.ui.screens.home.HomeScreen
import com.example.smart_trip.ui.screens.add_a_trip.AddATripScreen
import com.example.smart_trip.ui.screens.intro.IntroScreen
import com.example.smart_trip.ui.screens.trip_detail.TripDetailScreen
import com.example.smart_trip.ui.screens.weather.WeatherRoute


object Routes {
    const val INTRO = "intro"
    const val HOME = "home"
    const val ADD_A_TRIP = "addATrip"
    const val TRIP_DETAIL = "tripDetail/{tripId}"
    const val NEW_TRIP = "newTrip"
    const val NEW_PLACE = "newPlace"
    const val NEW_EXPENSE = "newExpense"
    const val NEW_PHOTO = "newPhoto"
    const val ALL_TRIPS = "allTrips"
    const val WEATHER = "weather"
    const val VOYAGES = "voyages"
    const val PARAMETRES = "parametres"

    fun tripDetail(tripId: Long) = "tripDetail/$tripId"

}


@RequiresApi(Build.VERSION_CODES.O)
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
        Routes.HOME -> 0
        Routes.VOYAGES, Routes.ALL_TRIPS, Routes.NEW_TRIP -> 1
        Routes.PARAMETRES -> 2
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
                            0 -> Routes.HOME
                            1 -> Routes.VOYAGES
                            2 -> Routes.PARAMETRES
                            else -> null
                        }

                        route?.let { destination ->
                            if (destination == "home") {
                                // C'est ce bloc qui remplace l'ancien navController.navigate
                                navController.popBackStack("home", inclusive = false)
                            } else {
                                navController.navigate(destination) {
                                    popUpTo("home") {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.INTRO,
            modifier = Modifier.padding(innerPadding)
        ) {

            // INTRODUCTION
            composable("intro") {
                IntroScreen(
                    onMesVoyagesClick = {
                        navController.navigate(Routes.HOME) {
                            popUpTo("intro") {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // ACCUEIL
            composable(Routes.HOME) {
                HomeScreen(
                    onAddATrip = {
                        navController.navigate(Routes.NEW_TRIP)
                    },
                    onAddPlace = {
                        navController.navigate(Routes.NEW_PLACE)
                    },
                    onAddExpense = {
                        navController.navigate(Routes.NEW_EXPENSE)
                    },
                    onAddPhoto = {
                        navController.navigate(Routes.NEW_PHOTO)
                    },
                    onViewDetailTrip = { tripId ->
                        navController.navigate(Routes.tripDetail(tripId))
                    },
                    onViewAllTrips = {
                        navController.navigate(Routes.ALL_TRIPS)
                    },
                    onViewWeather = {
                        navController.navigate(Routes.WEATHER)
                    }
                )
            }

            // DETAIL D'UN VOYAGE
            composable (
                Routes.TRIP_DETAIL,
                arguments = listOf(
                    navArgument("tripId") {
                        type = NavType.LongType
                    }
                )

            ) { backStackEntry ->

                val tripId = backStackEntry.arguments
                    ?.getLong("tripId")
                    ?: return@composable

                TripDetailScreen(
                    tripId = tripId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }


            // CRÉER UN VOYAGE
            composable(Routes.NEW_TRIP) {

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
            composable(Routes.NEW_PLACE) {
                Text("Ajouter un lieu")
            }

            // AJOUTER UNE DÉPENSE
            composable(Routes.NEW_EXPENSE) {
                Text("Ajouter une dépense")
            }

            // AJOUTER UNE PHOTO
            composable(Routes.NEW_PHOTO) {
                Text("Ajouter une photo")
            }

            // LISTE DES VOYAGES
            composable(Routes.ALL_TRIPS) {
                Text("Tous mes voyages")
            }

            // MÉTÉO
            composable(Routes.WEATHER) {
                WeatherRoute(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // ONGLET VOYAGES
            composable(Routes.VOYAGES) {
                Text("Page Voyages")
            }

            // ONGLET PARAMÈTRES
            composable(Routes.PARAMETRES) {
                Text("Page paramètres")
            }
        }
    }
}
