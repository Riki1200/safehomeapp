package com.romeodev.safehomeapp.feature_home_domain.models

import kotlinx.serialization.Serializable

@Serializable
enum class ListingType(val label: String) {
    RENT("Rent"),
    SALE("Sale")
}

@Serializable
enum class PropertyType(val label: String) {
    APARTMENT("Apartment"),
    HOUSE("House"),
    LOFT("Loft"),
    LAND("Land"),
    RETAIL("Retail")
}

@Serializable
data class Property(
    val id: String,
    val title: String,
    val zone: String,
    val city: String = "CDMX",
    val address: String,
    val price: Long,
    val currency: String = "MXN",
    val listingType: ListingType,
    val propertyType: PropertyType,
    val bedrooms: Int,
    val bathrooms: Int,
    val areaM2: Int,
    val parkingSpots: Int = 1,
    val inspectionScore: Double,
    val isVerified: Boolean = true,
    val isTitleVerified: Boolean = true,
    val isNoLien: Boolean = true,
    val ownerName: String = "Andrea Salinas",
    val ownerTrustScore: Int = 98,
    val description: String = "Stunning sunlit home with high ceilings, private terrace, and 24/7 security. Fully inspected and legal title verified with Public Registry notary deed.",
    val isFavorite: Boolean = false,
    val status: String = "Live"
)

@Serializable
data class ZoneSafety(
    val name: String,
    val safetyScore: Int,
    val avgRent: String,
    val verifiedCount: Int
)

@Serializable
data class InspectionCategory(
    val name: String,
    val score: Double,
    val status: String,
    val details: String
)

@Serializable
data class ConditionReport(
    val propertyId: String,
    val overallScore: Double = 9.2,
    val inspectorName: String = "Eng. Carlos Mendoza",
    val inspectorLicense: String = "CDMX-84920",
    val inspectionDate: String = "Oct 4, 2026",
    val categories: List<InspectionCategory> = listOf(
        InspectionCategory("Structure", 9.5, "Excellent", "No cracks, structural settlement or foundation issues detected."),
        InspectionCategory("Electrical", 9.0, "Verified Safe", "Modern 220V wiring, grounded outlets, GFCI installed in kitchen and baths."),
        InspectionCategory("Plumbing & Gas", 9.0, "Optimal", "Good water pressure (2.2 bar), zero leaks, certified copper piping."),
        InspectionCategory("Moisture & Waterproofing", 9.5, "Guaranteed", "0% excess humidity measured, roof membrane waterproofed in 2026."),
        InspectionCategory("Finishes & Carpentry", 8.8, "Good Condition", "Solid hardwood floors, minor cosmetic wear on secondary door.")
    )
)

@Serializable
data class TitleCheckItem(
    val title: String,
    val documentDetail: String,
    val isPassed: Boolean = true
)

@Serializable
data class TitleValidation(
    val propertyId: String,
    val notaryNumber: Int = 128,
    val notaryName: String = "Lic. Roberto González",
    val folioReal: String = "9482019",
    val verificationDate: String = "Sep 28, 2026",
    val checks: List<TitleCheckItem> = listOf(
        TitleCheckItem("Public Deed (Escritura Pública)", "Deed #45,820 · Notary Public #128, CDMX", true),
        TitleCheckItem("Public Property Registry (RPP)", "Folio Real #9482019 · Registered and free of dispute", true),
        TitleCheckItem("Lien-Free Certificate (Libertad de Gravamen)", "Issued Sep 28, 2026 · No mortgages, embargoes or liens", true),
        TitleCheckItem("Property Tax (Predial)", "Current through 2026 · Zero outstanding debt", true),
        TitleCheckItem("Water & Utilities (SACMEX)", "Paid and current", true),
        TitleCheckItem("Owner Identity Match", "Deed owner matches Andrea Salinas KYC (100% biometric match)", true)
    )
)

@Serializable
data class Appointment(
    val id: String,
    val propertyId: String,
    val propertyTitle: String,
    val date: String,
    val time: String,
    val hostName: String,
    val isVideoCall: Boolean,
    val bookingCode: String,
    val status: String = "Confirmed",
    val isUpcoming: Boolean = true
)

@Serializable
data class ChatMessage(
    val id: String,
    val senderName: String,
    val isFromMe: Boolean,
    val text: String,
    val timestamp: String
)

@Serializable
data class EphemeralChat(
    val id: String,
    val participantName: String,
    val propertyTitle: String,
    val lastMessage: String,
    val remainingHours: Int,
    val remainingMinutes: Int,
    val remainingSeconds: Int,
    val isExpired: Boolean = false,
    val unreadCount: Int = 0,
    val messages: List<ChatMessage> = emptyList()
)

@Serializable
data class Lead(
    val id: String,
    val initials: String,
    val name: String,
    val trustScore: Int,
    val propertyTitle: String,
    val actionText: String,
    val statusTag: String,
    val timestamp: String
)

@Serializable
data class PropertyOffer(
    val id: String,
    val rank: Int,
    val buyerName: String,
    val amount: Long,
    val priceDiff: String,
    val paymentType: String,
    val proofOfFunds: String,
    val estimatedClosingDays: Int,
    val visitedProperty: Boolean,
    val buyerTrustScore: Int,
    val isRecommended: Boolean = false
)

@Serializable
data class OwnerDashboardData(
    val views7d: Int = 312,
    val appointmentsCount: Int = 5,
    val offersCount: Int = 4,
    val bestBidderName: String = "Laura Méndez",
    val bestBidderAmount: String = "$9,100,000",
    val bestBidderProperty: String = "House with garden",
    val bestBidderTrust: Int = 96,
    val pendingDocumentsCount: Int = 2
)
