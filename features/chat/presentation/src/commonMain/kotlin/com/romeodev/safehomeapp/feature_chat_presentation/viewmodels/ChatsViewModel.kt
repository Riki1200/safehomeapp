package com.romeodev.safehomeapp.feature_chat_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_chat_domain.logics.GetChatsLogic
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatsAction
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatsEvent
import com.romeodev.safehomeapp.feature_chat_presentation.states.ChatsUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ChatsViewModel(
    private val getChatsLogic: GetChatsLogic
) : MviViewModel<ChatsUiState, ChatsAction, ChatsEvent>() {

    override val initialState: ChatsUiState
        get() = ChatsUiState()

    init {
        getChatsLogic().onEach { list ->
            _state.update { it.copy(chats = list) }
        }.launchIn(viewModelScope)
    }

    override fun onAction(action: ChatsAction) {
        when (action) {
            is ChatsAction.SelectTab -> _state.update { it.copy(selectedTab = action.index) }
        }
    }
}
