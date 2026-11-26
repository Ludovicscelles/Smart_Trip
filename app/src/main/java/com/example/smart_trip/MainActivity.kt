package com.example.smart_trip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.smart_trip.ui.theme.Smart_TripTheme
import com.example.smart_trip.navigation.AppNavGraph

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







