package com.romeodev.safehomeapp.feature_appointments_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.Appointment

data class AppointmentsUiState(
    val appointments: List<Appointment> = emptyList(),
    val selectedTab: Int = 0 // 0 = Upcoming, 1 = Past
)
