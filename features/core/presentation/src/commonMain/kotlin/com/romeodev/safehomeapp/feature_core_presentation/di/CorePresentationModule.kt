/*
 *
 *  *
 *  *  * Copyright (c) 2026
 *  *  *
 *  *  * Author: Athar Gul
 *  *  * GitHub: https://github.com/DevAtrii/Kmp-Starter-Template
 *  *  * YouTube: https://www.youtube.com/@devatrii/videos
 *  *  *
 *  *  * All rights reserved.
 *  *
 *  *
 *
 */

package com.romeodev.safehomeapp.feature_core_presentation.di

import com.romeodev.safehomeapp.feature_core_presentation.viewmodels.OnboardingViewModel
import com.romeodev.safehomeapp.feature_core_presentation.viewmodels.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module



val corePresentationModule  = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SplashViewModel)
}