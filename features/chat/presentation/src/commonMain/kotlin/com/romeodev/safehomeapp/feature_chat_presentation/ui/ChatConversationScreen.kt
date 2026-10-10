package com.romeodev.safehomeapp.feature_chat_presentation.ui

import com.romeodev.safehomeapp.feature_chat_presentation.events.*
import com.romeodev.safehomeapp.feature_chat_presentation.states.*
import com.romeodev.safehomeapp.feature_chat_presentation.models.*
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_core_domain.models.ChatMessage
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatConversationAction
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ChatConversationViewModel

@Composable
fun ChatConversationScreen(
    chatId: String,
    viewModel: ChatConversationViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToReportScam: (String) -> Unit
) {
    LaunchedEffect(chatId) {
        viewModel.onAction(ChatConversationAction.LoadChat(chatId))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val chat = state.chat

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(SafeHomeColors.DarkCard)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = chat?.participantName ?: "Andrea Salinas",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "⏱ 27:52:20 remaining",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SafeHomeColors.WarningAmber
                            )
                        }
                    }

                    IconButton(
                        onClick = { onNavigateToReportScam(chatId) },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SafeHomeColors.DangerRedBg)
                            .border(1.dp, Color(0xFF6B1D22), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Flag,
                            contentDescription = "Report",
                            tint = SafeHomeColors.DangerRed,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            // Input field
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "Attach",
                        tint = SafeHomeColors.TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(SafeHomeColors.DarkCard)
                        .border(1.dp, SafeHomeColors.CardBorderLight, RoundedCornerShape(20.dp))
                ) {
                    TextField(
                        value = state.inputText,
                        onValueChange = { viewModel.onAction(ChatConversationAction.UpdateInput(it)) },
                        placeholder = {
                            Text(
                                text = "Type ephemeral message...",
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
                    onClick = { viewModel.onAction(ChatConversationAction.SendCurrentMessage) },
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(SafeHomeColors.PrimaryBlue)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Anti-fraud reminder pill
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF16151E))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = SafeHomeColors.WarningAmber,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Never send money or deposits outside SafeHome.",
                        fontSize = 11.sp,
                        color = SafeHomeColors.WarningAmber
                    )
                }
            }

            // Messages
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(chat?.messages ?: emptyList()) { msg ->
                    ChatBubble(msg)
                }
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    val isMe = message.isFromMe
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMe) 16.dp else 4.dp,
                        bottomEnd = if (isMe) 4.dp else 16.dp
                    )
                )
                .background(if (isMe) SafeHomeColors.PrimaryBlue else SafeHomeColors.DarkSurface)
                .border(
                    1.dp,
                    if (isMe) SafeHomeColors.PrimaryBlue else SafeHomeColors.CardBorder,
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMe) 16.dp else 4.dp,
                        bottomEnd = if (isMe) 4.dp else 16.dp
                    )
                )
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .fillMaxWidth(0.78f)
        ) {
            Column {
                Text(
                    text = message.text,
                    fontSize = 14.sp,
                    color = Color.White,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.timestamp,
                    fontSize = 10.sp,
                    color = if (isMe) Color.White.copy(alpha = 0.7f) else SafeHomeColors.TextMuted,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
