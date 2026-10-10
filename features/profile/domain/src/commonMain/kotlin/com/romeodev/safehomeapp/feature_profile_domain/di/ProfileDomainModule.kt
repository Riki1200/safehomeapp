package com.romeodev.safehomeapp.feature_profile_domain.di

import com.romeodev.safehomeapp.feature_profile_domain.logics.GetProfilePreferencesLogic
import com.romeodev.safehomeapp.feature_profile_domain.logics.UpdateProfilePreferencesLogic
import org.koin.dsl.module

val profileDomainModule = module {
    factory { GetProfilePreferencesLogic(get()) }
    factory { UpdateProfilePreferencesLogic(get()) }
}
