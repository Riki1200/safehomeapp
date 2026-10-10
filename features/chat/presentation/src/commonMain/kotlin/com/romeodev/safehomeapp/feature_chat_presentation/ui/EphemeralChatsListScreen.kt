package com.romeodev.safehomeapp.feature_chat_presentation.ui

import com.romeodev.safehomeapp.feature_chat_presentation.events.*
import com.romeodev.safehomeapp.feature_chat_presentation.states.*
import com.romeodev.safehomeapp.feature_chat_presentation.models.*
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.*

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.romeodev.safehomeapp.feature_core_domain.models.EphemeralChat
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_chat_presentation.events.ChatsAction
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ChatsViewModel

@Composable
fun EphemeralChatsListScreen(
    viewModel: ChatsViewModel,
    onNavigateToConversation: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val active = state.chats.filter { !it.isExpired }
    val expired = state.chats.filter { it.isExpired }

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Ephemeral Chats",
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Ephemeral Privacy Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF131A26))
                    .border(1.dp, Color(0xFF1F2B3E), RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.LockClock,
                        contentDescription = null,
                        tint = SafeHomeColors.WarningAmber,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Messages and contact details self-destruct 48 hours after your viewing to protect your privacy and prevent off-platform scams.",
                        fontSize = 12.sp,
                        color = SafeHomeColors.TextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(14.dp))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (state.selectedTab == 0) SafeHomeColors.PrimaryBlue else Color.Transparent)
                        .clickable { viewModel.onAction(ChatsAction.SelectTab(0)) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Active (${active.size})",
                        fontSize = 13.sp,
                        fontWeight = if (state.selectedTab == 0) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (state.selectedTab == 0) Color.White else SafeHomeColors.TextSecondary
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (state.selectedTab == 1) SafeHomeColors.PrimaryBlue else Color.Transparent)
                        .clickable { viewModel.onAction(ChatsAction.SelectTab(1)) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Expired (${expired.size})",
                        fontSize = 13.sp,
                        fontWeight = if (state.selectedTab == 1) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (state.selectedTab == 1) Color.White else SafeHomeColors.TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val currentChats = if (state.selectedTab == 0) active else expired

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(currentChats) { chat ->
                    EphemeralChatItemCard(
                        chat = chat,
                        onClick = { onNavigateToConversation(chat.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EphemeralChatItemCard(
    chat: EphemeralChat,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SafeHomeColors.DarkSurface)
            .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF223046)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chat.participantName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("").take(2),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = chat.participantName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    if (!chat.isExpired) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(SafeHomeColors.WarningAmberBg)
                                .border(1.dp, SafeHomeColors.WarningAmberBorder, RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "⏱ ${chat.remainingHours}:${chat.remainingMinutes}:${chat.remainingSeconds}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SafeHomeColors.WarningAmber
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF1E2430))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "Expired",
                                fontSize = 11.sp,
                                color = SafeHomeColors.TextMuted
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = chat.propertyTitle,
                    fontSize = 12.sp,
                    color = SafeHomeColors.PrimaryBlue
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = chat.lastMessage,
                    fontSize = 13.sp,
                    color = SafeHomeColors.TextSecondary,
                    maxLines = 1
                )
            }
        }
    }
}
