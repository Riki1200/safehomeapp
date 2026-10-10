package com.romeodev.safehomeapp.feature_owner_domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
