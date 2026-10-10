package com.romeodev.safehomeapp.feature_map_presentation.ui

import com.romeodev.safehomeapp.feature_map_presentation.events.*
import com.romeodev.safehomeapp.feature_map_presentation.states.*
import com.romeodev.safehomeapp.feature_map_presentation.models.*
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.*

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_map_presentation.events.ExploreAction
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.ExploreViewModel

@Composable
fun ExploreMapScreen(
    viewModel: ExploreViewModel,
    onNavigateToResults: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SafeHomeColors.DarkBackground)
    ) {
        // Dark Map canvas simulation
        Canvas(modifier = Modifier.fillMaxSize()) {
            val gridStep = 40.dp.toPx()
            val lineColor = Color(0xFF131A26)
            for (x in 0..(size.width / gridStep).toInt()) {
                drawLine(
                    color = lineColor,
                    start = Offset(x * gridStep, 0f),
                    end = Offset(x * gridStep, size.height),
                    strokeWidth = 1f
                )
            }
            for (y in 0..(size.height / gridStep).toInt()) {
                drawLine(
                    color = lineColor,
                    start = Offset(0f, y * gridStep),
                    end = Offset(size.width, y * gridStep),
                    strokeWidth = 1f
                )
            }
        }

        // Floating Map Pins
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 60.dp)
        ) {
            MapZonePin(
                zoneName = "Roma Norte",
                price = "$28k",
                score = "94 Safe",
                isSelected = state.selectedZone == "Roma Norte",
                onClick = { viewModel.onAction(ExploreAction.SelectZone("Roma Norte")) }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 40.dp, top = 220.dp)
        ) {
            MapZonePin(
                zoneName = "Condesa",
                price = "$32k",
                score = "92 Safe",
                isSelected = state.selectedZone == "Condesa",
                onClick = { viewModel.onAction(ExploreAction.SelectZone("Condesa")) }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 40.dp, top = 260.dp)
        ) {
            MapZonePin(
                zoneName = "Polanco",
                price = "$48k",
                score = "96 Safe",
                isSelected = state.selectedZone == "Polanco",
                onClick = { viewModel.onAction(ExploreAction.SelectZone("Polanco")) }
            )
        }

        // Top Search & Chips Overlay
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SafeHomeColors.DarkSurface)
                        .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                ) {
                    TextField(
                        value = state.searchQuery,
                        onValueChange = { viewModel.onAction(ExploreAction.SearchQueryChanged(it)) },
                        placeholder = {
                            Text(
                                text = "Search zone, neighborhood...",
                                color = SafeHomeColors.TextMuted,
                                fontSize = 14.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = SafeHomeColors.TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(
                    onClick = onNavigateToResults,
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SafeHomeColors.DarkSurface)
                        .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.FormatListBulleted,
                        contentDescription = "List view",
                        tint = SafeHomeColors.PrimaryBlue,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Zone quick selection pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.zones.forEach { zone ->
                    val isSelected = state.selectedZone == zone.name
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable { viewModel.onAction(ExploreAction.SelectZone(zone.name)) }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${zone.name} · ${zone.safetyScore} Safe",
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                                color = if (isSelected) Color.White else SafeHomeColors.TextSecondary
                            )
                        }
                    }
                }
            }
        }

        // Bottom Selected Zone Preview Sheet
        state.selectedProperty?.let { prop ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorderLight, RoundedCornerShape(20.dp))
                    .clickable { onNavigateToDetail(prop.id) }
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = SafeHomeColors.VerifiedGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${prop.zone} · 94/100 Safe Index",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.VerifiedGreen
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SafeHomeColors.VerifiedGreenBg)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "Score ${prop.inspectionScore}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SafeHomeColors.VerifiedGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = prop.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$${prop.price} ${prop.currency} / mo · ${prop.bedrooms} beds · ${prop.bathrooms} baths · ${prop.areaM2} m²",
                        fontSize = 13.sp,
                        color = SafeHomeColors.TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "✓ Title & Notary 128 verified",
                            fontSize = 12.sp,
                            color = SafeHomeColors.VerifiedGreen
                        )
                        Text(
                            text = "View details >",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SafeHomeColors.PrimaryBlue
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MapZonePin(
    zoneName: String,
    price: String,
    score: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
            .border(
                1.5.dp,
                if (isSelected) SafeHomeColors.PrimaryBlueLight else SafeHomeColors.CardBorderLight,
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = zoneName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "$price · $score",
                fontSize = 10.sp,
                color = if (isSelected) Color.White.copy(alpha = 0.9f) else SafeHomeColors.TextSecondary
            )
        }
    }
}
