package com.example.smart_trip

import com.example.smart_trip.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smart_trip.ui.theme.Smart_TripTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip
import com.example.smart_trip.ui.theme.Turquoise
import com.example.smart_trip.ui.theme.PaleGray
import com.example.smart_trip.IntroScreen
import com.example.smart_trip.navigation.AppNavGraph
import com.example.smart_trip.ui.screens.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_TripTheme()  {
                AppNavGraph()
            }
        }
    }
}


@Composable
fun IntroScreen(
    onMesVoyagesClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PaleGray)
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.logo_smarttrip
                ),
                contentDescription = "Logo SmartTrip",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onMesVoyagesClick,
                modifier = Modifier
                    .height(65.dp)
                    .width(280.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Turquoise,
                    contentColor = Color.White
                )

            ) {
                Text(
                    text =  "Allez sur l'application",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

        }

    }

}
