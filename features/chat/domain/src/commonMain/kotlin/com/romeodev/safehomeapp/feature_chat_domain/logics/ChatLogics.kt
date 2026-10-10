package com.romeodev.safehomeapp.feature_chat_domain.logics

import com.romeodev.safehomeapp.feature_chat_domain.repositories.ChatRepository
import com.romeodev.safehomeapp.feature_core_domain.models.EphemeralChat
import kotlinx.coroutines.flow.Flow

class GetChatsLogic(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<List<EphemeralChat>> = repository.getChats()
}

class GetChatConversationLogic(
    private val repository: ChatRepository
) {
    operator fun invoke(id: String): Flow<EphemeralChat?> = repository.getChatById(id)
}

class SendMessageLogic(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String, messageText: String): Result<Unit> =
        repository.sendMessage(chatId, messageText)
}

class ReportScamLogic(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(reason: String, details: String): Result<Unit> =
        repository.reportScam(reason, details)
}
