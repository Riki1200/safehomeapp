package com.romeodev.safehomeapp.feature_appointments_data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
