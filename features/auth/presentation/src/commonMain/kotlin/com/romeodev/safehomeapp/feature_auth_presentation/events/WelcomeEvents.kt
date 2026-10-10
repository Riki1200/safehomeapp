package com.romeodev.safehomeapp.feature_auth_presentation.events

sealed class WelcomeActions {
    data object OnCreateAccountClick : WelcomeActions()
    data object OnAlreadyHaveAccountClick : WelcomeActions()
    data object OnToggleLanguageClick : WelcomeActions()
}

sealed class WelcomeEvents {
    data object NavigateToSignUp : WelcomeEvents()
    data object NavigateToSignIn : WelcomeEvents()
    data object OpenLanguageSelector : WelcomeEvents()
}
