package com.romeodev.safehomeapp.feature_map_domain.logics

import com.romeodev.safehomeapp.feature_core_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_core_domain.models.ZoneSafety
import com.romeodev.safehomeapp.feature_map_domain.repositories.MapRepository
import kotlinx.coroutines.flow.Flow

class GetPropertiesLogic(
    private val repository: MapRepository
) {
    operator fun invoke(): Flow<List<Property>> = repository.getProperties()
}

class GetPropertyDetailLogic(
    private val repository: MapRepository
) {
    operator fun invoke(id: String): Flow<Property?> = repository.getPropertyById(id)
}

class GetZonesLogic(
    private val repository: MapRepository
) {
    operator fun invoke(): Flow<List<ZoneSafety>> = repository.getZones()
}

class GetConditionReportLogic(
    private val repository: MapRepository
) {
    operator fun invoke(propertyId: String): Flow<ConditionReport> = repository.getConditionReport(propertyId)
}

class GetTitleValidationLogic(
    private val repository: MapRepository
) {
    operator fun invoke(propertyId: String): Flow<TitleValidation> = repository.getTitleValidation(propertyId)
}

class ToggleFavoriteLogic(
    private val repository: MapRepository
) {
    suspend operator fun invoke(propertyId: String) = repository.toggleFavorite(propertyId)
}
