package com.romeodev.safehomeapp.feature_profile_presentation.di

import com.romeodev.safehomeapp.feature_profile_presentation.viewmodels.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profilePresentationModule = module {
    viewModelOf(::ProfileViewModel)
}
