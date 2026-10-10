package com.romeodev.safehomeapp.feature_verification_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.IdDocumentType
import com.romeodev.safehomeapp.feature_core_domain.models.VerificationStatus
import kotlinx.coroutines.flow.Flow

interface VerificationRepository {
    fun getVerificationStatus(): Flow<VerificationStatus>
    suspend fun selectDocumentType(type: IdDocumentType)
    suspend fun captureIdFront()
    suspend fun completeFaceScan()
    suspend fun resetVerification()
}
