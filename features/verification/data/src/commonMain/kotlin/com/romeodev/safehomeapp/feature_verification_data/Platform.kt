package com.romeodev.safehomeapp.feature_verification_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
