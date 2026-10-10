package com.romeodev.safehomeapp.feature_auth_domain.di

import com.romeodev.safehomeapp.feature_auth_domain.logics.AuthLogics
import com.romeodev.safehomeapp.feature_auth_domain.logics.SignInLogic
import com.romeodev.safehomeapp.feature_auth_domain.logics.SignUpLogic
import com.romeodev.safehomeapp.feature_auth_domain.logics.SocialSignInLogic
import com.romeodev.safehomeapp.feature_auth_domain.logics.ValidateEmailLogic
import com.romeodev.safehomeapp.feature_auth_domain.logics.ValidatePasswordLogic
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {
    factoryOf(::SignInLogic)
    factoryOf(::SignUpLogic)
    factoryOf(::SocialSignInLogic)
    factoryOf(::ValidateEmailLogic)
    factoryOf(::ValidatePasswordLogic)
    factoryOf(::AuthLogics)
}
