package com.romeodev.safehomeapp.feature_assistant_presentation.di

import com.romeodev.safehomeapp.feature_assistant_presentation.viewmodels.AssistantViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val assistantPresentationModule = module {
    viewModelOf(::AssistantViewModel)
}
