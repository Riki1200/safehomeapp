package com.romeodev.safehomeapp.feature_assistant_domain.di

import com.romeodev.safehomeapp.feature_assistant_domain.logics.AskAssistantLogic
import org.koin.dsl.module

val assistantDomainModule = module {
    factory { AskAssistantLogic(get()) }
}
