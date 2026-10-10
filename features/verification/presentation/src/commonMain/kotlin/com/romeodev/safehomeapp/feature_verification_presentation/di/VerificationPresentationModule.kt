package com.romeodev.safehomeapp.feature_verification_presentation.di

import com.romeodev.safehomeapp.feature_verification_presentation.viewmodels.VerificationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val verificationPresentationModule = module {
    viewModelOf(::VerificationViewModel)
}
