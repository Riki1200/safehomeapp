package com.romeodev.safehomeapp.feature_profile_domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
