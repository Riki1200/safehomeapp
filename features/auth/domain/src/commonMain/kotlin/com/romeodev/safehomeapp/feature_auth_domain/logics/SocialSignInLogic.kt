package com.romeodev.safehomeapp.feature_auth_domain.logics

import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider
import com.romeodev.safehomeapp.feature_auth_domain.repositories.AuthRepository

class SocialSignInLogic(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(provider: SocialAuthProvider): AuthResult {
        return repository.signInWithSocial(provider)
    }
}
