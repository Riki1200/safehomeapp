package com.romeodev.safehomeapp.feature_chat_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
