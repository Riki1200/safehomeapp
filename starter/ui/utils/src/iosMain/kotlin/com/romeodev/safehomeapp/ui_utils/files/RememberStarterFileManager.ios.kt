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

package com.romeodev.safehomeapp.ui_utils.files

import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import com.romeodev.safehomeapp.utils.files.StarterFileManager
import com.romeodev.safehomeapp.utils.starter.ExperimentalStarterApi

@OptIn(markerClass = [ExperimentalStarterApi::class])
@Composable
actual fun rememberStarterFileManager(): StarterFileManager {
    return retain { StarterFileManager() }
}