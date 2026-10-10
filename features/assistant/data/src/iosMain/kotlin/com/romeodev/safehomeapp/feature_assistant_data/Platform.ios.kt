package com.romeodev.safehomeapp.feature_assistant_data

class IOSPlatform : Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()
