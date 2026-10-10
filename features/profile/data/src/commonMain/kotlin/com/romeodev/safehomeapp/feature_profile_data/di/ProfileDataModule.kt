package com.romeodev.safehomeapp.feature_profile_data.di

import com.romeodev.safehomeapp.feature_profile_data.repositories.ProfileRepositoryImpl
import com.romeodev.safehomeapp.feature_profile_domain.repositories.ProfileRepository
import org.koin.dsl.module

val profileDataModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl() }
}
