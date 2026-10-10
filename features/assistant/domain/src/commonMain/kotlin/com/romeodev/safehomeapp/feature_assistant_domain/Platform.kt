package com.romeodev.safehomeapp.feature_assistant_domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
