package com.romeodev.safehomeapp.feature_appointments_presentation.di

import com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels.AppointmentsViewModel
import com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels.BookViewingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appointmentsPresentationModule = module {
    viewModelOf(::AppointmentsViewModel)
    viewModelOf(::BookViewingViewModel)
}
