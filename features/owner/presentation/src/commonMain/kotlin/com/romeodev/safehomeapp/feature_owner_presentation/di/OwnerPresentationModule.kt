package com.romeodev.safehomeapp.feature_owner_presentation.di

import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ownerPresentationModule = module {
    viewModelOf(::OwnerPortalViewModel)
}
