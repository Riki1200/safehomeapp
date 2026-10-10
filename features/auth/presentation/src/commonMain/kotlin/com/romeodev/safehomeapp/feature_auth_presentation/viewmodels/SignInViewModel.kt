package com.romeodev.safehomeapp.feature_auth_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_analytics_domain.EventsTracker
import com.romeodev.safehomeapp.feature_auth_domain.logics.AuthLogics
import com.romeodev.safehomeapp.feature_auth_domain.models.AuthResult
import com.romeodev.safehomeapp.feature_auth_domain.models.SocialAuthProvider
import com.romeodev.safehomeapp.feature_auth_presentation.events.SignInActions
import com.romeodev.safehomeapp.feature_auth_presentation.events.SignInEvents
import com.romeodev.safehomeapp.feature_auth_presentation.states.SignInState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authLogics: AuthLogics,
    private val eventsTracker: EventsTracker,
) : MviViewModel<SignInState, SignInActions, SignInEvents>() {

    override val initialState: SignInState
        get() = SignInState()

    override fun onAction(action: SignInActions) {
        when (action) {
            is SignInActions.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = action.email,
                        emailError = null,
                        generalError = null
                    )
                }
            }

            is SignInActions.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = action.password,
                        passwordError = null,
                        generalError = null
                    )
                }
            }

            SignInActions.OnSignInClick -> signIn()
            SignInActions.OnAppleSignInClick -> socialSignIn(SocialAuthProvider.APPLE)
            SignInActions.OnGoogleSignInClick -> socialSignIn(SocialAuthProvider.GOOGLE)
            SignInActions.OnForgotPasswordClick -> forgotPassword()
            SignInActions.OnCreateAccountClick -> emitEventAsync(SignInEvents.NavigateToSignUp)
            SignInActions.OnBackClick -> emitEventAsync(SignInEvents.NavigateBack)
        }
    }

    private fun signIn() {
        val email = _state.value.email
        val password = _state.value.password

        val emailError = authLogics.validateEmail(email)
        val passwordError = authLogics.validatePassword(password)

        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, generalError = null) }
            when (val result = authLogics.signIn(email, password)) {
                is AuthResult.Success -> {
                    _state.update { it.copy(isLoading = false) }
                    emitEvent(SignInEvents.SignInSuccess)
                }

                is AuthResult.Error -> {
                    _state.update { it.copy(isLoading = false, generalError = result.message) }
                    emitEvent(SignInEvents.ShowSnackbar(result.message))
                }
            }
        }
    }

    private fun socialSignIn(provider: SocialAuthProvider) {
        viewModelScope.launch {
            _state.update { it.copy(isSocialLoading = true, generalError = null) }
            when (val result = authLogics.socialSignIn(provider)) {
                is AuthResult.Success -> {
                    _state.update { it.copy(isSocialLoading = false) }
                    emitEvent(SignInEvents.SignInSuccess)
                }

                is AuthResult.Error -> {
                    _state.update { it.copy(isSocialLoading = false, generalError = result.message) }
                    emitEvent(SignInEvents.ShowSnackbar(result.message))
                }
            }
        }
    }

    private fun forgotPassword() {
        val email = _state.value.email
        if (email.isBlank()) {
            _state.update { it.copy(emailError = "Enter your email to reset password") }
            return
        }
        emitEventAsync(SignInEvents.ShowSnackbar("Password reset instructions sent to $email"))
    }
}
