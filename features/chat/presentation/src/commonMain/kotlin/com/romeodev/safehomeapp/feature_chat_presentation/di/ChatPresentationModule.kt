package com.romeodev.safehomeapp.feature_chat_presentation.di

import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ChatConversationViewModel
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ChatsViewModel
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ReportScamViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatPresentationModule = module {
    viewModelOf(::ChatsViewModel)
    viewModelOf(::ChatConversationViewModel)
    viewModelOf(::ReportScamViewModel)
}
