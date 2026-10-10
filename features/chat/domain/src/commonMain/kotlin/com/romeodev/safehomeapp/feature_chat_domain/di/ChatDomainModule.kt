package com.romeodev.safehomeapp.feature_chat_domain.di

import com.romeodev.safehomeapp.feature_chat_domain.logics.GetChatConversationLogic
import com.romeodev.safehomeapp.feature_chat_domain.logics.GetChatsLogic
import com.romeodev.safehomeapp.feature_chat_domain.logics.ReportScamLogic
import com.romeodev.safehomeapp.feature_chat_domain.logics.SendMessageLogic
import org.koin.dsl.module

val chatDomainModule = module {
    factory { GetChatsLogic(get()) }
    factory { GetChatConversationLogic(get()) }
    factory { SendMessageLogic(get()) }
    factory { ReportScamLogic(get()) }
}
