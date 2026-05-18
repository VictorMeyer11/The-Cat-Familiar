package com.example.thecatfamiliar.character.presentation.overview.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecatfamiliar.core.Colors
import org.jetbrains.compose.resources.stringResource
import thecatfamiliar.composeapp.generated.resources.Res
import thecatfamiliar.composeapp.generated.resources.health_points

@Composable
fun HealthPointsCard(
    currentHp: Int,
    maxHp: Int,
    tempHp: Int,
    onHpIncrement: () -> Unit,
    onHpDecrement: () -> Unit
) {
    val ratio = (currentHp.toFloat() / maxHp.toFloat()).coerceIn(0f, 1f)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Colors.CardBeige),
        border = BorderStroke(1.dp, Colors.BorderGrey),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = stringResource(Res.string.health_points),
                fontFamily = FontFamily.SansSerif,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.8.sp,
                color = Colors.TextMuted,
            )
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedIconCircle(onClick = onHpDecrement) {
                        Text(
                            "−",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = Colors.ForestGreen,
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        currentHp.toString(),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        color = Colors.ForestGreen,
                    )
                    Text(
                        " / ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 28.sp,
                        color = Colors.HpTrack,
                    )
                    Text(
                        maxHp.toString(),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp,
                        color = Colors.TextMuted,
                    )
                    Spacer(Modifier.width(12.dp))
                    OutlinedIconCircle(onClick = onHpIncrement) {
                        Icon(Icons.Default.Add, contentDescription = "Increase HP", tint = Colors.ForestGreen)
                    }
                }
                if(tempHp > 0) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Colors.ChipGrey)
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                    ) {
                        Text(
                            "+$tempHp Temp",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            color = Colors.TextMuted,
                        )
                    }
                }
            }
            Spacer(Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Colors.HpTrack),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(ratio)
                        .background(Colors.ForestGreen),
                )
            }
        }
    }
}