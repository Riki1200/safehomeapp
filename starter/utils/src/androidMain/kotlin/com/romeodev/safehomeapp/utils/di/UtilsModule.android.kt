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

package com.romeodev.safehomeapp.utils.di

import com.romeodev.safehomeapp.utils.datastore.AppDataStore
import com.romeodev.safehomeapp.utils.files.KmpFileManager
import com.romeodev.safehomeapp.utils.files.StarterFileManager
import com.romeodev.safehomeapp.utils.intents.IntentUtils
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformUtilsModule = module {
    singleOf(::AppDataStore)
    singleOf(::IntentUtils)
    single {
        StarterFileManager(
            context = get(),
            activity = null,
        )
    }
    singleOf(::KmpFileManager)
}