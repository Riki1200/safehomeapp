package com.romeodev.safehomeapp.feature_appointments_data.repositories

import com.romeodev.safehomeapp.feature_appointments_domain.repositories.AppointmentsRepository
import com.romeodev.safehomeapp.feature_core_domain.models.Appointment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppointmentsRepositoryImpl : AppointmentsRepository {

    private val appointmentsFlow = MutableStateFlow(
        listOf(
            Appointment(
                id = "apt-1",
                propertyId = "prop-1",
                propertyTitle = "Apartment with terrace",
                date = "Thu, Oct 8",
                time = "16:00",
                hostName = "Andrea Salinas",
                isVideoCall = false,
                bookingCode = "SH-4821",
                status = "Confirmed",
                isUpcoming = true
            ),
            Appointment(
                id = "apt-2",
                propertyId = "prop-2",
                propertyTitle = "House with garden",
                date = "Sat, Oct 10",
                time = "11:30",
                hostName = "Andrea Salinas",
                isVideoCall = true,
                bookingCode = "SH-4933",
                status = "Confirmed",
                isUpcoming = true
            ),
            Appointment(
                id = "apt-3",
                propertyId = "prop-4",
                propertyTitle = "Penthouse in Condesa",
                date = "Tue, Sep 29",
                time = "15:00",
                hostName = "Carlos Slim Jr.",
                isVideoCall = false,
                bookingCode = "SH-3810",
                status = "Completed",
                isUpcoming = false
            )
        )
    )

    override fun getAppointments(): Flow<List<Appointment>> = appointmentsFlow.asStateFlow()

    override suspend fun bookAppointment(
        propertyId: String,
        date: String,
        time: String,
        isVideoCall: Boolean
    ): Result<Appointment> {
        val newAppointment = Appointment(
            id = "apt-${appointmentsFlow.value.size + 1}",
            propertyId = propertyId,
            propertyTitle = "Apartment with terrace",
            date = date,
            time = time,
            hostName = "Andrea Salinas",
            isVideoCall = isVideoCall,
            bookingCode = "SH-4821",
            status = "Confirmed",
            isUpcoming = true
        )
        appointmentsFlow.value = listOf(newAppointment) + appointmentsFlow.value
        return Result.success(newAppointment)
    }
}
