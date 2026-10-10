package com.romeodev.safehomeapp.feature_assistant_presentation.states

import com.romeodev.safehomeapp.feature_assistant_presentation.models.AssistantMessage

data class AssistantUiState(
    val verifiedDocs: List<String> = listOf("INE ✓", "Public Deed #45,820 ✓", "Predial 2026 ✓", "Water SACMEX ✓"),
    val suggestedPrompts: List<String> = listOf(
        "Explain security deposit return rules in CDMX",
        "Is the maintenance fee included legally?",
        "Review early lease termination penalty",
        "Verify notary registration status"
    ),
    val messages: List<AssistantMessage> = listOf(
        AssistantMessage(
            id = "ai-1",
            isUser = false,
            text = "Hello! I am your SafeHome AI Legal & Real Estate Assistant. I have analyzed the verified public deed and inspection report for your selected properties. How can I protect your transaction today?",
            timestamp = "10:00 AM"
        )
    ),
    val inputText: String = "",
    val isThinking: Boolean = false
)
