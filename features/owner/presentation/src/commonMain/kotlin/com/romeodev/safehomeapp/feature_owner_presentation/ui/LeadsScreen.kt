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
import com.romeodev.safehomeapp.feature_core_domain.models.Lead
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalAction
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel

@Composable
fun LeadsScreen(
    viewModel: OwnerPortalViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val filterOptions = listOf("All", "New", "Booked", "Visited", "Offered")

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Leads",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Filter pills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filterOptions.forEach { filter ->
                    val isSelected = state.selectedLeadFilter == filter
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(18.dp)
                            )
                            .clickable { viewModel.onAction(OwnerPortalAction.SelectLeadFilter(filter)) }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isSelected) Color.White else SafeHomeColors.TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Leads List Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 4.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.filteredLeads) { lead ->
                        LeadItemRow(lead)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(SafeHomeColors.DividerColor)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LeadItemRow(lead: Lead) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF223046)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = lead.initials,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${lead.name} · ${lead.trustScore}",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${lead.propertyTitle} — ${lead.actionText}",
                fontSize = 12.sp,
                color = SafeHomeColors.TextSecondary,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(horizontalAlignment = Alignment.End) {
            val (badgeBg, badgeColor) = when (lead.statusTag) {
                "Offered" -> SafeHomeColors.VerifiedGreenBg to SafeHomeColors.VerifiedGreen
                "Booked" -> SafeHomeColors.WarningAmberBg to SafeHomeColors.WarningAmber
                "New" -> Color(0xFF162544) to SafeHomeColors.PrimaryBlue
                else -> Color(0xFF1E2838) to SafeHomeColors.TextSecondary
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(badgeBg)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = lead.statusTag,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = badgeColor
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = lead.timestamp,
                fontSize = 10.sp,
                color = SafeHomeColors.TextMuted
            )
        }
    }
}
