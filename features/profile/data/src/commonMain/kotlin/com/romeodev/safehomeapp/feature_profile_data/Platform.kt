package com.romeodev.safehomeapp.feature_profile_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
