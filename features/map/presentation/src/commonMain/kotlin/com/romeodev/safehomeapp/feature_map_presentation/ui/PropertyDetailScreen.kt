package com.romeodev.safehomeapp.feature_map_presentation.ui

import com.romeodev.safehomeapp.feature_map_presentation.events.*
import com.romeodev.safehomeapp.feature_map_presentation.states.*
import com.romeodev.safehomeapp.feature_map_presentation.models.*
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bathtub
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_map_presentation.events.PropertyDetailAction
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.PropertyDetailViewModel

@Composable
fun PropertyDetailScreen(
    propertyId: String,
    viewModel: PropertyDetailViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToConditionReport: (String) -> Unit,
    onNavigateToTitleValidation: (String) -> Unit,
    onNavigateToBookViewing: (String) -> Unit,
    onNavigateToChat: (String) -> Unit
) {
    LaunchedEffect(propertyId) {
        viewModel.onAction(PropertyDetailAction.LoadProperty(propertyId))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val property = state.property

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground,
        bottomBar = {
            // Bottom Action Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavigateToChat("chat-1") },
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SafeHomeColors.DarkCard)
                        .border(1.dp, SafeHomeColors.CardBorderLight, RoundedCornerShape(16.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Chat,
                        contentDescription = "Chat",
                        tint = SafeHomeColors.PrimaryBlue,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = { onNavigateToBookViewing(propertyId) },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SafeHomeColors.PrimaryBlue,
                        contentColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Book viewing",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        property?.let { prop ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Hero Image with Overlaid Navigation Controls
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .background(Color(0xFF141C2A))
                ) {
                    Icon(
                        imageVector = Icons.Default.Apartment,
                        contentDescription = null,
                        tint = Color(0xFF223147),
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.Center)
                    )

                    // Top Bar controls
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(Color(0xCC0C1017))
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xCC0C1017))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            IconButton(
                                onClick = { viewModel.onAction(PropertyDetailAction.ToggleFavorite) },
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xCC0C1017))
                            ) {
                                Icon(
                                    imageVector = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (state.isFavorite) SafeHomeColors.DangerRed else Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    // Verified Badge over Image
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(SafeHomeColors.VerifiedGreenBg)
                            .border(1.dp, SafeHomeColors.VerifiedGreenBorder, RoundedCornerShape(10.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.VerifiedUser,
                                contentDescription = null,
                                tint = SafeHomeColors.VerifiedGreen,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Title & Inspection 100% Verified",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.VerifiedGreen
                            )
                        }
                    }
                }

                // Details Content
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = prop.title,
                        fontSize = 26.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = SafeHomeColors.TextMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = prop.address,
                            fontSize = 13.sp,
                            color = SafeHomeColors.TextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "$${prop.price} ${prop.currency} / mo",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = SafeHomeColors.PrimaryBlue
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Specs Grid (Bedrooms, Bathrooms, Area, Parking)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SpecCard(icon = Icons.Default.Bed, label = "Beds", value = "${prop.bedrooms}", modifier = Modifier.weight(1f))
                        SpecCard(icon = Icons.Default.Bathtub, label = "Baths", value = "${prop.bathrooms}", modifier = Modifier.weight(1f))
                        SpecCard(icon = Icons.Default.SquareFoot, label = "Area", value = "${prop.areaM2} m²", modifier = Modifier.weight(1f))
                        SpecCard(icon = Icons.Default.DirectionsCar, label = "Parking", value = "${prop.parkingSpots}", modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Certified Inspection Section Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(SafeHomeColors.DarkSurface)
                            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                            .clickable { onNavigateToConditionReport(prop.id) }
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(SafeHomeColors.VerifiedGreenBg)
                                    .border(1.dp, SafeHomeColors.VerifiedGreenBorder, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${prop.inspectionScore}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SafeHomeColors.VerifiedGreen
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Certified Inspection Report",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Score 9.4/10 · Inspected Oct 4, 2026",
                                    fontSize = 12.sp,
                                    color = SafeHomeColors.TextSecondary
                                )
                            }

                            Text(
                                text = "View >",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.PrimaryBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Title Validation Section Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(SafeHomeColors.DarkSurface)
                            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                            .clickable { onNavigateToTitleValidation(prop.id) }
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF13223A))
                                    .border(1.dp, Color(0xFF233A5E), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Security,
                                    contentDescription = null,
                                    tint = SafeHomeColors.PrimaryBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Legal Title & Deed Verified",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Notary Public #128 · Zero debt verified",
                                    fontSize = 12.sp,
                                    color = SafeHomeColors.TextSecondary
                                )
                            }

                            Text(
                                text = "View >",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.PrimaryBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Host / Owner Profile Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(SafeHomeColors.DarkSurface)
                            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF243247)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "AS",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = prop.ownerName,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = SafeHomeColors.VerifiedGreen,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Text(
                                    text = "Trust score ${prop.ownerTrustScore} · Verified Host",
                                    fontSize = 12.sp,
                                    color = SafeHomeColors.TextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "About this property",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = prop.description,
                        fontSize = 14.sp,
                        color = SafeHomeColors.TextSecondary,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
private fun SpecCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(14.dp))
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SafeHomeColors.TextMuted,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = label,
                fontSize = 11.sp,
                color = SafeHomeColors.TextSecondary
            )
        }
    }
}
