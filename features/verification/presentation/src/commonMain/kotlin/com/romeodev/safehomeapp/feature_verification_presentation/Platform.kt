package com.romeodev.safehomeapp.feature_verification_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
