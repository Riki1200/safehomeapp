package com.romeodev.safehomeapp.feature_map_presentation.ui

import com.romeodev.safehomeapp.feature_map_presentation.events.*
import com.romeodev.safehomeapp.feature_map_presentation.states.*
import com.romeodev.safehomeapp.feature_map_presentation.models.*
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.romeodev.safehomeapp.feature_core_domain.models.InspectionCategory
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.PropertyDetailViewModel

@Composable
fun ConditionReportScreen(
    propertyId: String,
    viewModel: PropertyDetailViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val report = state.conditionReport

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
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
                        text = "Condition Report",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = state.property?.title ?: "Apartment with terrace",
                        fontSize = 12.sp,
                        color = SafeHomeColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Score Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(SafeHomeColors.VerifiedGreenBg)
                            .border(2.dp, SafeHomeColors.VerifiedGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${report?.overallScore ?: 9.2}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = SafeHomeColors.VerifiedGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Certified Excellent Condition",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Inspected by ${report?.inspectorName ?: "Eng. Carlos Mendoza"} · ${report?.inspectionDate ?: "Oct 4, 2026"}",
                        fontSize = 12.sp,
                        color = SafeHomeColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Detailed inspection breakdown",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Categories
            report?.categories?.forEach { category ->
                InspectionCategoryCard(category)
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SafeHomeColors.DarkSurface,
                    contentColor = SafeHomeColors.PrimaryBlue
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, SafeHomeColors.PrimaryBlue)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Download official PDF certificate",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun InspectionCategoryCard(category: InspectionCategory) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = "${category.score}/10 · ${category.status}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = SafeHomeColors.VerifiedGreen
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { (category.score / 10f).toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = SafeHomeColors.VerifiedGreen,
                trackColor = Color(0xFF1B2433)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = category.details,
                fontSize = 12.sp,
                color = SafeHomeColors.TextSecondary,
                lineHeight = 18.sp
            )
        }
    }
}
