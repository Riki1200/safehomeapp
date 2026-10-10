package com.romeodev.safehomeapp.feature_chat_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_chat_domain.logics.GetChatConversationLogic
import com.romeodev.safehomeapp.feature_chat_domain.logics.SendMessageLogic
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatConversationAction
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatConversationEvent
import com.romeodev.safehomeapp.feature_chat_presentation.states.ChatConversationUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatConversationViewModel(
    private val getChatConversationLogic: GetChatConversationLogic,
    private val sendMessageLogic: SendMessageLogic
) : MviViewModel<ChatConversationUiState, ChatConversationAction, ChatConversationEvent>() {

    override val initialState: ChatConversationUiState
        get() = ChatConversationUiState()

    override fun onAction(action: ChatConversationAction) {
        when (action) {
            is ChatConversationAction.LoadChat -> {
                getChatConversationLogic(action.id).onEach { chat ->
                    _state.update { it.copy(chat = chat) }
                }.launchIn(viewModelScope)
            }
            is ChatConversationAction.UpdateInput -> {
                _state.update { it.copy(inputText = action.text) }
            }
            ChatConversationAction.SendCurrentMessage -> {
                val currentText = _state.value.inputText.trim()
                val chatId = _state.value.chat?.id ?: return
                if (currentText.isEmpty()) return

                viewModelScope.launch {
                    _state.update { it.copy(inputText = "", isSending = true) }
                    sendMessageLogic(chatId, currentText)
                    _state.update { it.copy(isSending = false) }
                }
            }
        }
    }
}
