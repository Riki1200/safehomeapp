package com.romeodev.safehomeapp.feature_assistant_presentation.ui

import com.romeodev.safehomeapp.feature_assistant_presentation.events.*
import com.romeodev.safehomeapp.feature_assistant_presentation.states.*
import com.romeodev.safehomeapp.feature_assistant_presentation.models.*
import com.romeodev.safehomeapp.feature_assistant_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_assistant_presentation.events.AssistantAction
import com.romeodev.safehomeapp.feature_assistant_presentation.models.AssistantMessage
import com.romeodev.safehomeapp.feature_assistant_presentation.viewmodels.AssistantViewModel
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors

@Composable
fun AssistantScreen(
    viewModel: AssistantViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1E2842)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SafeHomeColors.PrimaryBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "SafeHome AI Assistant",
                            fontSize = 17.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Legal & Real Estate Guardian",
                            fontSize = 11.sp,
                            color = SafeHomeColors.TextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Verified Document status pills
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.verifiedDocs.forEach { doc ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SafeHomeColors.VerifiedGreenBg)
                                .border(1.dp, SafeHomeColors.VerifiedGreenBorder, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = doc,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.VerifiedGreen
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Prompt suggestions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.suggestedPrompts.forEach { prompt ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(SafeHomeColors.DarkCard)
                                .border(1.dp, SafeHomeColors.CardBorderLight, RoundedCornerShape(14.dp))
                                .clickable { viewModel.onAction(AssistantAction.SelectPrompt(prompt)) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = prompt,
                                fontSize = 11.sp,
                                color = SafeHomeColors.TextSecondary
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(SafeHomeColors.DarkCard)
                            .border(1.dp, SafeHomeColors.CardBorderLight, RoundedCornerShape(20.dp))
                    ) {
                        TextField(
                            value = state.inputText,
                            onValueChange = { viewModel.onAction(AssistantAction.UpdateInput(it)) },
                            placeholder = {
                                Text(
                                    text = "Ask anything about leases, deeds, safety...",
                                    color = SafeHomeColors.TextMuted,
                                    fontSize = 13.sp
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { viewModel.onAction(AssistantAction.SendQuery) },
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(SafeHomeColors.PrimaryBlue)
                    ) {
                        if (state.isThinking) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(state.messages) { msg ->
                AiMessageBubble(msg)
            }
        }
    }
}

@Composable
private fun AiMessageBubble(message: AssistantMessage) {
    val isUser = message.isUser
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 4.dp,
                        bottomEnd = if (isUser) 4.dp else 16.dp
                    )
                )
                .background(if (isUser) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                .border(
                    1.dp,
                    if (isUser) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 4.dp,
                        bottomEnd = if (isUser) 4.dp else 16.dp
                    )
                )
                .padding(14.dp)
                .fillMaxWidth(0.85f)
        ) {
            Column {
                if (!isUser) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = SafeHomeColors.PrimaryBlue,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SafeHome Legal AI",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SafeHomeColors.PrimaryBlue
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                }

                Text(
                    text = message.text,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = message.timestamp,
                    fontSize = 10.sp,
                    color = if (isUser) Color.White.copy(alpha = 0.7f) else SafeHomeColors.TextMuted,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
