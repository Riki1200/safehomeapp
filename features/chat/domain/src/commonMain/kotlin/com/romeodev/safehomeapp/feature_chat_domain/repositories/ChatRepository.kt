package com.romeodev.safehomeapp.feature_chat_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.EphemeralChat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChats(): Flow<List<EphemeralChat>>
    fun getChatById(id: String): Flow<EphemeralChat?>
    suspend fun sendMessage(chatId: String, messageText: String): Result<Unit>
    suspend fun reportScam(reason: String, details: String): Result<Unit>
}
