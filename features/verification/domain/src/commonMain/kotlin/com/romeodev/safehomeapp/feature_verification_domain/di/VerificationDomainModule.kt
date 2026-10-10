package com.romeodev.safehomeapp.feature_verification_domain.di

import com.romeodev.safehomeapp.feature_verification_domain.logics.GetVerificationStatusLogic
import com.romeodev.safehomeapp.feature_verification_domain.logics.UpdateVerificationProgressLogic
import org.koin.dsl.module

val verificationDomainModule = module {
    factory { GetVerificationStatusLogic(get()) }
    factory { UpdateVerificationProgressLogic(get()) }
}
