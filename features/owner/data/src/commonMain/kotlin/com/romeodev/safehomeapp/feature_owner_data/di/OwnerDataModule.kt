package com.romeodev.safehomeapp.feature_owner_data.di

import com.romeodev.safehomeapp.feature_owner_data.repositories.OwnerRepositoryImpl
import com.romeodev.safehomeapp.feature_owner_domain.repositories.OwnerRepository
import org.koin.dsl.module

val ownerDataModule = module {
    single<OwnerRepository> { OwnerRepositoryImpl() }
}
