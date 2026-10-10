package com.romeodev.safehomeapp.feature_home_presentation.events

sealed interface HomeAction {
    data class SelectTab(val index: Int) : HomeAction
}

sealed interface HomeEvent
