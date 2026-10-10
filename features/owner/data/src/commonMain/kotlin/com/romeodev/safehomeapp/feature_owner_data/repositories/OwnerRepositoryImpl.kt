package com.romeodev.safehomeapp.feature_owner_data.repositories

import com.romeodev.safehomeapp.feature_core_domain.models.Lead
import com.romeodev.safehomeapp.feature_core_domain.models.ListingType
import com.romeodev.safehomeapp.feature_core_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyType
import com.romeodev.safehomeapp.feature_owner_domain.repositories.OwnerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OwnerRepositoryImpl : OwnerRepository {

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
            )
        )
    )

    private val leadsFlow = MutableStateFlow(
        listOf(
            Lead("ld-1", "LM", "Laura Méndez", 96, "House with garden", "Offered $9.1M", "Offered", "2 h ago"),
            Lead("ld-2", "JC", "Jorge Castañeda", 90, "Apartment with terrace", "Viewing booked Thu 8", "Booked", "4 h ago"),
            Lead("ld-3", "SR", "Sofía Rangel", 88, "House with garden", "Offered $9.0M", "Offered", "Yesterday"),
            Lead("ld-4", "PI", "Paola Ibarra", 82, "Apartment with terrace", "Requested video viewing", "New", "Yesterday"),
            Lead("ld-5", "DO", "Daniel Ortega", 92, "House with garden", "Visited Sat 3", "Visited", "4 d ago"),
            Lead("ld-6", "ER", "Emilio Rojas", 74, "Apartment with terrace", "Asked about pet policy", "New", "5 d ago")
        )
    )

    private val offersFlow = MutableStateFlow(
        listOf(
            PropertyOffer(
                id = "of-1",
                rank = 1,
                buyerName = "Laura Méndez",
                amount = 9100000,
                priceDiff = "+150,000 vs. price",
                paymentType = "Pre-approved mortgage",
                proofOfFunds = "Pre-approval letter verified",
                estimatedClosingDays = 45,
                visitedProperty = true,
                buyerTrustScore = 96,
                isRecommended = true
            ),
            PropertyOffer(
                id = "of-2",
                rank = 2,
                buyerName = "Daniel Ortega",
                amount = 8800000,
                priceDiff = "-150,000 vs. price",
                paymentType = "Cash",
                proofOfFunds = "Bank statement verified",
                estimatedClosingDays = 20,
                visitedProperty = true,
                buyerTrustScore = 92,
                isRecommended = false
            ),
            PropertyOffer(
                id = "of-3",
                rank = 3,
                buyerName = "Sofía Rangel",
                amount = 9000000,
                priceDiff = "+50,000 vs. price",
                paymentType = "Mortgage + Cash",
                proofOfFunds = "Pending appraisal",
                estimatedClosingDays = 60,
                visitedProperty = false,
                buyerTrustScore = 88,
                isRecommended = false
            )
        )
    )

    override fun getOwnerDashboard(): Flow<OwnerDashboardData> =
        MutableStateFlow(OwnerDashboardData()).asStateFlow()

    override fun getMyProperties(): Flow<List<Property>> = propertiesFlow.asStateFlow()

    override fun getLeads(): Flow<List<Lead>> = leadsFlow.asStateFlow()

    override fun getOffers(propertyId: String): Flow<List<PropertyOffer>> = offersFlow.asStateFlow()

    override suspend fun publishProperty(property: Property): Result<Unit> {
        propertiesFlow.value = listOf(property) + propertiesFlow.value
        return Result.success(Unit)
    }
}
