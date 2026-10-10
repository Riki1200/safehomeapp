package com.romeodev.safehomeapp.feature_appointments_presentation.events

sealed interface AppointmentsAction {
    data class SelectTab(val index: Int) : AppointmentsAction
}

sealed interface AppointmentsEvent {
    data class NavigateToChat(val chatId: String) : AppointmentsEvent
}
