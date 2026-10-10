package com.romeodev.safehomeapp.feature_auth_domain.models

sealed interface AuthResult {
    data class Success(val user: AuthUser) : AuthResult
    data class Error(val message: String) : AuthResult
}
