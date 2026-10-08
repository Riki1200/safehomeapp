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

package com.romeodev.safehomeapp.feature_purchases_presentation.di

import com.romeodev.safehomeapp.feature_purchases_presentation.PurchasesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val purchasesPresentationModule = module {
    viewModelOf(::PurchasesViewModel)
}