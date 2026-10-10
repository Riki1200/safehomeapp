package com.romeodev.safehomeapp.feature_auth_presentation.states

import kotlinx.serialization.Serializable

@Serializable
data class SignUpState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val acceptedTerms: Boolean = false,
    val fullNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val termsError: String? = null,
    val isLoading: Boolean = false,
    val isSocialLoading: Boolean = false,
    val generalError: String? = null,
) {
    val passwordCounter: String
        get() = "(${password.length.coerceAtMost(8)}/8)"
}
