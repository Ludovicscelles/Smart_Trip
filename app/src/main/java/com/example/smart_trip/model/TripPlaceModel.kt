package com.example.smart_trip.model

import androidx.annotation.DrawableRes

data class TripPlaceModel(
    val id: Long,
    val tripId: Long,
    val name: String,
    val address: String,
    val originalName: String ? = null,
    val description: String ? = null,
    @DrawableRes val imageResId: Int? = null,
    val latitude: Double ? = null,
    val longitude: Double ? = null,
    val isVisited: Boolean = false,
)