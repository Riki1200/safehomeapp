package com.romeodev.safehomeapp.feature_owner_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.Lead
import com.romeodev.safehomeapp.feature_core_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyOffer
import kotlinx.coroutines.flow.Flow

interface OwnerRepository {
    fun getOwnerDashboard(): Flow<OwnerDashboardData>
    fun getMyProperties(): Flow<List<Property>>
    fun getLeads(): Flow<List<Lead>>
    fun getOffers(propertyId: String): Flow<List<PropertyOffer>>
    suspend fun publishProperty(property: Property): Result<Unit>
}
