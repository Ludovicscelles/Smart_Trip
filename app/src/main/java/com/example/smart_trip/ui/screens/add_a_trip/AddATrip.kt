package com.example.smart_trip.ui.screens.add_a_trip

import android.R
import  androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.ui.theme.Turquoise
import androidx.compose.ui.graphics.Color



@Composable
fun AddATripScreen(
    destination: String,
    onDestinationChange: (String) -> Unit,
    budget: String,
    onBudgetChange: (String) -> Unit,
    onSubmit: () -> Unit

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = destination,
            onValueChange = onDestinationChange,
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
                value = budget,
                onValueChange = { value ->
                    if (value.all { it.isDigit() }) {
                        onBudgetChange(value)
                    }
                },
                label = { Text("Budget (€)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
        )

        Button(
            onClick = onSubmit,
            colors = ButtonDefaults.buttonColors(
                containerColor = Turquoise,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = destination.isNotBlank()
                && budget.isNotBlank()
            ) {
                Text("Créer le voyage")}
    }
}