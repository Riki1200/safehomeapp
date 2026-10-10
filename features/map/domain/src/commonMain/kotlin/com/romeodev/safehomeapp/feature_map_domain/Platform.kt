package com.romeodev.safehomeapp.feature_map_domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
