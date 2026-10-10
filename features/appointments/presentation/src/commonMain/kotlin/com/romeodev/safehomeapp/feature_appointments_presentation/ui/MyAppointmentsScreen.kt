package com.romeodev.safehomeapp.feature_appointments_presentation.ui

import com.romeodev.safehomeapp.feature_appointments_presentation.events.*
import com.romeodev.safehomeapp.feature_appointments_presentation.states.*
import com.romeodev.safehomeapp.feature_appointments_presentation.models.*
import com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels.*

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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.romeodev.safehomeapp.feature_core_domain.models.Appointment
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_appointments_presentation.events.AppointmentsAction
import com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels.AppointmentsViewModel

@Composable
fun MyAppointmentsScreen(
    viewModel: AppointmentsViewModel,
    onNavigateToChat: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val upcoming = state.appointments.filter { it.isUpcoming }
    val past = state.appointments.filter { !it.isUpcoming }

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
                text = "My appointments",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(14.dp))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (state.selectedTab == 0) SafeHomeColors.PrimaryBlue else Color.Transparent)
                        .clickable { viewModel.onAction(AppointmentsAction.SelectTab(0)) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Upcoming (${upcoming.size})",
                        fontSize = 13.sp,
                        fontWeight = if (state.selectedTab == 0) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (state.selectedTab == 0) Color.White else SafeHomeColors.TextSecondary
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (state.selectedTab == 1) SafeHomeColors.PrimaryBlue else Color.Transparent)
                        .clickable { viewModel.onAction(AppointmentsAction.SelectTab(1)) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Past (${past.size})",
                        fontSize = 13.sp,
                        fontWeight = if (state.selectedTab == 1) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (state.selectedTab == 1) Color.White else SafeHomeColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            val currentList = if (state.selectedTab == 0) upcoming else past

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(currentList) { appointment ->
                    AppointmentCard(
                        appointment = appointment,
                        onChatClick = { onNavigateToChat("chat-1") }
                    )
                }
            }
        }
    }
}

@Composable
private fun AppointmentCard(
    appointment: Appointment,
    onChatClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(18.dp))
            .padding(18.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF1E283A))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${appointment.date} · ${appointment.time}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    if (appointment.isVideoCall) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF222036))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Video call",
                                fontSize = 11.sp,
                                color = Color(0xFFA78BFA)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SafeHomeColors.VerifiedGreenBg)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = appointment.status,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SafeHomeColors.VerifiedGreen
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = appointment.propertyTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Host: ${appointment.hostName} · Code: ${appointment.bookingCode}",
                fontSize = 13.sp,
                color = SafeHomeColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onChatClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SafeHomeColors.PrimaryBlue,
                        contentColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Chat with owner", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SafeHomeColors.CardBorderLight)
                ) {
                    Text(text = "Reschedule", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
