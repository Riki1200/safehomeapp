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

package com.romeodev.safehomeapp.feature_home_data.di

import com.romeodev.safehomeapp.feature_home_data.repositories.HomeRepositoryImpl
import com.romeodev.safehomeapp.feature_home_domain.repositories.HomeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val featureHomeDataModule = module {
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
}