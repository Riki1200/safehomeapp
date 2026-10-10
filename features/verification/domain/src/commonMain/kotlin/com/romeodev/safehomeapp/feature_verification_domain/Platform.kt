package com.romeodev.safehomeapp.feature_verification_domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
