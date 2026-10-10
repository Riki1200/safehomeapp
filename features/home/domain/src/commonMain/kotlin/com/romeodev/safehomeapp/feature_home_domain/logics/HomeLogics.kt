package com.romeodev.safehomeapp.feature_home_domain.logics

import com.romeodev.safehomeapp.feature_home_domain.models.Appointment
import com.romeodev.safehomeapp.feature_home_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_home_domain.models.EphemeralChat
import com.romeodev.safehomeapp.feature_home_domain.models.Lead
import com.romeodev.safehomeapp.feature_home_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_home_domain.models.Property
import com.romeodev.safehomeapp.feature_home_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_home_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_home_domain.models.ZoneSafety
import com.romeodev.safehomeapp.feature_home_domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetPropertiesLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<Property>> = repository.getProperties()
}

class GetPropertyDetailLogic(private val repository: HomeRepository) {
    operator fun invoke(id: String): Flow<Property?> = repository.getPropertyById(id)
}

class GetZonesLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<ZoneSafety>> = repository.getZones()
}

class GetConditionReportLogic(private val repository: HomeRepository) {
    operator fun invoke(propertyId: String): Flow<ConditionReport> = repository.getConditionReport(propertyId)
}

class GetTitleValidationLogic(private val repository: HomeRepository) {
    operator fun invoke(propertyId: String): Flow<TitleValidation> = repository.getTitleValidation(propertyId)
}

class GetAppointmentsLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<Appointment>> = repository.getAppointments()
}

class BookAppointmentLogic(private val repository: HomeRepository) {
    suspend operator fun invoke(propertyId: String, date: String, time: String, isVideoCall: Boolean): Result<Appointment> =
        repository.bookAppointment(propertyId, date, time, isVideoCall)
}

class GetChatsLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<EphemeralChat>> = repository.getChats()
}

class GetChatConversationLogic(private val repository: HomeRepository) {
    operator fun invoke(chatId: String): Flow<EphemeralChat?> = repository.getChatById(chatId)
}

class SendMessageLogic(private val repository: HomeRepository) {
    suspend operator fun invoke(chatId: String, messageText: String): Result<Unit> =
        repository.sendMessage(chatId, messageText)
}

class ReportScamLogic(private val repository: HomeRepository) {
    suspend operator fun invoke(reason: String, details: String): Result<Unit> =
        repository.reportScam(reason, details)
}

class GetOwnerDashboardLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<OwnerDashboardData> = repository.getOwnerDashboard()
}

class GetMyPropertiesLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<Property>> = repository.getMyProperties()
}

class GetLeadsLogic(private val repository: HomeRepository) {
    operator fun invoke(): Flow<List<Lead>> = repository.getLeads()
}

class GetOffersLogic(private val repository: HomeRepository) {
    operator fun invoke(propertyId: String): Flow<List<PropertyOffer>> = repository.getOffers(propertyId)
}

class PublishPropertyLogic(private val repository: HomeRepository) {
    suspend operator fun invoke(property: Property): Result<Unit> = repository.publishProperty(property)
}
