package com.example.smart_trip.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// j'ai repris le principe des 4 états du cours pour gérer le chargement et les erreurs GPS
sealed interface WeatherUiState {
    data object Loading : WeatherUiState
    data object PermissionRequired : WeatherUiState // si le gars refuse la localisation
    data class Error(val message: String) : WeatherUiState
    data class Success(val weather: DestinationWeather) : WeatherUiState
}

data class DestinationWeather(
    val locationLabel: String,
    val currentTemp: Int,
    val condition: String,
    val feelsLike: Int,
    val humidity: Int,
    val windSpeed: Int,
    val dailyForecast: List<DailyForecast>,
    val hourlyForecast: List<HourlyForecast>,
    val tips: List<TravelTip>
)

data class DailyForecast(
    val dayLabel: String,
    val maxTemp: Int,
    val minTemp: Int,
    val iconType: WeatherIconType,
    val isToday: Boolean = false // juste pour savoir quelle carte mettre en bleu clair
)

data class HourlyForecast(
    val hourLabel: String,
    val temp: Int,
    val iconType: WeatherIconType
)

data class TravelTip(
    val title: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val iconTint: Color
)

enum class WeatherIconType {
    SUNNY, PARTLY_CLOUDY, RAINY, NIGHT_CLEAR
}