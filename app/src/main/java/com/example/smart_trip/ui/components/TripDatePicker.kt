package com.example.smart_trip.ui.components

import com.example.smart_trip.R
import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.smart_trip.ui.theme.Turquoise
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TripDatePicker(
    value: String,
    onDateSelected: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(modifier = modifier) {

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Calendrier",
                    tint = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Turquoise,
                unfocusedBorderColor = Color.Gray,
            )
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    val calendar = Calendar.getInstance()

                    if (value.isNotBlank()) {
                        runCatching {
                            SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.FRANCE
                            ).apply {
                                isLenient = false
                            }.parse(value)
                            }.getOrNull()?.let {
                                calendar.time = it
                        }
                    }

                    DatePickerDialog(
                        context,
                        R.style.Theme_DatePicker,
                        {_, year, month, day ->
                            val selectedDate =
                                Calendar.getInstance().apply {
                                    set(year, month, day)
                                }

                            val formatter =
                                SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)

                            onDateSelected(
                                formatter.format(selectedDate.time)
                            )
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()

                }
        )


    }
}