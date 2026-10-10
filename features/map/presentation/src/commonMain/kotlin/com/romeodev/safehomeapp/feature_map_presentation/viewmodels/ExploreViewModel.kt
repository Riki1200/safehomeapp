package com.romeodev.safehomeapp.feature_map_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_map_domain.logics.GetPropertiesLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetZonesLogic
import com.romeodev.safehomeapp.feature_map_presentation.events.ExploreAction
import com.romeodev.safehomeapp.feature_map_presentation.events.ExploreEvent
import com.romeodev.safehomeapp.feature_map_presentation.states.ExploreUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ExploreViewModel(
    private val getPropertiesLogic: GetPropertiesLogic,
    private val getZonesLogic: GetZonesLogic
) : MviViewModel<ExploreUiState, ExploreAction, ExploreEvent>() {

    override val initialState: ExploreUiState
        get() = ExploreUiState()

    init {
        loadData()
    }

    private fun loadData() {
        getZonesLogic().onEach { zones ->
            _state.update { it.copy(zones = zones) }
        }.launchIn(viewModelScope)

        getPropertiesLogic().onEach { properties ->
            _state.update {
                it.copy(
                    properties = properties,
                    filteredProperties = properties,
                    selectedProperty = properties.firstOrNull()
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun onAction(action: ExploreAction) {
        when (action) {
            is ExploreAction.SearchQueryChanged -> {
                _state.update { state ->
                    val filtered = state.properties.filter {
                        it.title.contains(action.query, ignoreCase = true) ||
                                it.zone.contains(action.query, ignoreCase = true)
                    }
                    state.copy(searchQuery = action.query, filteredProperties = filtered)
                }
            }
            is ExploreAction.SelectZone -> {
                _state.update { state ->
                    val filtered = if (action.zone.isEmpty()) state.properties else {
                        state.properties.filter { it.zone.equals(action.zone, ignoreCase = true) }
                    }
                    state.copy(
                        selectedZone = action.zone,
                        filteredProperties = filtered,
                        selectedProperty = filtered.firstOrNull() ?: state.properties.firstOrNull()
                    )
                }
            }
            is ExploreAction.SelectFilter -> {
                _state.update { it.copy(selectedFilter = action.filter) }
            }
            is ExploreAction.ToggleViewMode -> {
                _state.update { it.copy(isMapView = action.isMapView) }
            }
            is ExploreAction.SelectProperty -> {
                _state.update { it.copy(selectedProperty = action.property) }
            }
        }
    }
}
