package com.romeodev.safehomeapp.feature_map_presentation.events

sealed interface PropertyDetailAction {
    data class LoadProperty(val id: String) : PropertyDetailAction
    data object ToggleFavorite : PropertyDetailAction
}

sealed interface PropertyDetailEvent {
    data class NavigateToConditionReport(val propertyId: String) : PropertyDetailEvent
    data class NavigateToTitleValidation(val propertyId: String) : PropertyDetailEvent
    data class NavigateToBookViewing(val propertyId: String) : PropertyDetailEvent
    data class NavigateToChat(val chatId: String) : PropertyDetailEvent
}
