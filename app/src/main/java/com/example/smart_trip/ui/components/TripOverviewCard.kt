package com.example.smart_trip.ui.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smart_trip.ui.theme.PaleGray

import com.example.smart_trip.ui.theme.Turquoise
import kotlin.math.roundToInt

@Composable
fun TripOverviewCard(
    initialBudget: Double,
    spentBudget: Double,
    visitedPlaces: Int,
    totalPlaces: Int,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    val remainingBudget = initialBudget - spentBudget

    val budgetProgress = if (initialBudget > 0) {
        (remainingBudget / initialBudget)
            .toFloat()
            .coerceIn(0f, 1f)
    } else {
        0f
    }

    val placesProgress = if (totalPlaces > 0) {
        (visitedPlaces.toFloat() / totalPlaces)
            .coerceIn(0f, 1f)
    } else {
        0f
    }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onEditClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 1. Indicateur circulaire du budget restant
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center

            ) {
                CircularProgressIndicator(
                    progress = { budgetProgress },
                    modifier = Modifier.fillMaxSize(),
                    color = Turquoise,
                    trackColor = Turquoise.copy(alpha = 0.1f),
                    strokeWidth = 8.dp,
                    strokeCap = StrokeCap.Round
                )
                Text(
                    text = "${(budgetProgress * 100).roundToInt()}%",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Turquoise
                )

            }

            // 2. Information du budget
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Budget",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = buildAnnotatedString {
                        append("%.0f €".format(initialBudget))
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(" initial")
                        }
                    },
                    fontSize = 14.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = buildAnnotatedString {
                        append("%.0f €".format(spentBudget))
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(" dépensé")
                        }
                    },
                    fontSize = 14.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (spentBudget > remainingBudget) Color.Red else Color.Blue

                )
                Text(
                    text = buildAnnotatedString {
                        append("%.0f €".format(remainingBudget))
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append(" restant")
                        }
                    },
                    fontSize = 14.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (spentBudget > initialBudget) Color.Red else Color.Black
                )

            }


            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 16.dp),
                thickness = 1.dp
            )

            // 3. Informations sur les lieux visités
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.offset(x = (-8).dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Icone lieu",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "Lieux",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = "$visitedPlaces / $totalPlaces",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "visités",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                LinearProgressIndicator(
                    progress = { placesProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Turquoise,
                    trackColor = Turquoise.copy(alpha = 0.1f),
                    strokeCap = StrokeCap.Round,
                )
            }

        }
    }

}








