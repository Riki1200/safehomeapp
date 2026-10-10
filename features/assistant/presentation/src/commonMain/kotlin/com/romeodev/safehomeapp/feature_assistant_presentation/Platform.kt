package com.romeodev.safehomeapp.feature_assistant_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
