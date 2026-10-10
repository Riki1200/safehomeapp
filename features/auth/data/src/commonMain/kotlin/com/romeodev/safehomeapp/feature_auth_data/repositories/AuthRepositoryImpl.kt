package com.romeodev.safehomeapp.feature_auth_data.repositories

import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.models.AuthUser
import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider
import com.romeodev.safehomeapp.feature_auth_domain.repositories.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepositoryImpl : AuthRepository {

    private val _currentUser = MutableStateFlow<AuthUser?>(null)

    override fun getCurrentUser(): Flow<AuthUser?> = _currentUser.asStateFlow()

    override suspend fun signInWithEmail(email: String, password: String): AuthResult {
        // Simulate network latency
        delay(600)
        if (password.length < 8) {
            return AuthResult.Error("Password must be at least 8 characters")
        }
        val user = AuthUser(
            id = "user_${email.hashCode()}",
            email = email,
            fullName = email.substringBefore("@").replaceFirstChar { it.uppercase() },
            isVerified = true,
        )
        _currentUser.value = user
        return AuthResult.Success(user)
    }

    override suspend fun signUpWithEmail(
        fullName: String,
        email: String,
        password: String
    ): AuthResult {
        // Simulate network latency
        delay(600)
        if (password.length < 8) {
            return AuthResult.Error("Password must be at least 8 characters")
        }
        val user = AuthUser(
            id = "user_${email.hashCode()}",
            email = email,
            fullName = fullName,
            isVerified = false,
        )
        _currentUser.value = user
        return AuthResult.Success(user)
    }

    override suspend fun signInWithSocial(provider: SocialAuthProvider): AuthResult {
        delay(800)
        val providerName = when (provider) {
            SocialAuthProvider.APPLE -> "Apple User"
            SocialAuthProvider.GOOGLE -> "Google User"
        }
        val user = AuthUser(
            id = "social_${provider.name.lowercase()}_123",
            email = "${provider.name.lowercase()}user@safehome.io",
            fullName = providerName,
            isVerified = true,
        )
        _currentUser.value = user
        return AuthResult.Success(user)
    }

    override suspend fun requestPasswordReset(email: String): Result<Unit> {
        delay(400)
        return Result.success(Unit)
    }

    override suspend fun signOut() {
        _currentUser.value = null
    }
}
