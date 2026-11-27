package com.example.smart_trip.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.smart_trip.model.TripModel
import com.example.smart_trip.model.TripPlaceModel
import java.time.LocalDate
import com.example.smart_trip.R.drawable.*

@RequiresApi(Build.VERSION_CODES.O)
val trips = listOf(
    TripModel(
        id = 1,
        name = "Découverte de Rome",
        destination = "Rome",
        country = "Italie",
        startDate = LocalDate.of(2023, 7, 1),
        endDate = LocalDate.of(2023, 7, 15),
        initialBudget = 1000.0,
        currency = "EUR",
        description = "Découverte de la ville éternelle, de son patrimoine antique et de la gastronomie italienne.",
        imageResId = colisee,
        latitude = 41.9028,
        longitude = 12.4964,

    ),
    TripModel(
        id = 2,
        name = "Week-end à Barcelone",
        destination = "Barcelone",
        country = "Espagne",
        startDate = LocalDate.of(2027, 4, 16),
        endDate = LocalDate.of(2027, 4, 19),
        initialBudget = 650.0,
        currency = "EUR",
        description = "Un week-end entre architecture, tapas et plages méditerranéennes.",
        imageResId = barcelone,
        latitude = 41.3874,
        longitude = 2.1686
    ),
    TripModel(
        id = 3,
        name = "Aventure au Japon",
        destination = "Tokyo",
        country = "Japon",
        startDate = LocalDate.of(2027, 10, 5),
        endDate = LocalDate.of(2027, 10, 20),
        initialBudget = 3500.0,
        currency = "EUR",
        description = "Immersion dans la culture japonaise, entre traditions et modernité.",
        imageResId = tokyo,
        latitude = 35.6762,
        longitude = 139.6503
    ),
    TripModel(
        id = 4,
        name = "Escapade à Londres",
        destination = "Londres",
        country = "Royaume-Uni",
        startDate = LocalDate.of(2026, 11, 25),
        endDate = LocalDate.of(2026, 11, 29),
        initialBudget = 850.0,
        currency = "EUR",
        description = "Découverte des quartiers londoniens, des musées et des monuments historiques.",
        imageResId = londres,
        latitude = 51.5074,
        longitude = -0.1278
    ),
    TripModel(
        id = 5,
        name = "Vacances à Lisbonne",
        destination = "Lisbonne",
        country = "Portugal",
        startDate = LocalDate.of(2026, 9, 5),
        endDate = LocalDate.of(2026, 9, 10),
        initialBudget = 950.0,
        currency = "EUR",
        description = "Balades dans les ruelles de l'Alfama et découverte de la cuisine portugaise.",
        imageResId = com.example.smart_trip.R.drawable.lisbonne,
        latitude = 38.7223,
        longitude = -9.1393
    )


)


val places = listOf(
    TripPlaceModel(
        id = 1,
        tripId = 1,
        name = "Colisée",
        originalName = "Colosseo",
        address = "Piazza del Colosseo, 1, 00184 Roma RM, Italy",
        description = "Amphitheatrum Flavium (amphithéâtre Flavien), en référence à la dynastie des Flaviens.",
        isVisited = true,
    ),

    TripPlaceModel(
        id = 2,
        tripId = 1,
        name = "Cité du Vatican",
        originalName = "Città del Vaticano",
        address = "Vatican City, 37923 Rome RM, Italy",
        description = "La Cité du Vatican, située au cœur de Rome, est le plus petit État du monde, célèbre pour la basilique Saint-Pierre, ses trésors artistiques et son importance religieuse.",
        isVisited = true,
    ),
    TripPlaceModel(
        id = 3,
        tripId = 1,
        name = "Fontaine de Trevi",
        originalName = "Fontana di Trevi",
        address = "Piazza del Trevi, 1, 00184 Roma RM, Italy",
        description = "La fontaine de Trevi, située à Rome, est une magnifique fontaine baroque ornée de sculptures majestueuses en marbre blanc et d'un grand bassin aux eaux turquoise.",
        isVisited = false,
    ),
)