package com.romeodev.safehomeapp.feature_assistant_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
