package com.romeodev.safehomeapp.feature_auth_presentation.events

sealed class SignUpActions {
    data class OnFullNameChange(val name: String) : SignUpActions()
    data class OnEmailChange(val email: String) : SignUpActions()
    data class OnPasswordChange(val password: String) : SignUpActions()
    data class OnAcceptedTermsToggle(val accepted: Boolean) : SignUpActions()
    data object OnContinueClick : SignUpActions()
    data object OnAppleSignUpClick : SignUpActions()
    data object OnGoogleSignUpClick : SignUpActions()
    data object OnBackClick : SignUpActions()
}

sealed class SignUpEvents {
    data object NavigateBack : SignUpEvents()
    data object VerificationStarted : SignUpEvents()
    data class ShowSnackbar(val message: String) : SignUpEvents()
}
