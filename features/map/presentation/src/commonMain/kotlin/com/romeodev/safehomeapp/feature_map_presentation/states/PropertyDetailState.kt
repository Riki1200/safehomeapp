package com.romeodev.safehomeapp.feature_map_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.TitleValidation

data class PropertyDetailUiState(
    val property: Property? = null,
    val conditionReport: ConditionReport? = null,
    val titleValidation: TitleValidation? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false
)
