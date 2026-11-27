package com.example.smart_trip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.R.drawable.calendar_suitecase
import com.example.smart_trip.ui.theme.Turquoise


@Composable
fun TripDatesCard (
    startDate: String,
    endDate: String,
    duration: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onEditClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Turquoise.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TripDateItem(
                value = startDate,
                label = "Départ",
                modifier = Modifier.weight(1f),
                icon = {
                    Image(
                        painter = painterResource(
                            id = calendar_suitecase
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )

            TripDateDivider()

            TripDateItem(
                value = endDate,
                label = "Retour",
                modifier = Modifier.weight(1f),
                icon = {
                    Image(
                        painter = painterResource(
                            id = calendar_suitecase
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

            )

            TripDateDivider()

            TripDateItem(
                value = duration,
                label = "Durée",
                modifier = Modifier.weight(1f),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Bedtime,
                        contentDescription = null,
                        tint = Turquoise,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            )
        }
    }

}

@Composable
private fun TripDateItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {}
){
    Row(
        modifier = modifier
                .padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        icon()

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        }
    }

}

@Composable
public fun TripDateDivider(){
    VerticalDivider(
        modifier = Modifier.height(40.dp),
        thickness = 1.dp,
        color = Color.LightGray
    )
}