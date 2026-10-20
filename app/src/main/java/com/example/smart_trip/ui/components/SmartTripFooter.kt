package com.example.smart_trip.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.smart_trip.ui.theme.Turquoise

@Composable
fun SmartTripFooter(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        "Accueil",
        "Voyages",
        "Paramètres"
    )

    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.BusinessCenter,
        Icons.Default.Settings,
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, title ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = title,
                    )
                },
                label = { Text(title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Turquoise,
                    selectedTextColor = Turquoise,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor =  Color.Gray,
                    indicatorColor = Turquoise.copy(alpha = 0.15f),
                )


            )
        }
    }
}