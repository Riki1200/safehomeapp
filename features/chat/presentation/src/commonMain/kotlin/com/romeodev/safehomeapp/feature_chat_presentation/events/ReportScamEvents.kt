package com.romeodev.safehomeapp.feature_chat_presentation.events

sealed interface ReportScamAction {
    data class SelectReason(val reason: String) : ReportScamAction
    data class UpdateDescription(val text: String) : ReportScamAction
    data object SubmitReport : ReportScamAction
}

sealed interface ReportScamEvent {
    data object ReportSubmitted : ReportScamEvent
}
