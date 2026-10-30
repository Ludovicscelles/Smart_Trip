package com.example.smart_trip.ui.screens.home

import androidx.compose.foundation.Image
import  androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.ui.theme.Turquoise
import com.example.smart_trip.ui.theme.PaleGray
import com.example.smart_trip.ui.theme.PaleBlue
import com.example.smart_trip.ui.theme.BrightBlue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.WorkOutline
import com.example.smart_trip.ui.theme.PaleGreen
import com.example.smart_trip.ui.theme.PaleYellow
import com.example.smart_trip.ui.theme.PaleOrange
import com.example.smart_trip.ui.theme.PalePurple
import com.example.smart_trip.ui.theme.PaleBlue2


@Composable
fun HomeScreen(
    onAddATrip:() -> Unit,
    onAddPlace:() -> Unit,
    onAddExpense:() -> Unit,
    onAddPhoto:() -> Unit,
    onViewDetailTrip:() -> Unit,
    onViewAllTrips:() -> Unit,
    onViewWeather:() -> Unit,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PaleGray)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = com.example.smart_trip.R.drawable.logo_smarttrip
                ),
                contentDescription = "Logo SmartTrip",
                modifier = Modifier
                    .height(50.dp),
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(
                    id = com.example.smart_trip.R.drawable.notification_picto
                ),
                contentDescription = "Notifications",
                modifier = Modifier
                    .height(30.dp),
                contentScale = ContentScale.Fit
            )
        }

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
            TextButton(
                onClick = { onViewDetailTrip() }
            ) {
                Text(
                    text = "Voir tout >",
                    color = Turquoise
                )
            }

        }

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(PaleBlue)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Budget restant
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Image(
                   painter = painterResource(
                       id = com.example.smart_trip.R.drawable.suitcase_icon
                   ),
                   contentDescription = "Budget",
                   modifier = Modifier
                       .size(28.dp)
               )

               Spacer(modifier = Modifier.width(12.dp))

               Column(
                   horizontalAlignment = Alignment.Start
               ) {
                   Text(
                       text = "620 €",
                       fontWeight = FontWeight.Bold,
                       lineHeight = 18.sp
                   )

                   Text(
                       text = "restant",
                       fontSize = 12.sp,
                       lineHeight = 14.sp
                   )
               }
           }

            VerticalDivider(
                modifier = Modifier.height(35.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            // Lieux visités
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Place,
                    "Lieux visités",
                    modifier = Modifier.size(28.dp),
                    tint = BrightBlue,
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "2 / 5",
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp
                    )

                    Text(
                        text = "lieux visités",
                        fontSize = 12.sp,
                        lineHeight = 14.sp
                    )
                }
            }

            VerticalDivider(
                modifier = Modifier.height(35.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            // Jours de voyage
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.WorkOutline,
                    "Nombre de jours",
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "6 jours",
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp
                    )

                    Text(
                        text = "de voyage",
                        fontSize = 12.sp,
                        lineHeight = 14.sp
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Météo à Rome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold


            )
            TextButton(
                onClick = { onViewWeather() }
            ) {
                Text(
                    text = "Voir plus >",
                    color = Turquoise
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(PaleYellow)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.sun
                    ),
                    contentDescription = "Météo",
                    modifier = Modifier
                        .size(50.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "24°C",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )

                    Text(
                        text = "Ensoleillé",
                        fontSize = 12.sp,
                        lineHeight = 14.sp
                    )
                }
            }

            VerticalDivider(
                modifier = Modifier.height(35.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    "Lieu visité",
                    modifier = Modifier.size(20.dp),

                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Rome  >",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp

                )
            }


        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Actions rapides",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = PaleGreen)
                    .clickable { onAddATrip()}
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.suitcase_icon
                    ),
                    contentDescription = "Ajouter un voyage",
                    modifier = Modifier
                        .size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nouveau\nvoyage",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = PaleBlue2)
                    .clickable { onAddPlace()}
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    "Ajouter un lieu",
                    modifier = Modifier.size(32.dp),
                    tint = BrightBlue,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ajouter\nun lieu",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = PaleOrange)
                    .clickable { onAddExpense()}
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.plus
                    ),
                    contentDescription = "Ajouter une dépense",
                    modifier = Modifier
                        .size(28.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ajouter\nune dépense",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = PalePurple)
                    .clickable { onAddPhoto()}
                    .padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.camera
                    ),
                    contentDescription = "Ajouter une photo",
                    modifier = Modifier
                        .size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ajouter\nune photo",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp
                )
            }

        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mes voyages",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextButton(
                onClick = { onViewAllTrips() }
            ) {
                Text(
                    text = "Voir tout >",
                    color = Turquoise
                )
            }


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = com.example.smart_trip.R.drawable.lisbonne
                    ),

                    contentDescription = "Mes voyages",
                    modifier = Modifier
                        .height(60.dp)
                        .clip(
                            RoundedCornerShape(
                            topStart = 10.dp,
                            bottomStart = 10.dp,
                            )
                        )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Lisbonne",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp
                    )

                    Text(
                        text = "4 déc. -> 8 déc. 2026",
                        fontSize = 16.sp,
                        lineHeight = 14.sp
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ">",
                    fontSize = 32.sp,
                    lineHeight = 20.sp

                )
            }
        }

    }

}
