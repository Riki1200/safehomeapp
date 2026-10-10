package com.romeodev.safehomeapp.feature_auth_presentation.states

import kotlinx.serialization.Serializable

@Serializable
data class SignInState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isSocialLoading: Boolean = false,
    val generalError: String? = null,
)
