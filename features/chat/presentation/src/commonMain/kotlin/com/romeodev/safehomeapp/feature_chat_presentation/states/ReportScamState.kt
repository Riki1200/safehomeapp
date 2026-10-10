package com.romeodev.safehomeapp.feature_chat_presentation.states

data class ReportScamUiState(
    val reasons: List<String> = listOf(
        "Asked for payment or deposit outside SafeHome",
        "Listing photos or details do not match reality",
        "Identity mismatch or suspicious behavior",
        "Harassment or inappropriate communication",
        "Property is already occupied or unavailable",
        "Other safety concern"
    ),
    val selectedReason: String = "Asked for payment or deposit outside SafeHome",
    val description: String = "",
    val isSubmitting: Boolean = false
)
