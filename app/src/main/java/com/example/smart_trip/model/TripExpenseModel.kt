package com.example.smart_trip.model

import java.time.LocalDate

data class TripExpenseModel(
    val id: Long,
    val tripId: Long,
    val name: String,
    val amount: Double,
    val date: LocalDate,
    val description: String? = null,
)