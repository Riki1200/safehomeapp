package com.romeodev.safehomeapp.feature_profile_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
