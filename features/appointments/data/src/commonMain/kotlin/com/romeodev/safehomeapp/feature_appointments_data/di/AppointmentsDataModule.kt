package com.romeodev.safehomeapp.feature_appointments_data.di

import com.romeodev.safehomeapp.feature_appointments_data.repositories.AppointmentsRepositoryImpl
import com.romeodev.safehomeapp.feature_appointments_domain.repositories.AppointmentsRepository
import org.koin.dsl.module

val appointmentsDataModule = module {
    single<AppointmentsRepository> { AppointmentsRepositoryImpl() }
}
