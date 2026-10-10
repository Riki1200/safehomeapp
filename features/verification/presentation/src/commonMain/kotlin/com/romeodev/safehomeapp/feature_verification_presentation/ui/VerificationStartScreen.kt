package com.romeodev.safehomeapp.feature_verification_presentation.ui

import com.romeodev.safehomeapp.feature_verification_presentation.events.*
import com.romeodev.safehomeapp.feature_verification_presentation.states.*
import com.romeodev.safehomeapp.feature_verification_presentation.models.*
import com.romeodev.safehomeapp.feature_verification_presentation.viewmodels.*

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
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors

@Composable
fun VerificationStartScreen(
    onNavigateBack: () -> Unit,
    onStartVerification: () -> Unit
) {
    val darkBg = SafeHomeColors.DarkBackground
    val cardBg = SafeHomeColors.DarkSurface
    val borderCol = SafeHomeColors.CardBorder
    val accentBlue = SafeHomeColors.PrimaryBlue
    val textMuted = SafeHomeColors.TextMuted

    Scaffold(
        containerColor = darkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
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
                            .background(cardBg)
                            .border(1.dp, borderCol, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "IDENTITY VERIFICATION · 1/3",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp,
                            color = textMuted
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = { 0.33f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp)),
                            color = accentBlue,
                            trackColor = Color(0xFF1E2638)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Verify your identity",
                    fontSize = 32.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    lineHeight = 38.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "SafeHome requires verified identity for all buyers, renters, and owners. No fake profiles.",
                    fontSize = 15.sp,
                    color = textMuted,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                VerificationStepCard(
                    stepNumber = "1",
                    title = "Official ID",
                    description = "Passport, INE/IFE or driver's license. Front and back.",
                    cardBg = cardBg,
                    borderCol = borderCol
                )

                Spacer(modifier = Modifier.height(12.dp))

                VerificationStepCard(
                    stepNumber = "2",
                    title = "Live face scan",
                    description = "3-second biometric selfie to confirm it's really you.",
                    cardBg = cardBg,
                    borderCol = borderCol
                )

                Spacer(modifier = Modifier.height(12.dp))

                VerificationStepCard(
                    stepNumber = "3",
                    title = "Review & badge",
                    description = "Verified badge on your profile and access to book viewings.",
                    cardBg = cardBg,
                    borderCol = borderCol
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Privacy guarantee card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF131C2E))
                        .border(1.dp, Color(0xFF1E2E4B), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1A2640)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = accentBlue,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Data privacy guarantee",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Your data is encrypted end-to-end and deleted after verification. We never sell your personal information.",
                                fontSize = 12.sp,
                                color = textMuted,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onStartVerification,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = accentBlue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Start verification",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun VerificationStepCard(
    stepNumber: String,
    title: String,
    description: String,
    cardBg: Color,
    borderCol: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(cardBg)
            .border(1.dp, borderCol, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1E2638)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = SafeHomeColors.TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
