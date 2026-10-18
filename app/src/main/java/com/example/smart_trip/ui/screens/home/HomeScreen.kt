package com.example.smart_trip.ui.screens.home

import android.R
import android.os.strictmode.UntaggedSocketViolation
import androidx.compose.foundation.Image
import  androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.ui.theme.Turquoise

@Composable
fun HomeScreen(
    onBackCLick: () -> Unit,
    onAddATrip:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Image(
            painter = painterResource(
                id = com.example.smart_trip.R.drawable.logo_smarttrip
            ),
            contentDescription = "Logo SmartTrip",
            modifier = Modifier
                .align(Alignment.Start)
                .height(50.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bonjour Lucie !",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Prête pour la prochaine aventure ?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
            )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Prochain Voyage",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold


            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Turquoise
                )

            ) {
                Text("Voir tout")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painterResource(
                id = com.example.smart_trip.R.drawable.rome_nuit
            ),
            contentDescription = "Colisée de Rome vue de nuit",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onAddATrip,
            colors = ButtonDefaults.buttonColors(
                containerColor = Turquoise,
                contentColor = Color.White

            )

        ) {
            Text("Ajouter un Voyage")
        }


        Button(
            onClick = onBackCLick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Turquoise,
                contentColor = Color.White
            )

        ) {
            Text("Revenir au début")
        }

    }

}
