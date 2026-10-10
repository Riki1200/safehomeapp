package com.romeodev.safehomeapp.feature_auth_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_analytics_domain.EventsTracker
import com.romeodev.safehomeapp.feature_auth_domain.logics.AuthLogics
import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider
import com.romeodev.safehomeapp.feature_auth_presentation.events.SignUpActions
import com.romeodev.safehomeapp.feature_auth_presentation.events.SignUpEvents
import com.romeodev.safehomeapp.feature_auth_presentation.states.SignUpState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authLogics: AuthLogics,
    private val eventsTracker: EventsTracker,
) : MviViewModel<SignUpState, SignUpActions, SignUpEvents>() {

    override val initialState: SignUpState
        get() = SignUpState()

    override fun onAction(action: SignUpActions) {
        when (action) {
            is SignUpActions.OnFullNameChange -> {
                _state.update {
                    it.copy(
                        fullName = action.name,
                        fullNameError = null,
                        generalError = null
                    )
                }
            }

            is SignUpActions.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = action.email,
                        emailError = null,
                        generalError = null
                    )
                }
            }

            is SignUpActions.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = action.password,
                        passwordError = null,
                        generalError = null
                    )
                }
            }

            is SignUpActions.OnAcceptedTermsToggle -> {
                _state.update {
                    it.copy(
                        acceptedTerms = action.accepted,
                        termsError = null
                    )
                }
            }

            SignUpActions.OnContinueClick -> continueToVerification()
            SignUpActions.OnAppleSignUpClick -> socialSignUp(SocialAuthProvider.APPLE)
            SignUpActions.OnGoogleSignUpClick -> socialSignUp(SocialAuthProvider.GOOGLE)
            SignUpActions.OnBackClick -> emitEventAsync(SignUpEvents.NavigateBack)
        }
    }

    private fun continueToVerification() {
        val fullName = _state.value.fullName
        val email = _state.value.email
        val password = _state.value.password
        val acceptedTerms = _state.value.acceptedTerms

        val fullNameError = if (fullName.trim().isEmpty()) "Full name is required" else null
        val emailError = authLogics.validateEmail(email)
        val passwordError = authLogics.validatePassword(password)
        val termsError = if (!acceptedTerms) "You must accept the terms and privacy notice" else null

        if (fullNameError != null || emailError != null || passwordError != null || termsError != null) {
            _state.update {
                it.copy(
                    fullNameError = fullNameError,
                    emailError = emailError,
                    passwordError = passwordError,
                    termsError = termsError
                )
            }
            if (termsError != null && fullNameError == null && emailError == null && passwordError == null) {
                emitEventAsync(SignUpEvents.ShowSnackbar(termsError))
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, generalError = null) }
            when (val result = authLogics.signUp(fullName, email, password)) {
                is AuthResult.Success -> {
                    _state.update { it.copy(isLoading = false) }
                    emitEvent(SignUpEvents.VerificationStarted)
                }

                is AuthResult.Error -> {
                    _state.update { it.copy(isLoading = false, generalError = result.message) }
                    emitEvent(SignUpEvents.ShowSnackbar(result.message))
                }
            }
        }
    }

    private fun socialSignUp(provider: SocialAuthProvider) {
        viewModelScope.launch {
            _state.update { it.copy(isSocialLoading = true, generalError = null) }
            when (val result = authLogics.socialSignIn(provider)) {
                is AuthResult.Success -> {
                    _state.update { it.copy(isSocialLoading = false) }
                    emitEvent(SignUpEvents.VerificationStarted)
                }

                is AuthResult.Error -> {
                    _state.update { it.copy(isSocialLoading = false, generalError = result.message) }
                    emitEvent(SignUpEvents.ShowSnackbar(result.message))
                }
            }
        }
    }
}
