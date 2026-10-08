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

package com.romeodev.safehomeapp.feature_analytics_data_firebase.di

import com.romeodev.safehomeapp.feature_analytics_data_firebase.FirebaseAnalyticsScope
import com.romeodev.safehomeapp.feature_analytics_data_firebase.get
import org.koin.dsl.module

val analyticsFirebaseDataModule = module {
    single {
        FirebaseAnalyticsScope.get()
    }
}
