package com.romeodev.safehomeapp.feature_home_domain.repositories

import com.romeodev.safehomeapp.feature_home_domain.models.Appointment
import com.romeodev.safehomeapp.feature_home_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_home_domain.models.EphemeralChat
import com.romeodev.safehomeapp.feature_home_domain.models.Lead
import com.romeodev.safehomeapp.feature_home_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_home_domain.models.Property
import com.romeodev.safehomeapp.feature_home_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_home_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_home_domain.models.ZoneSafety
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getProperties(): Flow<List<Property>>
    fun getPropertyById(id: String): Flow<Property?>
    fun getZones(): Flow<List<ZoneSafety>>
    fun getConditionReport(propertyId: String): Flow<ConditionReport>
    fun getTitleValidation(propertyId: String): Flow<TitleValidation>
    
    fun getAppointments(): Flow<List<Appointment>>
    suspend fun bookAppointment(propertyId: String, date: String, time: String, isVideoCall: Boolean): Result<Appointment>
    
    fun getChats(): Flow<List<EphemeralChat>>
    fun getChatById(id: String): Flow<EphemeralChat?>
    suspend fun sendMessage(chatId: String, messageText: String): Result<Unit>
    suspend fun reportScam(reason: String, details: String): Result<Unit>
    
    fun getOwnerDashboard(): Flow<OwnerDashboardData>
    fun getMyProperties(): Flow<List<Property>>
    fun getLeads(): Flow<List<Lead>>
    fun getOffers(propertyId: String): Flow<List<PropertyOffer>>
    suspend fun publishProperty(property: Property): Result<Unit>
}
