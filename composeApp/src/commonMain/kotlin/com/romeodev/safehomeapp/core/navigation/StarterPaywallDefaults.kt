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

package com.romeodev.safehomeapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Api
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Update
import com.romeodev.safehomeapp.feature_purchases_presentation.ui.screens.Paywalls
import com.romeodev.safehomeapp.feature_purchases_presentation.ui.screens.paywalls.PaywallV1UiContent
import com.romeodev.safehomeapp.feature_purchases_presentation.ui.screens.paywalls.PurchaseFeature
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.compose_multiplatform
import com.romeodev.safehomeapp.feature_resources.paywall_v1_btn_continue
import com.romeodev.safehomeapp.feature_resources.paywall_v1_btn_trial
import com.romeodev.safehomeapp.feature_resources.paywall_v1_faqs_title
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_1
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_10
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_11
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_2
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_3
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_4
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_5
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_6
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_7
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_8
import com.romeodev.safehomeapp.feature_resources.paywall_v1_feature_9
import com.romeodev.safehomeapp.feature_resources.paywall_v1_purchasing
import com.romeodev.safehomeapp.feature_resources.paywall_v1_restore_purchases
import com.romeodev.safehomeapp.feature_resources.paywall_v1_reviews_title
import com.romeodev.safehomeapp.feature_resources.paywall_v1_title
import com.romeodev.safehomeapp.feature_resources.privacy_policy
import com.romeodev.safehomeapp.feature_resources.starter_purchases_error_button_try_again
import com.romeodev.safehomeapp.feature_resources.starter_purchases_products_not_found
import com.romeodev.safehomeapp.feature_resources.terms_of_use
import com.romeodev.safehomeapp.core.AppConstants

internal fun starterDefaultPaywallV1(): Paywalls =
    Paywalls.V1(
        ui =
            PaywallV1UiContent(
                heroImage = Res.drawable.compose_multiplatform,
                title = Res.string.paywall_v1_title,
                productsNotFoundTitle = Res.string.starter_purchases_products_not_found,
                tryAgainButtonText = Res.string.starter_purchases_error_button_try_again,
                purchasingText = Res.string.paywall_v1_purchasing,
                trialButtonText = Res.string.paywall_v1_btn_trial,
                continueButtonText = Res.string.paywall_v1_btn_continue,
                restorePurchasesText = Res.string.paywall_v1_restore_purchases,
                privacyPolicyText = Res.string.privacy_policy,
                termsOfUseText = Res.string.terms_of_use,
                reviewsTitle = Res.string.paywall_v1_reviews_title,
                faqsTitle = Res.string.paywall_v1_faqs_title,
                features = starterDefaultPaywallV1Features(),
            ),
        privacyPolicyUrl = AppConstants.PRIVACY_POLICY,
        termsOfUseUrl = AppConstants.TERMS_OF_USE,
    )

private fun starterDefaultPaywallV1Features(): List<PurchaseFeature> =
    listOf(
        PurchaseFeature(Icons.Default.AccountTree, Res.string.paywall_v1_feature_1),
        PurchaseFeature(Icons.Default.Code, Res.string.paywall_v1_feature_2),
        PurchaseFeature(Icons.Default.Storage, Res.string.paywall_v1_feature_3),
        PurchaseFeature(Icons.Default.Api, Res.string.paywall_v1_feature_4),
        PurchaseFeature(Icons.Default.Security, Res.string.paywall_v1_feature_5),
        PurchaseFeature(Icons.Default.Sync, Res.string.paywall_v1_feature_6),
        PurchaseFeature(Icons.Default.Extension, Res.string.paywall_v1_feature_7),
        PurchaseFeature(Icons.Default.Devices, Res.string.paywall_v1_feature_8),
        PurchaseFeature(Icons.Default.Update, Res.string.paywall_v1_feature_9),
        PurchaseFeature(Icons.Default.RocketLaunch, Res.string.paywall_v1_feature_10),
        PurchaseFeature(Icons.Default.AccountTree, Res.string.paywall_v1_feature_11),
    )
