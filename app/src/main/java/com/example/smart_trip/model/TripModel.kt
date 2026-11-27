package com.example.smart_trip.model

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class TripModel(
    val id: Long,
    val name: String,
    val destination: String,
    val country: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val initialBudget: Double = 0.0,
    val currency: String = "EUR",
    val description: String? = null,
    @DrawableRes val imageResId: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
) {
    val durationNights: Long
    @RequiresApi(Build.VERSION_CODES.O)
    get() = ChronoUnit.DAYS.between(
        startDate,
        endDate
    )
    val durationDays: Long
    @RequiresApi(Build.VERSION_CODES.O)
    get() = durationNights + 1
}
