package com.romeodev.safehomeapp.feature_assistant_presentation.models

data class AssistantMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val timestamp: String
)
