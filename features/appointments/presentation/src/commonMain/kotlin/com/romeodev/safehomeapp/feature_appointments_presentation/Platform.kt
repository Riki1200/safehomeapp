package com.romeodev.safehomeapp.feature_appointments_presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
