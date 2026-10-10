package com.romeodev.safehomeapp.feature_appointments_domain.logics

import com.romeodev.safehomeapp.feature_appointments_domain.repositories.AppointmentsRepository
import com.romeodev.safehomeapp.feature_core_domain.models.Appointment
import kotlinx.coroutines.flow.Flow

class GetAppointmentsLogic(
    private val repository: AppointmentsRepository
) {
    operator fun invoke(): Flow<List<Appointment>> = repository.getAppointments()
}

class BookAppointmentLogic(
    private val repository: AppointmentsRepository
) {
    suspend operator fun invoke(
        propertyId: String,
        date: String,
        time: String,
        isVideoCall: Boolean
    ): Result<Appointment> = repository.bookAppointment(propertyId, date, time, isVideoCall)
}
