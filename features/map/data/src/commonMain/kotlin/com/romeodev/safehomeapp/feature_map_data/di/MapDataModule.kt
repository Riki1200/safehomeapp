package com.romeodev.safehomeapp.feature_map_data.di

import com.romeodev.safehomeapp.feature_map_data.repositories.MapRepositoryImpl
import com.romeodev.safehomeapp.feature_map_domain.repositories.MapRepository
import org.koin.dsl.module

val mapDataModule = module {
    single<MapRepository> { MapRepositoryImpl() }
}
