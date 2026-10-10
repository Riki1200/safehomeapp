package com.romeodev.safehomeapp.feature_owner_presentation.ui

import com.romeodev.safehomeapp.feature_owner_presentation.events.*
import com.romeodev.safehomeapp.feature_owner_presentation.states.*
import com.romeodev.safehomeapp.feature_owner_presentation.models.*
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.*

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.romeodev.safehomeapp.feature_core_domain.models.ListingType
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyType
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalAction
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalEvent
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents

@Composable
fun PublishPropertyScreen(
    viewModel: OwnerPortalViewModel,
    onNavigateBack: () -> Unit,
    onListingSubmitted: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.uiEvents) { event ->
        when (event) {
            OwnerPortalEvent.ListingSubmitted -> onListingSubmitted()
        }
    }

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(SafeHomeColors.DarkSurface)
                            .border(1.dp, SafeHomeColors.CardBorder, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "NEW LISTING · Listing type 1/5",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = SafeHomeColors.TextMuted
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { 0.2f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = SafeHomeColors.PrimaryBlue,
                            trackColor = Color(0xFF1E2638)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "What are you listing?",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Rent vs Sale toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (state.listingType == ListingType.RENT) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (state.listingType == ListingType.RENT) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { viewModel.onAction(OwnerPortalAction.SetListingType(ListingType.RENT)) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Rent",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (state.listingType == ListingType.RENT) Color.White else SafeHomeColors.TextSecondary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (state.listingType == ListingType.SALE) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (state.listingType == ListingType.SALE) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { viewModel.onAction(OwnerPortalAction.SetListingType(ListingType.SALE)) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sale",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (state.listingType == ListingType.SALE) Color.White else SafeHomeColors.TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "PROPERTY TYPE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = SafeHomeColors.TextMuted
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Property type chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PropertyType.entries.forEach { type ->
                        val isSelected = state.propertyType == type
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp))
                                .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                                .border(
                                    1.dp,
                                    if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                    RoundedCornerShape(18.dp)
                                )
                                .clickable { viewModel.onAction(OwnerPortalAction.SetPropertyType(type)) }
                                .padding(horizontal = 16.dp, vertical = 9.dp)
                        ) {
                            Text(
                                text = type.label,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                                color = if (isSelected) Color.White else SafeHomeColors.TextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = if (state.listingType == ListingType.RENT) "Monthly rent (MXN)" else "Price (MXN)",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SafeHomeColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SafeHomeColors.DarkSurface)
                        .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
                ) {
                    TextField(
                        value = state.priceInput,
                        onValueChange = { viewModel.onAction(OwnerPortalAction.UpdatePrice(it)) },
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

                Spacer(modifier = Modifier.height(20.dp))

                // Bedrooms, Bathrooms, Area row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SpecInputField(
                        label = "Bedrooms",
                        value = "${state.bedrooms}",
                        onValueChange = { it.toIntOrNull()?.let { v -> viewModel.onAction(OwnerPortalAction.UpdateBedrooms(v)) } },
                        modifier = Modifier.weight(1f)
                    )
                    SpecInputField(
                        label = "Bathrooms",
                        value = "${state.bathrooms}",
                        onValueChange = { it.toIntOrNull()?.let { v -> viewModel.onAction(OwnerPortalAction.UpdateBathrooms(v)) } },
                        modifier = Modifier.weight(1f)
                    )
                    SpecInputField(
                        label = "m²",
                        value = "${state.areaM2}",
                        onValueChange = { it.toIntOrNull()?.let { v -> viewModel.onAction(OwnerPortalAction.UpdateArea(v)) } },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onAction(OwnerPortalAction.SubmitListing) },
                enabled = !state.isPublishing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SafeHomeColors.PrimaryBlue,
                    contentColor = Color.White
                )
            ) {
                if (state.isPublishing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Continue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun SpecInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = SafeHomeColors.TextSecondary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SafeHomeColors.DarkSurface)
                .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(14.dp))
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
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
    }
}
