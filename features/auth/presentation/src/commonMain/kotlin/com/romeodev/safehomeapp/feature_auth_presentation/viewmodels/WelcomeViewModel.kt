package com.romeodev.safehomeapp.feature_auth_presentation.viewmodels

import com.romeodev.safehomeapp.feature_auth_presentation.events.WelcomeActions
import com.romeodev.safehomeapp.feature_auth_presentation.events.WelcomeEvents
import com.romeodev.safehomeapp.feature_auth_presentation.states.WelcomeState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel

class WelcomeViewModel : MviViewModel<WelcomeState, WelcomeActions, WelcomeEvents>() {

    override val initialState: WelcomeState
        get() = WelcomeState()

    override fun onAction(action: WelcomeActions) {
        when (action) {
            WelcomeActions.OnCreateAccountClick -> {
                emitEventAsync(WelcomeEvents.NavigateToSignUp)
            }
            WelcomeActions.OnAlreadyHaveAccountClick -> {
                emitEventAsync(WelcomeEvents.NavigateToSignIn)
            }
            WelcomeActions.OnToggleLanguageClick -> {
                emitEventAsync(WelcomeEvents.OpenLanguageSelector)
            }
        }
    }
}
