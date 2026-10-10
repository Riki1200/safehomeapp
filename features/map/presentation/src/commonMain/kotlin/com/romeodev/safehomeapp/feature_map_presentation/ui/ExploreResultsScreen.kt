package com.romeodev.safehomeapp.feature_map_presentation.ui

import com.romeodev.safehomeapp.feature_map_presentation.events.*
import com.romeodev.safehomeapp.feature_map_presentation.states.*
import com.romeodev.safehomeapp.feature_map_presentation.models.*
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shield
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_map_presentation.events.ExploreAction
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.ExploreViewModel

@Composable
fun ExploreResultsScreen(
    viewModel: ExploreViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val filters = listOf("All types", "Price", "2+ beds", "Verified only")

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
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
                        text = "Results in ${state.selectedZone}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${state.filteredProperties.size} verified properties found",
                        fontSize = 12.sp,
                        color = SafeHomeColors.TextSecondary
                    )
                }
            }

            // Filter pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = state.selectedFilter == filter
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(18.dp)
                            )
                            .clickable { viewModel.onAction(ExploreAction.SelectFilter(filter)) }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isSelected) Color.White else SafeHomeColors.TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Properties list
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.filteredProperties) { property ->
                    PropertyListItemCard(
                        property = property,
                        onClick = { onNavigateToDetail(property.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun PropertyListItemCard(
    property: Property,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ) {
        Column {
            // Photo thumbnail with badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color(0xFF151E2D))
            ) {
                Icon(
                    imageVector = Icons.Default.Apartment,
                    contentDescription = null,
                    tint = Color(0xFF263750),
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )

                // Top left badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xCC0B1019))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Live · Verified",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SafeHomeColors.VerifiedGreen
                    )
                }

                // Top right score pill
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SafeHomeColors.VerifiedGreenBg)
                        .border(1.dp, SafeHomeColors.VerifiedGreenBorder, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Inspection ${property.inspectionScore}/10",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SafeHomeColors.VerifiedGreen
                    )
                }
            }

            // Property details
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = property.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${property.price} ${property.currency} / mo · ${property.zone}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = SafeHomeColors.PrimaryBlue
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${property.bedrooms} beds · ${property.bathrooms} baths · ${property.areaM2} m² · 1 parking",
                    fontSize = 12.sp,
                    color = SafeHomeColors.TextSecondary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = SafeHomeColors.VerifiedGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Deed & Notary verified · Zero lien debt",
                        fontSize = 12.sp,
                        color = SafeHomeColors.VerifiedGreen
                    )
                }
            }
        }
    }
}
