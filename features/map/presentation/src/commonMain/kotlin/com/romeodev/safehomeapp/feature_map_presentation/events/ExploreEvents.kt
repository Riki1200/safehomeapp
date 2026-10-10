package com.romeodev.safehomeapp.feature_map_presentation.events

import com.romeodev.safehomeapp.feature_core_domain.models.Property

sealed interface ExploreAction {
    data class SearchQueryChanged(val query: String) : ExploreAction
    data class SelectZone(val zone: String) : ExploreAction
    data class SelectFilter(val filter: String) : ExploreAction
    data class ToggleViewMode(val isMapView: Boolean) : ExploreAction
    data class SelectProperty(val property: Property?) : ExploreAction
}

sealed interface ExploreEvent {
    data class NavigateToDetail(val propertyId: String) : ExploreEvent
}
