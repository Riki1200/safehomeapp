package com.romeodev.safehomeapp.feature_chat_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_chat_domain.logics.ReportScamLogic
import com.romeodev.safehomeapp.feature_chat_presentation.events.ReportScamAction
import com.romeodev.safehomeapp.feature_chat_presentation.events.ReportScamEvent
import com.romeodev.safehomeapp.feature_chat_presentation.states.ReportScamUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReportScamViewModel(
    private val reportScamLogic: ReportScamLogic
) : MviViewModel<ReportScamUiState, ReportScamAction, ReportScamEvent>() {

    override val initialState: ReportScamUiState
        get() = ReportScamUiState()

    override fun onAction(action: ReportScamAction) {
        when (action) {
            is ReportScamAction.SelectReason -> _state.update { it.copy(selectedReason = action.reason) }
            is ReportScamAction.UpdateDescription -> _state.update { it.copy(description = action.text) }
            ReportScamAction.SubmitReport -> {
                viewModelScope.launch {
                    _state.update { it.copy(isSubmitting = true) }
                    reportScamLogic(_state.value.selectedReason, _state.value.description)
                    _state.update { it.copy(isSubmitting = false) }
                    emitEvent(ReportScamEvent.ReportSubmitted)
                }
            }
        }
    }
}
