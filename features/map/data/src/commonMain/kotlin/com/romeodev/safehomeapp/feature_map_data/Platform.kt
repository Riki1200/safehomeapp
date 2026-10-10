package com.romeodev.safehomeapp.feature_map_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
