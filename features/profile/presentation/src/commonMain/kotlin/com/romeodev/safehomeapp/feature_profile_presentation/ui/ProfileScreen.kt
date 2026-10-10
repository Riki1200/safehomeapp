package com.romeodev.safehomeapp.feature_profile_presentation.ui

import com.romeodev.safehomeapp.feature_profile_presentation.events.*
import com.romeodev.safehomeapp.feature_profile_presentation.states.*
import com.romeodev.safehomeapp.feature_profile_presentation.models.*
import com.romeodev.safehomeapp.feature_profile_presentation.viewmodels.*

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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.romeodev.safehomeapp.feature_profile_presentation.events.ProfileAction
import com.romeodev.safehomeapp.feature_profile_presentation.events.ProfileEvent
import com.romeodev.safehomeapp.feature_profile_presentation.viewmodels.ProfileViewModel
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToLanguage: () -> Unit,
    onNavigateToReportScam: () -> Unit,
    onSignOut: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.uiEvents) { event ->
        when (event) {
            ProfileEvent.SignedOut -> onSignOut()
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
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Avatar photo circle
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF19253B))
                    .border(2.dp, SafeHomeColors.PrimaryBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PHOTO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SafeHomeColors.TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.userName,
                fontSize = 26.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Verified Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SafeHomeColors.VerifiedGreenBg)
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SafeHomeColors.VerifiedGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Identity verified",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SafeHomeColors.VerifiedGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Toggles Card (Owner mode, Dark mode)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = SafeHomeColors.TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Text(
                                text = "Owner mode",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }

                        Switch(
                            checked = state.isOwnerMode,
                            onCheckedChange = { viewModel.onAction(ProfileAction.ToggleOwnerMode(it)) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = SafeHomeColors.PrimaryBlue,
                                uncheckedThumbColor = SafeHomeColors.TextMuted,
                                uncheckedTrackColor = SafeHomeColors.DarkCard
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(SafeHomeColors.DividerColor)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = null,
                                tint = SafeHomeColors.TextSecondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Text(
                                text = "Dark mode",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        }

                        Switch(
                            checked = state.isDarkMode,
                            onCheckedChange = { viewModel.onAction(ProfileAction.ToggleDarkMode(it)) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = SafeHomeColors.PrimaryBlue,
                                uncheckedThumbColor = SafeHomeColors.TextMuted,
                                uncheckedTrackColor = SafeHomeColors.DarkCard
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Links Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(20.dp))
            ) {
                Column {
                    ProfileMenuRow(
                        icon = Icons.Default.Language,
                        title = "Language",
                        trailingText = state.selectedLanguage,
                        onClick = onNavigateToLanguage
                    )
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SafeHomeColors.DividerColor))
                    ProfileMenuRow(
                        icon = Icons.Default.Folder,
                        title = "My documents",
                        onClick = { }
                    )
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SafeHomeColors.DividerColor))
                    ProfileMenuRow(
                        icon = Icons.Default.CalendarToday,
                        title = "Viewing history",
                        onClick = { }
                    )
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SafeHomeColors.DividerColor))
                    ProfileMenuRow(
                        icon = Icons.Default.Bookmark,
                        title = "Saved",
                        onClick = { }
                    )
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SafeHomeColors.DividerColor))
                    ProfileMenuRow(
                        icon = Icons.Default.ReportProblem,
                        title = "Report a scam",
                        iconTint = SafeHomeColors.DangerRed,
                        onClick = onNavigateToReportScam
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Sign out button
            OutlinedButton(
                onClick = { viewModel.onAction(ProfileAction.SignOut) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = SafeHomeColors.DangerRed
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF4A1E24))
            ) {
                Text(
                    text = "Sign out",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SafeHomeColors.DangerRed
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ProfileMenuRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    trailingText: String? = null,
    iconTint: Color = SafeHomeColors.TextSecondary,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            trailingText?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = SafeHomeColors.TextSecondary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = SafeHomeColors.TextMuted,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}
