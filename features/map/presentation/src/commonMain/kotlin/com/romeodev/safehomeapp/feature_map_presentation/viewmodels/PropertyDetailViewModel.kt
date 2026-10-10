package com.romeodev.safehomeapp.feature_map_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_map_domain.logics.GetConditionReportLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetPropertyDetailLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetTitleValidationLogic
import com.romeodev.safehomeapp.feature_map_presentation.events.PropertyDetailAction
import com.romeodev.safehomeapp.feature_map_presentation.events.PropertyDetailEvent
import com.romeodev.safehomeapp.feature_map_presentation.states.PropertyDetailUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class PropertyDetailViewModel(
    private val getPropertyDetailLogic: GetPropertyDetailLogic,
    private val getConditionReportLogic: GetConditionReportLogic,
    private val getTitleValidationLogic: GetTitleValidationLogic
) : MviViewModel<PropertyDetailUiState, PropertyDetailAction, PropertyDetailEvent>() {

    override val initialState: PropertyDetailUiState
        get() = PropertyDetailUiState()

    override fun onAction(action: PropertyDetailAction) {
        when (action) {
            is PropertyDetailAction.LoadProperty -> {
                getPropertyDetailLogic(action.id).onEach { prop ->
                    _state.update { it.copy(property = prop) }
                }.launchIn(viewModelScope)

                getConditionReportLogic(action.id).onEach { rep ->
                    _state.update { it.copy(conditionReport = rep) }
                }.launchIn(viewModelScope)

                getTitleValidationLogic(action.id).onEach { title ->
                    _state.update { it.copy(titleValidation = title) }
                }.launchIn(viewModelScope)
            }
            PropertyDetailAction.ToggleFavorite -> {
                _state.update { it.copy(isFavorite = !it.isFavorite) }
            }
        }
    }
}
