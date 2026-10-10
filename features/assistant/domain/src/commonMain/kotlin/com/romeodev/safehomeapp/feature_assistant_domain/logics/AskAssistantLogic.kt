package com.romeodev.safehomeapp.feature_assistant_domain.logics

import com.romeodev.safehomeapp.feature_assistant_domain.repositories.AssistantRepository

class AskAssistantLogic(
    private val repository: AssistantRepository
) {
    suspend operator fun invoke(prompt: String): String = repository.askAssistant(prompt)
}
