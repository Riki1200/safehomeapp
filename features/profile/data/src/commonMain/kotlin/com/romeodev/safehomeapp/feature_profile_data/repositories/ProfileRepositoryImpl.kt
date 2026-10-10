package com.romeodev.safehomeapp.feature_profile_data.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.LanguageOption
import com.romeodev.safehomeapp.feature_profile_domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileRepositoryImpl : ProfileRepository {

    private val isOwnerModeFlow = MutableStateFlow(false)
    private val selectedLanguageFlow = MutableStateFlow("English")

    private val languagesList = listOf(
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

    override fun isOwnerMode(): Flow<Boolean> = isOwnerModeFlow.asStateFlow()

    override suspend fun setOwnerMode(enabled: Boolean) {
        isOwnerModeFlow.value = enabled
    }

    override fun getSelectedLanguage(): Flow<String> = selectedLanguageFlow.asStateFlow()

    override suspend fun setLanguage(language: String) {
        selectedLanguageFlow.value = language
    }

    override fun getAvailableLanguages(): List<LanguageOption> = languagesList
}
