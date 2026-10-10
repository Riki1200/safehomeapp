package com.romeodev.safehomeapp.feature_owner_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
