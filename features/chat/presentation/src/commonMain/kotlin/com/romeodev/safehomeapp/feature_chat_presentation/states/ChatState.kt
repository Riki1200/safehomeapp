package com.romeodev.safehomeapp.feature_chat_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.EphemeralChat

data class ChatsUiState(
    val chats: List<EphemeralChat> = emptyList(),
    val selectedTab: Int = 0 // 0 = Active, 1 = Expired
)

data class ChatConversationUiState(
    val chat: EphemeralChat? = null,
    val inputText: String = "",
    val isSending: Boolean = false
)
