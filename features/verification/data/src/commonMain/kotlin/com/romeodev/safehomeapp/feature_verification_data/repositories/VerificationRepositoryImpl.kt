package com.romeodev.safehomeapp.feature_verification_data.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.IdDocumentType
import com.romeodev.safehomeapp.feature_core_domain.models.VerificationStatus
import com.romeodev.safehomeapp.feature_verification_domain.repositories.VerificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VerificationRepositoryImpl : VerificationRepository {
    private val _status = MutableStateFlow(VerificationStatus())

    override fun getVerificationStatus(): Flow<VerificationStatus> = _status.asStateFlow()

    override suspend fun selectDocumentType(type: IdDocumentType) {
        _status.update { it.copy(idDocumentType = type) }
    }

    override suspend fun captureIdFront() {
        _status.update { it.copy(idFrontCaptured = true) }
    }

    override suspend fun completeFaceScan() {
        _status.update {
            it.copy(
                faceScanCompleted = true,
                isVerified = true,
                trustScore = 98
            )
        }
    }

    override suspend fun resetVerification() {
        _status.value = VerificationStatus()
    }
}
