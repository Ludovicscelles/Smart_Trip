package com.example.smart_trip.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun FormatDateRange(
    startDate: LocalDate,
    endDate: LocalDate
): Pair<String, String> {
    val shortFormat = DateTimeFormatter.ofPattern("d MMM", Locale.FRANCE)
    val longFormat = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.FRANCE)

    val startFormat = if (startDate.year == endDate.year) {
        shortFormat
    } else {
        longFormat
    }
    return Pair(
        startDate.format(startFormat),
        endDate.format(longFormat)
    )
}

