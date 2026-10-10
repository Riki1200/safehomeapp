package com.romeodev.safehomeapp.feature_profile_domain.logics

import com.romeodev.safehomeapp.feature_core_domain.models.LanguageOption
import com.romeodev.safehomeapp.feature_profile_domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetProfilePreferencesLogic(
    private val repository: ProfileRepository
) {
    fun isOwnerMode(): Flow<Boolean> = repository.isOwnerMode()
    fun getSelectedLanguage(): Flow<String> = repository.getSelectedLanguage()
    fun getAvailableLanguages(): List<LanguageOption> = repository.getAvailableLanguages()
}

class UpdateProfilePreferencesLogic(
    private val repository: ProfileRepository
) {
    suspend fun setOwnerMode(enabled: Boolean) = repository.setOwnerMode(enabled)
    suspend fun setLanguage(language: String) = repository.setLanguage(language)
}
