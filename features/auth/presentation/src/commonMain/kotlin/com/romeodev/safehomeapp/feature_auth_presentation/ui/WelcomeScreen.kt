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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthPrimaryButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.AuthSecondaryButton
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.BrandIcon
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.FeatureHighlightCard
import com.romeodev.safehomeapp.feature_auth_presentation.ui.components.LanguagePillBadge
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_app_name
import com.romeodev.safehomeapp.feature_resources.safehome_btn_already_have_account
import com.romeodev.safehomeapp.feature_resources.safehome_btn_create_account
import com.romeodev.safehomeapp.feature_resources.safehome_welcome_subtitle
import com.romeodev.safehomeapp.ui_utils.resources.toActualString
import com.romeodev.safehomeapp.ui_utils.side_effects.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = koinViewModel(),
    onNavigateToSignIn: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onOpenLanguagePicker: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    ObserveAsEvents(flow = viewModel.uiEvents) { event ->
        when (event) {
            WelcomeEvents.NavigateToSignUp -> onNavigateToSignUp()
            WelcomeEvents.NavigateToSignIn -> onNavigateToSignIn()
            WelcomeEvents.OpenLanguageSelector -> onOpenLanguagePicker()
        }
    }

    WelcomeContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun WelcomeContent(
    state: WelcomeState,
    onAction: (WelcomeActions) -> Unit,
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
            // Language selector in top-right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 28.dp),
                horizontalArrangement = Arrangement.End
            ) {
                LanguagePillBadge(
                    languageText = state.currentLanguage,
                    onClick = { onAction(WelcomeActions.OnToggleLanguageClick) }
                )
            }

            // App Logo
            BrandIcon(size = 64.dp)

            Spacer(modifier = Modifier.height(28.dp))

            // Title in elegant Serif font
            Text(
                text = Res.string.safehome_app_name.toActualString(),
                color = Color.White,
                fontSize = 42.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                letterSpacing = (-0.5).sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Subtitle
            Text(
                text = Res.string.safehome_welcome_subtitle.toActualString(),
                color = Color(0xFF94A3B8),
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Feature Highlights
            FeatureHighlightCard()

            Spacer(modifier = Modifier.height(36.dp))
            Spacer(modifier = Modifier.weight(1f, fill = false))

            // Action Buttons
            AuthPrimaryButton(
                text = Res.string.safehome_btn_create_account.toActualString(),
                onClick = { onAction(WelcomeActions.OnCreateAccountClick) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            AuthSecondaryButton(
                text = Res.string.safehome_btn_already_have_account.toActualString(),
                onClick = { onAction(WelcomeActions.OnAlreadyHaveAccountClick) }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
