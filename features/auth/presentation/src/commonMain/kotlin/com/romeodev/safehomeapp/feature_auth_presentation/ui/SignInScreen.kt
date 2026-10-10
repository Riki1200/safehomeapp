package com.romeodev.safehomeapp.feature_auth_presentation.ui

import com.romeodev.safehomeapp.feature_auth_presentation.events.*
import com.romeodev.safehomeapp.feature_auth_presentation.states.*
import com.romeodev.safehomeapp.feature_auth_presentation.viewmodels.*
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.*

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_btn_create_account
import com.romeodev.safehomeapp.feature_resources.safehome_btn_sign_in
import com.romeodev.safehomeapp.feature_resources.safehome_email_label
import com.romeodev.safehomeapp.feature_resources.safehome_email_placeholder
import com.romeodev.safehomeapp.feature_resources.safehome_forgot_password
import com.romeodev.safehomeapp.feature_resources.safehome_new_to_safehome
import com.romeodev.safehomeapp.feature_resources.safehome_password_label
import com.romeodev.safehomeapp.feature_resources.safehome_password_placeholder
import com.romeodev.safehomeapp.feature_resources.safehome_signin_subtitle
import com.romeodev.safehomeapp.feature_resources.safehome_signin_title
import com.romeodev.safehomeapp.ui_utils.resources.toActualString
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onSignInSuccess: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    ObserveAsEvents(flow = viewModel.uiEvents) { event ->
        when (event) {
            SignInEvents.NavigateBack -> onNavigateBack()
            SignInEvents.NavigateToSignUp -> onNavigateToSignUp()
            SignInEvents.SignInSuccess -> onSignInSuccess()
            is SignInEvents.ShowSnackbar -> SnackbarController.sendMessage(event.message)
        }
    }

    SignInContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun SignInContent(
    state: SignInState,
    onAction: (SignInActions) -> Unit,
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
                onClick = { onAction(SignInActions.OnBackClick) }
            )

            // Title
            Text(
                text = Res.string.safehome_signin_title.toActualString(),
                color = Color.White,
                fontSize = 38.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = Res.string.safehome_signin_subtitle.toActualString(),
                color = Color(0xFF94A3B8),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Social Logins (Stacked)
            AppleSignInButton(
                modifier = Modifier.fillMaxWidth(),
                isCompact = false,
                onClick = { onAction(SignInActions.OnAppleSignInClick) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            GoogleSignInButton(
                modifier = Modifier.fillMaxWidth(),
                isCompact = false,
                onClick = { onAction(SignInActions.OnGoogleSignInClick) }
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Divider: OR WITH EMAIL
            AuthDivider()

            Spacer(modifier = Modifier.height(26.dp))

            // Email Field
            AuthTextField(
                label = Res.string.safehome_email_label.toActualString(),
                value = state.email,
                onValueChange = { onAction(SignInActions.OnEmailChange(it)) },
                placeholder = Res.string.safehome_email_placeholder.toActualString(),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                errorMessage = state.emailError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Password Field
            AuthPasswordTextField(
                label = Res.string.safehome_password_label.toActualString(),
                value = state.password,
                onValueChange = { onAction(SignInActions.OnPasswordChange(it)) },
                placeholder = Res.string.safehome_password_placeholder.toActualString(),
                imeAction = ImeAction.Done,
                onImeAction = { onAction(SignInActions.OnSignInClick) },
                errorMessage = state.passwordError
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Forgot Password Link
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = Res.string.safehome_forgot_password.toActualString(),
                    color = Color(0xFF3E6DFF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { onAction(SignInActions.OnForgotPasswordClick) }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Sign In Button
            AuthPrimaryButton(
                text = Res.string.safehome_btn_sign_in.toActualString(),
                isLoading = state.isLoading,
                onClick = { onAction(SignInActions.OnSignInClick) }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Bottom Link: New to Safe Home? Create account
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Res.string.safehome_new_to_safehome.toActualString(),
                    color = Color(0xFF94A3B8),
                    fontSize = 14.sp
                )
                Text(
                    text = Res.string.safehome_btn_create_account.toActualString(),
                    color = Color(0xFF3E6DFF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onAction(SignInActions.OnCreateAccountClick) }
                )
            }
        }
    }
}
