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

package com.romeodev.safehomeapp.core.di

import com.romeodev.safehomeapp.core.datastore.di.dataStoreModule
import com.romeodev.safehomeapp.core.events.di.eventsModule
import com.romeodev.safehomeapp.feature_analytics_data.di.analyticsDataModule
import com.romeodev.safehomeapp.feature_analytics_domain.di.analyticsDomainModule
import com.romeodev.safehomeapp.feature_core_data.di.coreDataModule
import com.romeodev.safehomeapp.feature_core_domain.di.coreDomainModule
import com.romeodev.safehomeapp.feature_core_presentation.di.corePresentationModule
import com.romeodev.safehomeapp.feature_database.di.databaseModule
import com.romeodev.safehomeapp.feature_notifications_core.notificationsCoreModule
import com.romeodev.safehomeapp.feature_notifications_local.notificationsLocalModule
import com.romeodev.safehomeapp.feature_notifications_push.notificationsPushModule
import com.romeodev.safehomeapp.feature_purchases_data.di.purchasesDataModule
import com.romeodev.safehomeapp.feature_purchases_domain.di.purchasesDomainModule
import com.romeodev.safehomeapp.feature_purchases_presentation.di.purchasesPresentationModule
import com.romeodev.safehomeapp.feature_remote_config_data.di.remoteConfigDataModule
import com.romeodev.safehomeapp.feature_remote_config_domain.di.remoteConfigDomainModule
import com.romeodev.safehomeapp.feature_home_data.di.featureHomeDataModule
import com.romeodev.safehomeapp.feature_home_domain.di.featureHomeDomainModule
import com.romeodev.safehomeapp.feature_home_presentation.di.featureHomePresentationModule
import com.romeodev.safehomeapp.utils.di.utilsModule
import com.romeodev.safehomeapp.core.KmpAppInitializer
import com.romeodev.safehomeapp.core.navigation.appNavigationModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val starterModules = module {
    includes(
        /*Starter Core Modules*/
        coreDataModule,
        coreDomainModule,
        corePresentationModule,
        utilsModule,
        eventsModule,
        dataStoreModule,
        /*Feature: Database*/
        databaseModule,
        /*Feature: Purchases*/
        purchasesDataModule,
        purchasesDomainModule,
        purchasesPresentationModule,
        /*Feature: Analytics*/
        analyticsDomainModule,
        analyticsDataModule,
        /*Feature: Navigation*/
        appNavigationModule,
        /*Feature: RemoteConfig*/
        remoteConfigDataModule,
        remoteConfigDomainModule,
        /*Feature: Notifications*/
        notificationsCoreModule,
        notificationsLocalModule,
        notificationsPushModule,
    )
}

private val kmpAppInitializerModule = module {
    singleOf(::KmpAppInitializer)
}

internal fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            starterModules,
            kmpAppInitializerModule,
            /* Add Modules Here */
            featureHomeDataModule,
            featureHomeDomainModule,
            featureHomePresentationModule
        )
    }
}



















