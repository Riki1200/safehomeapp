package com.romeodev.safehomeapp.feature_assistant_data

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
