package com.romeodev.safehomeapp.feature_auth_presentation.ui

import com.romeodev.safehomeapp.feature_auth_presentation.events.*
import com.romeodev.safehomeapp.feature_auth_presentation.states.*
import com.romeodev.safehomeapp.feature_auth_presentation.viewmodels.*
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.*

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.core.events.controllers.SnackbarController
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AppleSignInButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthDivider
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthPasswordTextField
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthPrimaryButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthTextField
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.CircularBackButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.GoogleSignInButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.TermsCheckbox
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_btn_continue_verification
import com.romeodev.safehomeapp.feature_resources.safehome_email_label
import com.romeodev.safehomeapp.feature_resources.safehome_email_placeholder
import com.romeodev.safehomeapp.feature_resources.safehome_fullname_label
import com.romeodev.safehomeapp.feature_resources.safehome_fullname_placeholder
import com.romeodev.safehomeapp.feature_resources.safehome_password_label
import com.romeodev.safehomeapp.feature_resources.safehome_password_signup_placeholder
import com.romeodev.safehomeapp.feature_resources.safehome_signup_subtitle
import com.romeodev.safehomeapp.feature_resources.safehome_signup_title
import com.romeodev.safehomeapp.ui_utils.resources.toActualString
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onVerificationSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    ObserveAsEvents(flow = viewModel.uiEvents) { event ->
        when (event) {
            SignUpEvents.NavigateBack -> onNavigateBack()
            SignUpEvents.VerificationStarted -> onVerificationSuccess()
            is SignUpEvents.ShowSnackbar -> SnackbarController.sendMessage(event.message)
        }
    }

    SignUpContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SignUpContent(
    state: SignUpState,
    onAction: (SignUpActions) -> Unit,
) {
    val bg = Color(0xFF0C1017)
    val insets = WindowInsets.safeDrawing.asPaddingValues()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(insets)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            // Circular Back Button
            CircularBackButton(
                modifier = Modifier.padding(top = 12.dp, bottom = 24.dp),
                onClick = { onAction(SignUpActions.OnBackClick) }
            )

            // Title
            Text(
                text = Res.string.safehome_signup_title.toActualString(),
                color = Color.White,
                fontSize = 38.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = Res.string.safehome_signup_subtitle.toActualString(),
                color = Color(0xFF94A3B8),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Social Buttons (Side by Side Row)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppleSignInButton(
                    modifier = Modifier.weight(1f),
                    isCompact = true,
                    onClick = { onAction(SignUpActions.OnAppleSignUpClick) }
                )
                GoogleSignInButton(
                    modifier = Modifier.weight(1f),
                    isCompact = true,
                    onClick = { onAction(SignUpActions.OnGoogleSignUpClick) }
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Divider: OR WITH EMAIL
            AuthDivider()

            Spacer(modifier = Modifier.height(26.dp))

            // Full Name Field
            AuthTextField(
                label = Res.string.safehome_fullname_label.toActualString(),
                value = state.fullName,
                onValueChange = { onAction(SignUpActions.OnFullNameChange(it)) },
                placeholder = Res.string.safehome_fullname_placeholder.toActualString(),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                errorMessage = state.fullNameError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Email Field
            AuthTextField(
                label = Res.string.safehome_email_label.toActualString(),
                value = state.email,
                onValueChange = { onAction(SignUpActions.OnEmailChange(it)) },
                placeholder = Res.string.safehome_email_placeholder.toActualString(),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                errorMessage = state.emailError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Password Field with dynamic counter
            AuthPasswordTextField(
                label = Res.string.safehome_password_label.toActualString(),
                value = state.password,
                onValueChange = { onAction(SignUpActions.OnPasswordChange(it)) },
                placeholder = Res.string.safehome_password_signup_placeholder.toActualString(),
                imeAction = ImeAction.Done,
                onImeAction = { onAction(SignUpActions.OnContinueClick) },
                helperText = "At least 8 characters",
                counterText = state.passwordCounter,
                errorMessage = state.passwordError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Terms and Privacy Checkbox
            TermsCheckbox(
                checked = state.acceptedTerms,
                onCheckedChange = { onAction(SignUpActions.OnAcceptedTermsToggle(it)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Continue to Verification Button
            AuthPrimaryButton(
                text = Res.string.safehome_btn_continue_verification.toActualString(),
                isLoading = state.isLoading,
                onClick = { onAction(SignUpActions.OnContinueClick) }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
