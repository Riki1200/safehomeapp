package com.romeodev.safehomeapp.feature_map_presentation

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
