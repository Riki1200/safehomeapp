package com.romeodev.safehomeapp.feature_verification_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.IdDocumentType
import com.romeodev.safehomeapp.feature_core_domain.models.VerificationStatus

data class VerificationUiState(
    val selectedDocType: IdDocumentType = IdDocumentType.INE,
    val isFrontCaptured: Boolean = false,
    val isFaceScanning: Boolean = false,
    val isFaceScanComplete: Boolean = false,
    val trustScore: Int = 98,
    val isSubmitting: Boolean = false,
    val status: VerificationStatus = VerificationStatus()
)
