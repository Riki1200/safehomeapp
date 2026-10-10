package com.romeodev.safehomeapp.feature_chat_domain

class IOSPlatform : Platform {
    override val name: String = "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()
