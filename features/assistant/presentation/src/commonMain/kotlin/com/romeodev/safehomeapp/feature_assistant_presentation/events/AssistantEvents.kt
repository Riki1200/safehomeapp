package com.romeodev.safehomeapp.feature_assistant_presentation.events

sealed interface AssistantAction {
    data class UpdateInput(val text: String) : AssistantAction
    data class SelectPrompt(val prompt: String) : AssistantAction
    data object SendQuery : AssistantAction
}

sealed interface AssistantEvent
