package com.romeodev.safehomeapp.feature_verification_presentation.events

import com.romeodev.safehomeapp.feature_core_domain.models.IdDocumentType

sealed interface VerificationAction {
    data class SelectDocType(val type: IdDocumentType) : VerificationAction
    data object CaptureId : VerificationAction
    data object StartFaceScan : VerificationAction
    data object CompleteVerification : VerificationAction
}

sealed interface VerificationEvent {
    data object NavigateToIdCapture : VerificationEvent
    data object NavigateToFaceScan : VerificationEvent
    data object NavigateToSuccess : VerificationEvent
    data object NavigateToMainApp : VerificationEvent
    data class ShowToast(val message: String) : VerificationEvent
}
