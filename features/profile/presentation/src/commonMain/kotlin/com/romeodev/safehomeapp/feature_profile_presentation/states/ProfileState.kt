package com.romeodev.safehomeapp.feature_profile_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.LanguageOption

data class ProfileUiState(
    val userName: String = "Andrea Salinas",
    val isVerified: Boolean = true,
    val trustScore: Int = 98,
    val isOwnerMode: Boolean = false,
    val isDarkMode: Boolean = true,
    val selectedLanguage: String = "English",
    val languages: List<LanguageOption> = listOf(
        LanguageOption("en", "English", "English"),
        LanguageOption("es", "Spanish", "Español"),
        LanguageOption("fr", "French", "Français"),
        LanguageOption("de", "German", "Deutsch"),
        LanguageOption("it", "Italian", "Italiano"),
        LanguageOption("pt", "Portuguese", "Português"),
        LanguageOption("pl", "Polish", "Polski"),
        LanguageOption("ru", "Russian", "Русский"),
        LanguageOption("tr", "Turkish", "Türkçe"),
        LanguageOption("ar", "Arabic", "العربية"),
        LanguageOption("ur", "Urdu", "اردو"),
        LanguageOption("hi", "Hindi", "हिन्दी")
    )
)
