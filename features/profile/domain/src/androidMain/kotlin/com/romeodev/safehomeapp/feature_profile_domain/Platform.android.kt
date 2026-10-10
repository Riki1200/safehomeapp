package com.romeodev.safehomeapp.feature_profile_domain

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
