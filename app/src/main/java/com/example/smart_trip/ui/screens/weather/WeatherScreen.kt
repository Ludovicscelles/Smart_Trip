package com.example.smart_trip.ui.screens.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.smart_trip.R
import com.example.smart_trip.data.getWeatherForCoordinates
import com.example.smart_trip.model.DestinationWeather
import com.example.smart_trip.model.WeatherUiState
import com.example.smart_trip.ui.components.*
import com.example.smart_trip.ui.theme.Smart_TripTheme
import com.example.smart_trip.ui.theme.Turquoise
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

// partie stateful : gère la permission GPS et la récupération de la position (principe State Hoisting du cours)
@Composable
fun WeatherRoute(
    onBackClick: () -> Unit,
    bottomBar: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    var uiState by remember { mutableStateOf<WeatherUiState>(WeatherUiState.Loading) }

    // récupère les coordonnées sur l'émulateur une fois la permission accordée
    @SuppressLint("MissingPermission")
    fun fetchCurrentLocationWeather() {
        uiState = WeatherUiState.Loading
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    val data = getWeatherForCoordinates(context, location.latitude, location.longitude)
                    uiState = WeatherUiState.Success(data)
                } else {
                    uiState = WeatherUiState.Error(
                        "Signal GPS introuvable. Activez votre localisation pour afficher la météo autour de vous."
                    )
                }
            }
            .addOnFailureListener {
                uiState = WeatherUiState.Error("Erreur lors de la récupération du GPS.")
            }
    }

    // popup système Android pour demander l'autorisation
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            fetchCurrentLocationWeather()
        } else {
            uiState = WeatherUiState.PermissionRequired
        }
    }

    // vérifie la permission dès qu'on arrive sur la page
    LaunchedEffect(Unit) {
        val hasFine = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarse = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasFine || hasCoarse) {
            fetchCurrentLocationWeather()
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    WeatherScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onRefreshLocation = { fetchCurrentLocationWeather() },
        onRequestPermission = {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    )
}

// partie stateless : juste l'affichage de la maquette selon l'état reçu
@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    onBackClick: () -> Unit,
    onRefreshLocation: () -> Unit,
    onRequestPermission: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8FAFC)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // header turquoise avec le fond avion/nuages
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(Turquoise)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.teal_sky),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour",
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Météo",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Consultez la météo de votre destination\net anticipez votre voyage",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.95f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // bloc blanc arrondi qui remonte un peu sur le header turquoise
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-24).dp)
                    .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    .background(Color(0xFFF8FAFC))
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                when (uiState) {
                    is WeatherUiState.Loading -> {
                        Box(Modifier.height(400.dp)) {
                            LoadingState()
                        }
                    }
                    is WeatherUiState.PermissionRequired -> {
                        Box(Modifier.height(400.dp)) {
                            LocationPermissionState(onRequestPermission = onRequestPermission)
                        }
                    }
                    is WeatherUiState.Error -> {
                        Box(Modifier.height(400.dp)) {
                            ErrorState(message = uiState.message, onRetry = onRefreshLocation)
                        }
                    }
                    is WeatherUiState.Success -> {
                        WeatherSuccessContent(
                            weather = uiState.weather,
                            onRefreshLocation = onRefreshLocation
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WeatherSuccessContent(
    weather: DestinationWeather,
    onRefreshLocation: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        DestinationSelectorCard(
            destination = weather.locationLabel,
            onClick = onRefreshLocation
        )

        CurrentWeatherCard(weather = weather)

        // prévisions sur 5 jours
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Prévisions sur 5 jours",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Voir plus",
                        style = MaterialTheme.typography.labelLarge,
                        color = Turquoise,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Turquoise,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                weather.dailyForecast.forEach { day ->
                    DailyForecastItem(
                        forecast = day,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // prévisions horaires
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "Prévisions horaires",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            HourlyForecastCard(hourlyList = weather.hourlyForecast)
        }

        // conseils voyage
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "Conseils voyage",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                weather.tips.forEach { tip ->
                    TravelTipCard(
                        tip = tip,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPermissionPreview() {
    Smart_TripTheme {
        WeatherScreen(
            uiState = WeatherUiState.PermissionRequired,
            onBackClick = {},
            onRefreshLocation = {},
            onRequestPermission = {}
        )
    }
}