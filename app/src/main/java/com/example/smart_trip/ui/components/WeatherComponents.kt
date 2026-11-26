package com.example.smart_trip.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.model.*

// affiche la bonne icône selon le temps
@Composable
fun WeatherConditionIcon(type: WeatherIconType, modifier: Modifier = Modifier) {
    when (type) {
        WeatherIconType.SUNNY -> Icon(
            imageVector = Icons.Filled.WbSunny,
            contentDescription = "Ensoleillé",
            tint = Color(0xFFFDB813),
            modifier = modifier
        )
        WeatherIconType.PARTLY_CLOUDY -> Icon(
            imageVector = Icons.Outlined.WbCloudy,
            contentDescription = "Partiellement nuageux",
            tint = Color(0xFFF59E0B),
            modifier = modifier
        )
        WeatherIconType.RAINY -> Icon(
            imageVector = Icons.Outlined.WaterDrop,
            contentDescription = "Pluvieux",
            tint = Color(0xFF60A5FA),
            modifier = modifier
        )
        WeatherIconType.NIGHT_CLEAR -> Icon(
            imageVector = Icons.Filled.NightsStay,
            contentDescription = "Nuit claire",
            tint = Color(0xFF4B5563),
            modifier = modifier
        )
    }
}

// barre blanche en haut pour afficher la ville (et actualiser le GPS au clic)
@Composable
fun DestinationSelectorCard(
    destination: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        color = Color.White,
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Destination",
                tint = Color(0xFF1F2937),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Destination",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
                Text(
                    text = destination,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Actualiser la position",
                tint = Color.Gray
            )
        }
    }
}

// grande carte beige avec la température actuelle et les détails à droite
@Composable
fun CurrentWeatherCard(
    weather: DestinationWeather,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFFFEF6E4),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                WeatherConditionIcon(
                    type = WeatherIconType.SUNNY,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = "${weather.currentTemp}°C",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF111827)
                    )
                    Text(
                        text = weather.condition,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF374151)
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                WeatherMetricRow(Icons.Outlined.Thermostat, "Ressenti", "${weather.feelsLike}°C")
                WeatherMetricRow(Icons.Outlined.WaterDrop, "Humidité", "${weather.humidity} %")
                WeatherMetricRow(Icons.Outlined.Air, "Vent", "${weather.windSpeed} km/h")
            }
        }
    }
}

@Composable
private fun WeatherMetricRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF4B5563),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "$label ",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF4B5563)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )
    }
}

// une des 5 petites cartes verticales pour les prévisions de la semaine
@Composable
fun DailyForecastItem(
    forecast: DailyForecast,
    modifier: Modifier = Modifier
) {
    val bgColor = if (forecast.isToday) Color(0xFFE0F2F1) else Color.White

    Surface(
        modifier = modifier,
        color = bgColor,
        shape = RoundedCornerShape(14.dp),
        shadowElevation = if (forecast.isToday) 0.dp else 1.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = forecast.dayLabel,
                fontSize = 11.sp,
                fontWeight = if (forecast.isToday) FontWeight.Bold else FontWeight.Medium,
                color = Color(0xFF1F2937),
                maxLines = 1
            )
            WeatherConditionIcon(
                type = forecast.iconType,
                modifier = Modifier.size(26.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${forecast.maxTemp}°C",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )
                Text(
                    text = "${forecast.minTemp}°C",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}

// bloc blanc des prévisions par heure
@Composable
fun HourlyForecastCard(
    hourlyList: List<HourlyForecast>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            hourlyList.forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = item.hourLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF4B5563)
                    )
                    WeatherConditionIcon(
                        type = item.iconType,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "${item.temp}°C",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                }
            }
        }
    }
}

// un carré coloré en bas pour les conseils voyage
@Composable
fun TravelTipCard(
    tip: TravelTip,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(110.dp),
        color = tip.backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = tip.icon,
                contentDescription = tip.title,
                tint = tip.iconTint,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tip.title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1F2937),
                textAlign = TextAlign.Center,
                lineHeight = 15.sp
            )
        }
    }
}