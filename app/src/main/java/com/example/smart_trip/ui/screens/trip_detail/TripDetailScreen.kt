package com.example.smart_trip.ui.screens.trip_detail


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material3.Icon


import androidx.compose.runtime.Composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import com.example.smart_trip.R
import com.example.smart_trip.ui.theme.PaleGray
import com.example.smart_trip.ui.theme.Turquoise
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.smart_trip.ui.components.TripDetailTopAppBar
import com.example.smart_trip.ui.components.TripOverviewCard
import com.example.smart_trip.ui.components.TripDatesCard
import com.example.smart_trip.ui.components.CurrentWeatherGeneralCard
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smart_trip.model.DestinationWeather
import com.example.smart_trip.data.trips
import com.example.smart_trip.R.drawable.default_image



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripDetailScreen (
    onBackClick: () -> Unit,
    tripId: Long
) {

    val trip = trips.find { it.id == tripId }

    if (trip == null) {
        Text("Voyage introuvable")
        return
    }

    val imageHeight = 280.dp
    val cardOverlap = 24.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PaleGray,
        topBar = {
            TripDetailTopAppBar(
                onBackClick = onBackClick,
                onOptionClick = {},

            )
        },
        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = innerPadding.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                ) {
                    Image(
                        painter = painterResource(
                            id = trip.imageResId ?: default_image
                        ),
                        contentDescription = "Colisée de Rome vue à l'aube",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = cardOverlap + 16.dp,
                            ),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = trip.destination,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
                        )
                        Text(
                            text = "${trip.startDate}-> ${trip.endDate}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),

                            ) {
                            Icon(
                                imageVector = Icons.Default.Flight,
                                contentDescription = "Icon avion",
                                tint = Turquoise,
                                modifier = Modifier
                                    .size(24.dp)
                                    .rotate(45f)
                            )
                            Text(
                                text = "${trip.durationDays} jours",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Turquoise,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = imageHeight - cardOverlap,
                            start = 12.dp,
                            end = 12.dp
                        )
                ) {
                    TripOverviewCard(
                        initialBudget = 1000.0,
                        spentBudget = 500.0,
                        visitedPlaces = 3,
                        totalPlaces = 5,
                        onEditClick = {}
                    )

                }

            }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    TripDatesCard(
                        startDate = "12 Oct. 2025",
                        endDate = "15 Oct. 2025",
                        duration = "3 jours",
                        onEditClick = {}
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    CurrentWeatherGeneralCard(
                        weather = DestinationWeather(
                            locationLabel = "Rome",
                            currentTemp = 25,
                            feelsLike = 28,
                            humidity = 60,
                            windSpeed = 5,
                            dailyForecast = listOf(),
                            hourlyForecast = listOf(),
                            tips = listOf(),
                            condition = "Ensoleillé",
                        )
                    )
                }

            }

        }

    }