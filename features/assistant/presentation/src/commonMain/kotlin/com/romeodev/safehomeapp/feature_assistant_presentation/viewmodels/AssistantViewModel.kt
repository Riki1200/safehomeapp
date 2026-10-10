package com.romeodev.safehomeapp.feature_assistant_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_assistant_domain.logics.AskAssistantLogic
import com.romeodev.safehomeapp.feature_assistant_presentation.events.AssistantAction
import com.romeodev.safehomeapp.feature_assistant_presentation.events.AssistantEvent
import com.romeodev.safehomeapp.feature_assistant_presentation.models.AssistantMessage
import com.romeodev.safehomeapp.feature_assistant_presentation.states.AssistantUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val askAssistantLogic: AskAssistantLogic
) : MviViewModel<AssistantUiState, AssistantAction, AssistantEvent>() {

    override val initialState: AssistantUiState
        get() = AssistantUiState()

    override fun onAction(action: AssistantAction) {
        when (action) {
            is AssistantAction.UpdateInput -> _state.update { it.copy(inputText = action.text) }
            is AssistantAction.SelectPrompt -> {
                sendUserQuery(action.prompt)
            }
            AssistantAction.SendQuery -> {
                val text = _state.value.inputText.trim()
                if (text.isNotEmpty()) {
                    sendUserQuery(text)
                }
            }
        }
    }

    private fun sendUserQuery(text: String) {
        viewModelScope.launch {
            val userMsg = AssistantMessage(
                id = "u-${_state.value.messages.size + 1}",
                isUser = true,
                text = text,
                timestamp = "Just now"
            )
            _state.update { it.copy(inputText = "", messages = it.messages + userMsg, isThinking = true) }

            val replyText = askAssistantLogic(text)

            val aiMsg = AssistantMessage(
                id = "ai-${_state.value.messages.size + 2}",
                isUser = false,
                text = replyText,
                timestamp = "Just now"
            )

            _state.update { it.copy(messages = it.messages + aiMsg, isThinking = false) }
        }
    }
}
