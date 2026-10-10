package com.romeodev.safehomeapp.feature_owner_domain.logics

import com.romeodev.safehomeapp.feature_core_domain.models.Lead
import com.romeodev.safehomeapp.feature_core_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_owner_domain.repositories.OwnerRepository
import kotlinx.coroutines.flow.Flow

class GetOwnerDashboardLogic(
    private val repository: OwnerRepository
) {
    operator fun invoke(): Flow<OwnerDashboardData> = repository.getOwnerDashboard()
}

class GetMyPropertiesLogic(
    private val repository: OwnerRepository
) {
    operator fun invoke(): Flow<List<Property>> = repository.getMyProperties()
}

class GetLeadsLogic(
    private val repository: OwnerRepository
) {
    operator fun invoke(): Flow<List<Lead>> = repository.getLeads()
}

class GetOffersLogic(
    private val repository: OwnerRepository
) {
    operator fun invoke(propertyId: String): Flow<List<PropertyOffer>> = repository.getOffers(propertyId)
}

class PublishPropertyLogic(
    private val repository: OwnerRepository
) {
    suspend operator fun invoke(property: Property): Result<Unit> = repository.publishProperty(property)
}
