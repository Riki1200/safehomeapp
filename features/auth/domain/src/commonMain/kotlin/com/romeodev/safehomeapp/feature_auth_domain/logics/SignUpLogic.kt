package com.romeodev.safehomeapp.feature_auth_domain.logics

import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.repositories.AuthRepository

class SignUpLogic(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(fullName: String, email: String, password: String): AuthResult {
        return repository.signUpWithEmail(fullName.trim(), email.trim(), password)
    }
}
