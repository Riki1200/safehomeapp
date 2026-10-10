package com.romeodev.safehomeapp.feature_auth_domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthUser(
    val id: String,
    val email: String,
    val fullName: String,
    val isVerified: Boolean = false,
    val photoUrl: String? = null,
)
