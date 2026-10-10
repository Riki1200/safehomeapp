package com.romeodev.safehomeapp.feature_profile_presentation.viewmodels

import com.romeodev.safehomeapp.feature_profile_presentation.events.ProfileAction
import com.romeodev.safehomeapp.feature_profile_presentation.events.ProfileEvent
import com.romeodev.safehomeapp.feature_profile_presentation.states.ProfileUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.update

class ProfileViewModel : MviViewModel<ProfileUiState, ProfileAction, ProfileEvent>() {

    override val initialState: ProfileUiState
        get() = ProfileUiState()

    override fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.ToggleOwnerMode -> _state.update { it.copy(isOwnerMode = action.isOwner) }
            is ProfileAction.ToggleDarkMode -> _state.update { it.copy(isDarkMode = action.isDark) }
            is ProfileAction.SelectLanguage -> _state.update { it.copy(selectedLanguage = action.languageName) }
            ProfileAction.SignOut -> emitEventAsync(ProfileEvent.SignedOut)
        }
    }
}
