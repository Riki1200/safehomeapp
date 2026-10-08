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

package com.romeodev.safehomeapp.feature_purchases_domain.logics

import com.romeodev.safehomeapp.feature_purchases_domain.models.Product
import com.romeodev.safehomeapp.feature_purchases_domain.repositories.PurchasesRepository

/**
 * Restores previous purchases from the store.
 */
class RestorePurchasesLogic(
    private val repository: PurchasesRepository,
) {

    suspend operator fun invoke(): Result<List<Product>> = repository.restorePurchases()

}