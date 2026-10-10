package com.romeodev.safehomeapp.feature_owner_domain

class AndroidPlatform : Platform {
    override val name: String = "Android"
}

actual fun getPlatform(): Platform = AndroidPlatform()
