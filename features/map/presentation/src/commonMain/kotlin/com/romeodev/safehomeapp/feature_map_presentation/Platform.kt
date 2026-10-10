package com.romeodev.safehomeapp.feature_map_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
