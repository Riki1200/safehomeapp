package com.romeodev.safehomeapp.feature_map_presentation.di

import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.ExploreViewModel
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.PropertyDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mapPresentationModule = module {
    viewModelOf(::ExploreViewModel)
    viewModelOf(::PropertyDetailViewModel)
}
