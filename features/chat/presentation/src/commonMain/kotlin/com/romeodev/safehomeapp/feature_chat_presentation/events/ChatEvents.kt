package com.romeodev.safehomeapp.feature_chat_presentation.events

sealed interface ChatsAction {
    data class SelectTab(val index: Int) : ChatsAction
}

sealed interface ChatsEvent {
    data class NavigateToConversation(val chatId: String) : ChatsEvent
}

sealed interface ChatConversationAction {
    data class LoadChat(val id: String) : ChatConversationAction
    data class UpdateInput(val text: String) : ChatConversationAction
    data object SendCurrentMessage : ChatConversationAction
}

sealed interface ChatConversationEvent {
    data object NavigateBack : ChatConversationEvent
    data class NavigateToReportScam(val chatId: String) : ChatConversationEvent
}
