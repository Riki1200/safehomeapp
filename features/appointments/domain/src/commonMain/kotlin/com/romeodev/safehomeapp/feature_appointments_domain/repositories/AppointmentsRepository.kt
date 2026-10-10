package com.romeodev.safehomeapp.feature_appointments_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.Appointment
import kotlinx.coroutines.flow.Flow

interface AppointmentsRepository {
    fun getAppointments(): Flow<List<Appointment>>
    suspend fun bookAppointment(
        propertyId: String,
        date: String,
        time: String,
        isVideoCall: Boolean
    ): Result<Appointment>
}
