package com.romeodev.safehomeapp.feature_appointments_presentation.states

data class BookViewingUiState(
    val propertyId: String = "prop-1",
    val propertyTitle: String = "Apartment with terrace",
    val selectedDate: String = "Thu, Oct 8",
    val selectedTime: String = "16:00",
    val isVideoCall: Boolean = false,
    val isBooking: Boolean = false
)
