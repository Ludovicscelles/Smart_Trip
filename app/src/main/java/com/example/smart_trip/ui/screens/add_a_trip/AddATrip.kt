package com.example.smart_trip.ui.screens.add_a_trip

import androidx.compose.foundation.Image
import  androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.ui.theme.Turquoise
import com.example.smart_trip.ui.theme.PaleGray
import androidx.compose.ui.graphics.Color

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun AddATripScreen(
    destination: String,
    onDestinationChange: (String) -> Unit,
    startDatePickerValue: String,
    onStartDateChange: (String) -> Unit,
    endDatePickerValue: String,
    onEndDateChange: (String) -> Unit,
    budget: String,
    onBudgetChange: (String) -> Unit,
    selectedTravelType: String,
    onTravelTypeChange: (String) -> Unit,
    notes: String,
    onNotesChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBackClick: () -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleGray)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentAlignment = Alignment.Center
        ) {


            Image(
                painter = painterResource(
                    id = com.example.smart_trip.R.drawable.teal_sky
                ),
                contentDescription = "Image de fond SmartTrip",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Nouveau Voyage",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Renseignez les informations de votre voyage pour bien le préparer.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )

            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-40).dp)
                .clip(RoundedCornerShape(15.dp))
                .background(PaleGray)
                .padding(26.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // Destination du voyage
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Icon destination",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
                Text(
                    text = buildAnnotatedString {
                        append("Destination ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red
                            )
                        ) {
                            append("*")
                        }
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = destination,
                onValueChange = onDestinationChange,
                placeholder = {
                    Text(
                        text = "Ex.Rome, Tokyo, New York ...",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Turquoise,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Dates du voyage
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Icon calendrier",
                    modifier = Modifier.size(26.dp),
                    tint = Color.Black
                )
                Text(
                    text = "Dates du voyage",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = startDatePickerValue,
                    onValueChange = onStartDateChange,
                    placeholder = {
                        Text(
                            text = "Date du début",
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = "Calendrier début",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Turquoise,
                        unfocusedBorderColor = Color.Gray,
                    )
                )
                OutlinedTextField(
                    value = endDatePickerValue,
                    onValueChange = onEndDateChange,
                    placeholder = {
                        Text(
                            text = "Date de fin",
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.CalendarToday,
                            contentDescription = "Calendrier fin",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Turquoise,
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Budget estimé
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "Icon portefeuille",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )


                Text(
                    text = "Budget estimé",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = budget,
                onValueChange = onBudgetChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                placeholder = {
                    Text(
                        text = "Ex. 1200",
                        color = Color.Gray
                    )
                },
                suffix = {
                    Text(
                        text = "€",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Turquoise,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(24.dp))



            // Type de voyage
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.plane
                    ),
                    contentDescription = "Icon avion",
                    modifier = Modifier.size(30.dp),
                )
                Text(
                    text = "Type de voyage",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                listOf("Loisir", "Professionnel", "Autre").forEach { type ->
                    val isSelected = selectedTravelType == type
                   Button(
                       onClick = {
                           onTravelTypeChange(type)
                       },
                       modifier = Modifier.weight(1f),
                       shape = RoundedCornerShape(12.dp),
                       border = BorderStroke(
                           width = 1.dp,
                           color = if (isSelected) Turquoise else Color.Gray
                       ),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = if (isSelected) Turquoise else Color.Transparent,
                           contentColor = if (isSelected) Color.White else Color.Gray
                       ),
                       contentPadding = PaddingValues(
                           horizontal = 4.dp,
                           vertical = 10.dp
                       )

                   ) {
                       Text(
                           text = type,
                           fontSize = 13.sp,
                           fontWeight = FontWeight.Medium,
                           maxLines = 1,
                           )
                   }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Notes du voyage
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.StickyNote2,
                    contentDescription = "Icon notes",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Notes")
                        }
                        append(" (facultatif)")
                    },
                    fontSize = 14.sp,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = onNotesChange,
                placeholder = {
                    Text(
                        text = "Ajouter des informations supplémentaires ...",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            val isFormValid =
                destination.isNotBlank() &&
                startDatePickerValue.isNotBlank() &&
                endDatePickerValue.isNotBlank()
                && ( budget.toDoubleOrNull()?.let { it > 0 } == true)


            // Bouton de création du voyage
            Button(
                onClick = onSubmit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Turquoise,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                shape = RoundedCornerShape(15.dp),
                enabled = isFormValid
            ) {
                Text(
                    text = "Créer le voyage",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    }



