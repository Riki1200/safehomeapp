package com.romeodev.safehomeapp.feature_appointments_domain.di

import com.romeodev.safehomeapp.feature_appointments_domain.logics.BookAppointmentLogic
import com.romeodev.safehomeapp.feature_appointments_domain.logics.GetAppointmentsLogic
import org.koin.dsl.module

val appointmentsDomainModule = module {
    factory { GetAppointmentsLogic(get()) }
    factory { BookAppointmentLogic(get()) }
}
