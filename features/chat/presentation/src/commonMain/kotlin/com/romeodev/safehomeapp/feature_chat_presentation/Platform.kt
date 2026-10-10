package com.romeodev.safehomeapp.feature_chat_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
