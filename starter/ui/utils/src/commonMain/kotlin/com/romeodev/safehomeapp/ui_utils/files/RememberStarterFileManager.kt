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
import com.romeodev.safehomeapp.utils.files.StarterFileManager
import com.romeodev.safehomeapp.utils.starter.ExperimentalStarterApi

/**
 * Returns a [StarterFileManager] bound to the current Compose host.
 *
 * On Android, this supplies the current [androidx.activity.ComponentActivity], which is
 * required for [StarterFileManager.saveFileIn], [StarterFileManager.shareFile], and
 * [StarterFileManager.openFile]. Do not use the Koin singleton for those methods;
 * inject or call other methods from Koin when activity is not needed.
 */
@OptIn(ExperimentalStarterApi::class)
@Composable
expect fun rememberStarterFileManager(): StarterFileManager
