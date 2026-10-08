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

package com.romeodev.safehomeapp.feature_remote_config_data.di

import com.romeodev.safehomeapp.feature_remote_config_data.FirebaseRemoteConfigRepository
import com.romeodev.safehomeapp.feature_remote_config_data._FirebaseRemoteConfigInitializer
import com.romeodev.safehomeapp.feature_remote_config_domain.RemoteConfigRepository
import com.romeodev.safehomeapp.feature_remote_config_domain._RemoteConfigInitializer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.remoteConfig
import org.koin.dsl.module

val remoteConfigDataModule = module {
    single<_RemoteConfigInitializer>{
        _FirebaseRemoteConfigInitializer(
            remoteConfig = Firebase.remoteConfig
        )
    }
    single<RemoteConfigRepository>{
        FirebaseRemoteConfigRepository(
            remoteConfig = Firebase.remoteConfig
        )
    }
 }