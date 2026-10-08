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

package com.romeodev.safehomeapp.feature_core_domain.logics

import com.romeodev.safehomeapp.feature_core_domain.logics.onboarding.CheckIsOnboardedLogic
import com.romeodev.safehomeapp.feature_core_domain.logics.onboarding.SetOnboardedLogic

data class OnboardingLogics(
    val checkIsOnboarded: CheckIsOnboardedLogic,
    val setOnboarded: SetOnboardedLogic,
)
