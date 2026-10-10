package com.romeodev.safehomeapp.feature_map_domain.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_core_domain.models.ZoneSafety
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    fun getProperties(): Flow<List<Property>>
    fun getPropertyById(id: String): Flow<Property?>
    fun getZones(): Flow<List<ZoneSafety>>
    fun getConditionReport(propertyId: String): Flow<ConditionReport>
    fun getTitleValidation(propertyId: String): Flow<TitleValidation>
    suspend fun toggleFavorite(propertyId: String)
}
