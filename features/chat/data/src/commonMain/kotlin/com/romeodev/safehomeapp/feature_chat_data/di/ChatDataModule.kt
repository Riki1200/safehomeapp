package com.romeodev.safehomeapp.feature_chat_data.di

import com.romeodev.safehomeapp.feature_chat_data.repositories.ChatRepositoryImpl
import com.romeodev.safehomeapp.feature_chat_domain.repositories.ChatRepository
import org.koin.dsl.module

val chatDataModule = module {
    single<ChatRepository> { ChatRepositoryImpl() }
}
