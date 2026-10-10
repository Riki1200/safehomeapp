package com.romeodev.safehomeapp.feature_owner_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
