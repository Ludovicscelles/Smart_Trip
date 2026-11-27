package com.example.smart_trip.ui.components

import androidx.compose.foundation.layout.size

import androidx.compose.runtime.Composable

import androidx.compose.material3.Icon


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.ui.unit.dp

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailTopAppBar(
    onBackClick: () -> Unit,
    onOptionClick: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            FilledIconButton(
                onClick = onBackClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "Retour",
                    modifier = Modifier
                        .size(30.dp)
                )
            }

        },
        actions = {
            FilledIconButton(
                onClick = onOptionClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            )
            {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Options",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )

    )
}
