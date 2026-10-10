package com.romeodev.safehomeapp.feature_auth_data.di

import com.romeodev.safehomeapp.feature_auth_data.repositories.AuthRepositoryImpl
import com.romeodev.safehomeapp.feature_auth_domain.repositories.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}
