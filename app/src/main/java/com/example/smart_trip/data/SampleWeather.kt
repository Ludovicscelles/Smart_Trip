package com.example.smart_trip.data

import android.content.Context
import android.location.Geocoder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BeachAccess
import androidx.compose.material.icons.outlined.Luggage
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.Color
import com.example.smart_trip.model.*
import java.util.Locale

// génère les fausses données météo mais avec la vraie ville du GPS
fun getWeatherForCoordinates(context: Context, lat: Double, lon: Double): DestinationWeather {
    // Geocoder traduit les coordonnées GPS de l'émulateur en nom de ville
    val cityName = try {
        @Suppress("DEPRECATION")
        val addresses = Geocoder(context, Locale.getDefault()).getFromLocation(lat, lon, 1)
        val city = addresses?.firstOrNull()?.locality ?: addresses?.firstOrNull()?.subAdminArea
        val country = addresses?.firstOrNull()?.countryName
        if (city != null && country != null) {
            "$city, $country"
        } else {
            "Ma position (${String.format(Locale.US, "%.2f", lat)}, ${String.format(Locale.US, "%.2f", lon)})"
        }
    } catch (e: Exception) {
        // au cas où l'émulateur a pas internet pour trouver le nom de la ville
        "Position GPS (${String.format(Locale.US, "%.2f", lat)}, ${String.format(Locale.US, "%.2f", lon)})"
    }

    return DestinationWeather(
        locationLabel = cityName,
        currentTemp = 24,
        condition = "Ensoleillé",
        feelsLike = 26,
        humidity = 52,
        windSpeed = 12,
        dailyForecast = listOf(
            DailyForecast("Aujourd'hui", 24, 16, WeatherIconType.SUNNY, isToday = true),
            DailyForecast("Mar. 7", 22, 15, WeatherIconType.PARTLY_CLOUDY),
            DailyForecast("Mer. 8", 25, 17, WeatherIconType.SUNNY),
            DailyForecast("Jeu. 9", 21, 14, WeatherIconType.RAINY),
            DailyForecast("Ven. 10", 23, 16, WeatherIconType.PARTLY_CLOUDY)
        ),
        hourlyForecast = listOf(
            HourlyForecast("10 h", 20, WeatherIconType.SUNNY),
            HourlyForecast("13 h", 24, WeatherIconType.SUNNY),
            HourlyForecast("16 h", 24, WeatherIconType.SUNNY),
            HourlyForecast("19 h", 22, WeatherIconType.NIGHT_CLEAR),
            HourlyForecast("22 h", 18, WeatherIconType.NIGHT_CLEAR)
        ),
        tips = listOf(
            TravelTip("Tenue\nlégère", Icons.Outlined.Luggage, Color(0xFFE6F4EA), Color(0xFF1E8E3E)),
            TravelTip("Prenez\ndes lunettes", Icons.Outlined.BeachAccess, Color(0xFFE8F0FE), Color(0xFF1A73E8)),
            TravelTip("Pensez à la\ncrème solaire", Icons.Outlined.WbSunny, Color(0xFFFEF7E0), Color(0xFFF29900))
        )
    )
}