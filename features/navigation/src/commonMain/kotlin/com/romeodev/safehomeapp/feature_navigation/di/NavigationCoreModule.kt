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

package com.romeodev.safehomeapp.feature_navigation.di

import com.romeodev.safehomeapp.feature_navigation.StarterNavigator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationCoreModule = module {
    singleOf(::StarterNavigator)
}