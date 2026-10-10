package com.romeodev.safehomeapp.feature_map_data.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_core_domain.models.ListingType
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyType
import com.romeodev.safehomeapp.feature_core_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_core_domain.models.ZoneSafety
import com.romeodev.safehomeapp.feature_map_domain.repositories.MapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class MapRepositoryImpl : MapRepository {

    private val propertiesFlow = MutableStateFlow(
        listOf(
            Property(
                id = "prop-1",
                title = "Apartment with terrace",
                zone = "Roma Norte",
                city = "CDMX",
                address = "Colima 142, Roma Norte, Cuauhtémoc",
                price = 28000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 2,
                bathrooms = 2,
                areaM2 = 85,
                parkingSpots = 1,
                inspectionScore = 9.4,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                description = "Modern and bright 2-bedroom apartment with private terrace overlooking leafy Colima street. Certified zero structural defects, public deed verified with Notary 128."
            ),
            Property(
                id = "prop-2",
                title = "House with garden",
                zone = "Coyoacán",
                city = "CDMX",
                address = "Francisco Sosa 218, Coyoacán",
                price = 8950000,
                listingType = ListingType.SALE,
                propertyType = PropertyType.HOUSE,
                bedrooms = 3,
                bathrooms = 3,
                areaM2 = 240,
                parkingSpots = 2,
                inspectionScore = 9.6,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                description = "Charming colonial-style residence with private mature garden, high wood-beam ceilings, and solar water heating. Full legal deed check passed."
            ),
            Property(
                id = "prop-3",
                title = "Loft in Juárez",
                zone = "Juárez",
                city = "CDMX",
                address = "Havre 77, Juárez",
                price = 22000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.LOFT,
                bedrooms = 1,
                bathrooms = 1,
                areaM2 = 65,
                parkingSpots = 0,
                inspectionScore = 9.1,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                status = "Inspection booked · Mon Oct 12, 10:00"
            ),
            Property(
                id = "prop-4",
                title = "Penthouse in Condesa",
                zone = "Condesa",
                city = "CDMX",
                address = "Amsterdam 89, Hipódromo Condesa",
                price = 45000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 3,
                bathrooms = 3,
                areaM2 = 145,
                parkingSpots = 2,
                inspectionScore = 9.5,
                isVerified = true,
                ownerName = "Carlos Slim Jr.",
                ownerTrustScore = 95
            ),
            Property(
                id = "prop-5",
                title = "Design Studio in Polanco",
                zone = "Polanco",
                city = "CDMX",
                address = "Campos Elíseos 310, Polanco",
                price = 38000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 1,
                bathrooms = 1,
                areaM2 = 70,
                parkingSpots = 1,
                inspectionScore = 9.8,
                isVerified = true,
                ownerName = "Elena Garza",
                ownerTrustScore = 99
            )
        )
    )

    private val zonesFlow = MutableStateFlow(
        listOf(
            ZoneSafety("Roma Norte", 94, "$28,500 MXN", 14),
            ZoneSafety("Condesa", 92, "$32,000 MXN", 18),
            ZoneSafety("Polanco", 96, "$48,000 MXN", 22),
            ZoneSafety("Juárez", 88, "$22,000 MXN", 9),
            ZoneSafety("Coyoacán", 91, "$26,000 MXN", 11)
        )
    )

    override fun getProperties(): Flow<List<Property>> = propertiesFlow.asStateFlow()

    override fun getPropertyById(id: String): Flow<Property?> =
        propertiesFlow.map { list -> list.find { it.id == id } ?: list.firstOrNull() }

    override fun getZones(): Flow<List<ZoneSafety>> = zonesFlow.asStateFlow()

    override fun getConditionReport(propertyId: String): Flow<ConditionReport> =
        MutableStateFlow(ConditionReport(propertyId = propertyId)).asStateFlow()

    override fun getTitleValidation(propertyId: String): Flow<TitleValidation> =
        MutableStateFlow(TitleValidation(propertyId = propertyId)).asStateFlow()

    override suspend fun toggleFavorite(propertyId: String) {
        propertiesFlow.update { list ->
            list.map { if (it.id == propertyId) it.copy(isFavorite = !it.isFavorite) else it }
        }
    }
}
