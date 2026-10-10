package com.romeodev.safehomeapp.feature_chat_presentation

class IOSPlatform : Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()
