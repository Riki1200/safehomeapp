package com.romeodev.safehomeapp.feature_owner_presentation.events

import com.romeodev.safehomeapp.feature_core_domain.models.ListingType
import com.romeodev.safehomeapp.feature_core_domain.models.PropertyType

sealed interface OwnerPortalAction {
    data class SelectLeadFilter(val filter: String) : OwnerPortalAction
    data class SortOffers(val sortBy: String) : OwnerPortalAction
    data class SetListingType(val type: ListingType) : OwnerPortalAction
    data class SetPropertyType(val type: PropertyType) : OwnerPortalAction
    data class UpdatePrice(val price: String) : OwnerPortalAction
    data class UpdateBedrooms(val count: Int) : OwnerPortalAction
    data class UpdateBathrooms(val count: Int) : OwnerPortalAction
    data class UpdateArea(val area: Int) : OwnerPortalAction
    data object SubmitListing : OwnerPortalAction
}

sealed interface OwnerPortalEvent {
    data object ListingSubmitted : OwnerPortalEvent
}
