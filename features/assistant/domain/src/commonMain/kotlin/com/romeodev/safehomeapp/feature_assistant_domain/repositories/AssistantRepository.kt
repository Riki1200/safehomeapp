package com.romeodev.safehomeapp.feature_assistant_domain.repositories

interface AssistantRepository {
    suspend fun askAssistant(prompt: String): String
}
