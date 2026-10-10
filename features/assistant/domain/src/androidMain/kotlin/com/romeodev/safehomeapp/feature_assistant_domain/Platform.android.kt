package com.romeodev.safehomeapp.feature_assistant_domain

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
