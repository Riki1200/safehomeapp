package com.romeodev.safehomeapp.feature_auth_domain.logics

class ValidateEmailLogic {
    operator fun invoke(email: String): String? {
        val trimmed = email.trim()
        if (trimmed.isEmpty()) {
            return "Email is required"
        }
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        if (!trimmed.matches(emailRegex)) {
            return "Please enter a valid email address"
        }
        return null
    }
}
