package com.romeodev.safehomeapp.feature_profile_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.LanguageOption
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun isOwnerMode(): Flow<Boolean>
    suspend fun setOwnerMode(enabled: Boolean)
    fun getSelectedLanguage(): Flow<String>
    suspend fun setLanguage(language: String)
    fun getAvailableLanguages(): List<LanguageOption>
}
