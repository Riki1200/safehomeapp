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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
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
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalAction
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel

@Composable
fun OffersScreen(
    propertyId: String,
    viewModel: OwnerPortalViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sortOptions = listOf("Score", "Amount", "Trust")

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

                Text(
                    text = "Offers",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "House with garden",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Listed at $8,950,000 · ${state.offers.size} offers",
                fontSize = 13.sp,
                color = SafeHomeColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sort row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Sort",
                    fontSize = 12.sp,
                    color = SafeHomeColors.TextMuted,
                    modifier = Modifier.padding(end = 4.dp)
                )

                sortOptions.forEach { sort ->
                    val isSelected = state.selectedOfferSort == sort
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                            .border(
                                1.dp,
                                if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { viewModel.onAction(OwnerPortalAction.SortOffers(sort)) }
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = sort,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isSelected) Color.White else SafeHomeColors.TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(state.offers) { offer ->
                    OfferCard(offer)
                }
            }
        }
    }
}

@Composable
private fun OfferCard(offer: PropertyOffer) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(
                1.5.dp,
                if (offer.isRecommended) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                RoundedCornerShape(20.dp)
            )
            .padding(18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${offer.rank}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = SafeHomeColors.TextMuted
                )

                if (offer.isRecommended) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SafeHomeColors.PrimaryBlue)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Recommended",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = offer.buyerName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${formatPrice(offer.amount)}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = offer.priceDiff,
                    fontSize = 12.sp,
                    color = if (offer.priceDiff.startsWith("+")) SafeHomeColors.VerifiedGreen else SafeHomeColors.TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            OfferDetailRow("Payment", offer.paymentType)
            OfferDetailRow("Proof of funds", offer.proofOfFunds)
            OfferDetailRow("Estimated closing", "${offer.estimatedClosingDays} days")
            OfferDetailRow("Viewing", if (offer.visitedProperty) "✓ Visited the property" else "Video preview only")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Buyer trust",
                    fontSize = 12.sp,
                    color = SafeHomeColors.TextSecondary
                )
                Text(
                    text = "${offer.buyerTrustScore}/100",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SafeHomeColors.VerifiedGreen
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { (offer.buyerTrustScore / 100f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = SafeHomeColors.VerifiedGreen,
                trackColor = Color(0xFF1B2433)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Counter & Accept buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SafeHomeColors.CardBorderLight)
                ) {
                    Text(text = "Counter", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SafeHomeColors.PrimaryBlue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Accept", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun OfferDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 12.sp, color = SafeHomeColors.TextMuted)
        Text(text = value, fontSize = 12.sp, color = Color.White)
    }
}

private fun formatPrice(amount: Long): String {
    return amount.toString().reversed().chunked(3).joinToString(",").reversed()
}
