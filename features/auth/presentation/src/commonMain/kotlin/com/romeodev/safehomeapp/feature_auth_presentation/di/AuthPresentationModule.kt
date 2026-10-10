package com.romeodev.safehomeapp.feature_auth_presentation.di

import com.romeodev.safehomeapp.feature_auth_presentation.viewmodels.SignInViewModel
import com.romeodev.safehomeapp.feature_auth_presentation.viewmodels.SignUpViewModel
import com.romeodev.safehomeapp.feature_auth_presentation.viewmodels.WelcomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
}
