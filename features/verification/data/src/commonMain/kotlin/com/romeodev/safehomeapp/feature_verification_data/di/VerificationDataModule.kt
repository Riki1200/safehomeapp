package com.romeodev.safehomeapp.feature_verification_data.di

import com.romeodev.safehomeapp.feature_verification_data.repositories.VerificationRepositoryImpl
import com.romeodev.safehomeapp.feature_verification_domain.repositories.VerificationRepository
import org.koin.dsl.module

val verificationDataModule = module {
    single<VerificationRepository> { VerificationRepositoryImpl() }
}
