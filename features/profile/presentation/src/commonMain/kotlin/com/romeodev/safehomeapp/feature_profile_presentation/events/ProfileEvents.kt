package com.romeodev.safehomeapp.feature_profile_presentation.events

sealed interface ProfileAction {
    data class ToggleOwnerMode(val isOwner: Boolean) : ProfileAction
    data class ToggleDarkMode(val isDark: Boolean) : ProfileAction
    data class SelectLanguage(val languageName: String) : ProfileAction
    data object SignOut : ProfileAction
}

sealed interface ProfileEvent {
    data object SignedOut : ProfileEvent
}
