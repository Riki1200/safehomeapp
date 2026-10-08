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

package com.romeodev.safehomeapp.feature_analytics_domain.di

import com.romeodev.safehomeapp.feature_analytics_domain.Analytics
import com.romeodev.safehomeapp.feature_analytics_domain.AnalyticsRouter
import com.romeodev.safehomeapp.feature_analytics_domain.AnalyticsScope
import com.romeodev.safehomeapp.feature_analytics_domain.EventsTracker
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsDomainModule = module {
    single<Analytics> {
        AnalyticsRouter.create(AnalyticsScope.snapshot())
    } bind EventsTracker::class
}
