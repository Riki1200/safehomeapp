package com.romeodev.safehomeapp.feature_auth_domain.repositories

import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.models.AuthUser
import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): AuthResult
    suspend fun signUpWithEmail(fullName: String, email: String, password: String): AuthResult
    suspend fun signInWithSocial(provider: SocialAuthProvider): AuthResult
    suspend fun requestPasswordReset(email: String): Result<Unit>
    fun getCurrentUser(): Flow<AuthUser?>
    suspend fun signOut()
}
