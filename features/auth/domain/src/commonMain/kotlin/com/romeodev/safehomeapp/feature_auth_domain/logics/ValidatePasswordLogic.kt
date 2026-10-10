package com.romeodev.safehomeapp.feature_auth_domain.logics

class ValidatePasswordLogic {
    operator fun invoke(password: String): String? {
        if (password.isEmpty()) {
            return "Password is required"
        }
        if (password.length < 8) {
            return "Password must be at least 8 characters"
        }
        return null
    }
}
