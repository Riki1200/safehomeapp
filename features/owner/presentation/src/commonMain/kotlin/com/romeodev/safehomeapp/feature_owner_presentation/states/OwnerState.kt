package com.romeodev.safehomeapp.feature_owner_presentation.states

import com.romeodev.safehomeapp.feature_core_domain.models.Lead
import com.romeodev.safehomeapp.feature_core_domain.models.ListingType
import com.romeodev.safehomeapp.feature_core_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyType

data class OwnerPortalUiState(
    val dashboardData: OwnerDashboardData = OwnerDashboardData(),
    val myProperties: List<Property> = emptyList(),
    val leads: List<Lead> = emptyList(),
    val filteredLeads: List<Lead> = emptyList(),
    val selectedLeadFilter: String = "All",
    val offers: List<PropertyOffer> = emptyList(),
    val selectedOfferSort: String = "Score",
    val listingType: ListingType = ListingType.RENT,
    val propertyType: PropertyType = PropertyType.APARTMENT,
    val priceInput: String = "0",
    val bedrooms: Int = 2,
    val bathrooms: Int = 1,
    val areaM2: Int = 80,
    val isPublishing: Boolean = false
)
