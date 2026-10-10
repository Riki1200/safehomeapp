package com.romeodev.safehomeapp.feature_owner_presentation.ui

import com.romeodev.safehomeapp.feature_owner_presentation.events.*
import com.romeodev.safehomeapp.feature_owner_presentation.states.*
import com.romeodev.safehomeapp.feature_owner_presentation.models.*
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel

@Composable
fun MyPropertiesScreen(
    viewModel: OwnerPortalViewModel,
    onNavigateToPublish: () -> Unit,
    onNavigateToOffers: (String) -> Unit,
    onNavigateToLeads: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My properties",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )

                Button(
                    onClick = onNavigateToPublish,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SafeHomeColors.PrimaryBlue,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "List", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                // Property 1 (For Sale)
                item {
                    OwnerPropertyCard(
                        category = "HOUSE · COYOACÁN",
                        title = "House with garden",
                        statusBadge = "Live · For sale",
                        views = 198,
                        viewings = 3,
                        thirdMetricLabel = "offers",
                        thirdMetricValue = 4,
                        buttonText = "Compare offers",
                        onButtonClick = { onNavigateToOffers("prop-2") }
                    )
                }

                // Property 2 (For Rent)
                item {
                    OwnerPropertyCard(
                        category = "APARTMENT · ROMA NORTE",
                        title = "Apartment with terrace",
                        statusBadge = "Live · For rent",
                        views = 114,
                        viewings = 2,
                        thirdMetricLabel = "leads",
                        thirdMetricValue = 4,
                        buttonText = "See interested buyers",
                        onButtonClick = onNavigateToLeads
                    )
                }

                // Property 3 (Pending Inspection)
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(SafeHomeColors.DarkSurface)
                            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(18.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF2E2412)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Engineering,
                                    contentDescription = null,
                                    tint = SafeHomeColors.WarningAmber,
                                    modifier = Modifier.size(22.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column {
                                Text(
                                    text = "Loft in Juárez",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Inspection booked · Mon Oct 12, 10:00",
                                    fontSize = 12.sp,
                                    color = SafeHomeColors.TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OwnerPropertyCard(
    category: String,
    title: String,
    statusBadge: String,
    views: Int,
    viewings: Int,
    thirdMetricLabel: String,
    thirdMetricValue: Int,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
    ) {
        Column {
            // Mock banner image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(0xFF131A26)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = SafeHomeColors.TextMuted
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SafeHomeColors.VerifiedGreenBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = statusBadge,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SafeHomeColors.VerifiedGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$views views · $viewings viewings · $thirdMetricValue $thirdMetricLabel",
                    fontSize = 12.sp,
                    color = SafeHomeColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SafeHomeColors.DarkCard,
                        contentColor = Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SafeHomeColors.CardBorderLight)
                ) {
                    Text(text = buttonText, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
