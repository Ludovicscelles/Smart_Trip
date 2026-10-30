package com.example.smart_trip.ui.screens.trip_detail

import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import com.example.smart_trip.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import com.example.smart_trip.ui.theme.PaleGray
import androidx.compose.ui.unit.dp
@Composable
fun TripDetailScreen (
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleGray)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(PaleGray),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.colisee
                ),
                contentDescription = "Colisée de Rome vue à l'aube",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )

                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Retour",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )

                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Options",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }

        }
    }
}