package com.romeodev.safehomeapp.feature_appointments_presentation.models

data class AppointmentTimeSlot(
    val time: String,
    val isAvailable: Boolean = true
)
