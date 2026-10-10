package com.romeodev.safehomeapp.feature_appointments_presentation.events

sealed interface BookViewingAction {
    data class SelectDate(val date: String) : BookViewingAction
    data class SelectTime(val time: String) : BookViewingAction
    data class SetVideoCall(val isVideo: Boolean) : BookViewingAction
    data object ConfirmBooking : BookViewingAction
}

sealed interface BookViewingEvent {
    data class BookingConfirmed(val code: String, val propertyTitle: String, val dateTime: String) : BookViewingEvent
}
