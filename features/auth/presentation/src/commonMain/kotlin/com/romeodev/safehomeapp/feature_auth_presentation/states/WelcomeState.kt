package com.romeodev.safehomeapp.feature_auth_presentation.states

import kotlinx.serialization.Serializable

@Serializable
data class WelcomeState(
    val currentLanguage: String = "English",
)
