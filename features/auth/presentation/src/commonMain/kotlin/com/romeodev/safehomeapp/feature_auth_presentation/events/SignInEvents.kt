package com.romeodev.safehomeapp.feature_auth_presentation.events

import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider

sealed class SignInActions {
    data class OnEmailChange(val email: String) : SignInActions()
    data class OnPasswordChange(val password: String) : SignInActions()
    data object OnSignInClick : SignInActions()
    data object OnAppleSignInClick : SignInActions()
    data object OnGoogleSignInClick : SignInActions()
    data object OnForgotPasswordClick : SignInActions()
    data object OnCreateAccountClick : SignInActions()
    data object OnBackClick : SignInActions()
}

sealed class SignInEvents {
    data object NavigateBack : SignInEvents()
    data object NavigateToSignUp : SignInEvents()
    data object SignInSuccess : SignInEvents()
    data class ShowSnackbar(val message: String) : SignInEvents()
}
