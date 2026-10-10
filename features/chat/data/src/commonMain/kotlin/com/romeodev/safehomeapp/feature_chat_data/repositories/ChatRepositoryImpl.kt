package com.romeodev.safehomeapp.feature_chat_data.repositories

import com.romeodev.safehomeapp.feature_chat_domain.repositories.ChatRepository
import com.romeodev.safehomeapp.feature_core_domain.models.ChatMessage
import com.romeodev.safehomeapp.feature_core_domain.models.EphemeralChat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl : ChatRepository {

    private val chatsFlow = MutableStateFlow(
        listOf(
            EphemeralChat(
                id = "chat-1",
                participantName = "Andrea Salinas",
                propertyTitle = "Apartment with terrace",
                lastMessage = "See you on Thursday at 4:00 PM outside Colima 142!",
                remainingHours = 27,
                remainingMinutes = 52,
                remainingSeconds = 20,
                isExpired = false,
                unreadCount = 1,
                messages = listOf(
                    ChatMessage("m1", "Andrea Salinas", false, "Hello! Excited to show you the apartment.", "10:15 AM"),
                    ChatMessage("m2", "You", true, "Hi Andrea! Does the building have 24/7 security concierge?", "10:18 AM"),
                    ChatMessage("m3", "Andrea Salinas", false, "Yes! 24/7 guarded lobby with CCTV, and the parking space is right by the elevator.", "10:20 AM"),
                    ChatMessage("m4", "Andrea Salinas", false, "See you on Thursday at 4:00 PM outside Colima 142!", "10:22 AM")
                )
            ),
            EphemeralChat(
                id = "chat-2",
                participantName = "Jorge Castañeda",
                propertyTitle = "House with garden",
                lastMessage = "Thanks for the mortgage pre-approval document.",
                remainingHours = 12,
                remainingMinutes = 15,
                remainingSeconds = 44,
                isExpired = false,
                unreadCount = 0,
                messages = listOf(
                    ChatMessage("m1", "Jorge Castañeda", false, "Hello, I submitted the mortgage pre-approval from BBVA.", "Yesterday"),
                    ChatMessage("m2", "You", true, "Thanks for the mortgage pre-approval document. It looks solid.", "Yesterday")
                )
            ),
            EphemeralChat(
                id = "chat-3",
                participantName = "Daniel Ortega",
                propertyTitle = "Loft in Juárez",
                lastMessage = "Viewing completed. Chat expired.",
                remainingHours = 0,
                remainingMinutes = 0,
                remainingSeconds = 0,
                isExpired = true,
                unreadCount = 0,
                messages = emptyList()
            )
        )
    )

    override fun getChats(): Flow<List<EphemeralChat>> = chatsFlow.asStateFlow()

    override fun getChatById(id: String): Flow<EphemeralChat?> =
        chatsFlow.map { list -> list.find { it.id == id } ?: list.firstOrNull() }

    override suspend fun sendMessage(chatId: String, messageText: String): Result<Unit> {
        val currentList = chatsFlow.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == chatId }
        if (index != -1) {
            val chat = currentList[index]
            val newMsg = ChatMessage(
                id = "m-${chat.messages.size + 1}",
                senderName = "You",
                isFromMe = true,
                text = messageText,
                timestamp = "Just now"
            )
            currentList[index] = chat.copy(
                lastMessage = messageText,
                messages = chat.messages + newMsg
            )
            chatsFlow.value = currentList
        }
        return Result.success(Unit)
    }

    override suspend fun reportScam(reason: String, details: String): Result<Unit> {
        return Result.success(Unit)
    }
}
