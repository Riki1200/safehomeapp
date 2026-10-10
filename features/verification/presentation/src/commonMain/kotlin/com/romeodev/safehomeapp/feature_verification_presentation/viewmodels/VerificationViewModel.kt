package com.romeodev.safehomeapp.feature_verification_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_verification_presentation.events.VerificationAction
import com.romeodev.safehomeapp.feature_verification_presentation.events.VerificationEvent
import com.romeodev.safehomeapp.feature_verification_presentation.states.VerificationUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerificationViewModel : MviViewModel<VerificationUiState, VerificationAction, VerificationEvent>() {

    override val initialState: VerificationUiState
        get() = VerificationUiState()

    override fun onAction(action: VerificationAction) {
        when (action) {
            is VerificationAction.SelectDocType -> {
                _state.update { it.copy(selectedDocType = action.type) }
            }
            VerificationAction.CaptureId -> {
                viewModelScope.launch {
                    _state.update { it.copy(isSubmitting = true) }
                    delay(800)
                    _state.update { it.copy(isSubmitting = false, isFrontCaptured = true) }
                    emitEvent(VerificationEvent.NavigateToFaceScan)
                }
            }
            VerificationAction.StartFaceScan -> {
                viewModelScope.launch {
                    _state.update { it.copy(isFaceScanning = true) }
                    delay(1200)
                    _state.update { it.copy(isFaceScanning = false, isFaceScanComplete = true) }
                    emitEvent(VerificationEvent.NavigateToSuccess)
                }
            }
            VerificationAction.CompleteVerification -> {
                emitEventAsync(VerificationEvent.NavigateToMainApp)
            }
        }
    }
}
