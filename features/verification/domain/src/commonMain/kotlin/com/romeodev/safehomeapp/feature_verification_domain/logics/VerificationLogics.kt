package com.romeodev.safehomeapp.feature_verification_domain.logics

import com.romeodev.safehomeapp.feature_core_domain.models.IdDocumentType
import com.romeodev.safehomeapp.feature_core_domain.models.VerificationStatus
import com.romeodev.safehomeapp.feature_verification_domain.repositories.VerificationRepository
import kotlinx.coroutines.flow.Flow

class GetVerificationStatusLogic(
    private val repository: VerificationRepository
) {
    operator fun invoke(): Flow<VerificationStatus> = repository.getVerificationStatus()
}

class UpdateVerificationProgressLogic(
    private val repository: VerificationRepository
) {
    suspend fun selectDocumentType(type: IdDocumentType) = repository.selectDocumentType(type)
    suspend fun captureIdFront() = repository.captureIdFront()
    suspend fun completeFaceScan() = repository.completeFaceScan()
    suspend fun reset() = repository.resetVerification()
}
