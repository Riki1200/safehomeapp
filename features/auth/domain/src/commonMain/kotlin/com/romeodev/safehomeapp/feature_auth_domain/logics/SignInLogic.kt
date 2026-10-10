package com.romeodev.safehomeapp.feature_auth_domain.logics

import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.repositories.AuthRepository

class SignInLogic(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return repository.signInWithEmail(email.trim(), password)
    }
}
