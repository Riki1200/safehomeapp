package com.romeodev.safehomeapp.feature_assistant_data.di

import com.romeodev.safehomeapp.feature_assistant_data.repositories.AssistantRepositoryImpl
import com.romeodev.safehomeapp.feature_assistant_domain.repositories.AssistantRepository
import org.koin.dsl.module

val assistantDataModule = module {
    single<AssistantRepository> { AssistantRepositoryImpl() }
}
