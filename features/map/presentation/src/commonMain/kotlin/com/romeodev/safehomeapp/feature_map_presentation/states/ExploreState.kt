package com.romeodev.safehomeapp.feature_map_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.ZoneSafety

data class ExploreUiState(
    val properties: List<Property> = emptyList(),
    val filteredProperties: List<Property> = emptyList(),
    val zones: List<ZoneSafety> = emptyList(),
    val selectedZone: String = "Roma Norte",
    val searchQuery: String = "",
    val selectedFilter: String = "All types",
    val isMapView: Boolean = true,
    val selectedProperty: Property? = null
)
