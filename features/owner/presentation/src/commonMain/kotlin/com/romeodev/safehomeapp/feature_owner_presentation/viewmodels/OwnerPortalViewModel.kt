package com.romeodev.safehomeapp.feature_owner_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_core_domain.models.Property
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetLeadsLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetMyPropertiesLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetOffersLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetOwnerDashboardLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.PublishPropertyLogic
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalAction
import com.romeodev.safehomeapp.feature_owner_presentation.events.OwnerPortalEvent
import com.romeodev.safehomeapp.feature_owner_presentation.states.OwnerPortalUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OwnerPortalViewModel(
    private val getOwnerDashboardLogic: GetOwnerDashboardLogic,
    private val getMyPropertiesLogic: GetMyPropertiesLogic,
    private val getLeadsLogic: GetLeadsLogic,
    private val getOffersLogic: GetOffersLogic,
    private val publishPropertyLogic: PublishPropertyLogic
) : MviViewModel<OwnerPortalUiState, OwnerPortalAction, OwnerPortalEvent>() {

    override val initialState: OwnerPortalUiState
        get() = OwnerPortalUiState()

    init {
        loadData()
    }

    private fun loadData() {
        getOwnerDashboardLogic().onEach { dash ->
            _state.update { it.copy(dashboardData = dash) }
        }.launchIn(viewModelScope)

        getMyPropertiesLogic().onEach { list ->
            _state.update { it.copy(myProperties = list) }
        }.launchIn(viewModelScope)

        getLeadsLogic().onEach { list ->
            _state.update { it.copy(leads = list, filteredLeads = list) }
        }.launchIn(viewModelScope)

        getOffersLogic("prop-2").onEach { list ->
            _state.update { it.copy(offers = list) }
        }.launchIn(viewModelScope)
    }

    override fun onAction(action: OwnerPortalAction) {
        when (action) {
            is OwnerPortalAction.SelectLeadFilter -> {
                _state.update { state ->
                    val filtered = if (action.filter == "All") state.leads else {
                        state.leads.filter { it.statusTag.equals(action.filter, ignoreCase = true) }
                    }
                    state.copy(selectedLeadFilter = action.filter, filteredLeads = filtered)
                }
            }
            is OwnerPortalAction.SortOffers -> {
                _state.update { state ->
                    val sorted = when (action.sortBy) {
                        "Amount" -> state.offers.sortedByDescending { it.amount }
                        "Trust" -> state.offers.sortedByDescending { it.buyerTrustScore }
                        else -> state.offers.sortedBy { it.rank }
                    }
                    state.copy(selectedOfferSort = action.sortBy, offers = sorted)
                }
            }
            is OwnerPortalAction.SetListingType -> {
                _state.update { it.copy(listingType = action.type) }
            }
            is OwnerPortalAction.SetPropertyType -> {
                _state.update { it.copy(propertyType = action.type) }
            }
            is OwnerPortalAction.UpdatePrice -> {
                _state.update { it.copy(priceInput = action.price) }
            }
            is OwnerPortalAction.UpdateBedrooms -> {
                _state.update { it.copy(bedrooms = action.count) }
            }
            is OwnerPortalAction.UpdateBathrooms -> {
                _state.update { it.copy(bathrooms = action.count) }
            }
            is OwnerPortalAction.UpdateArea -> {
                _state.update { it.copy(areaM2 = action.area) }
            }
            OwnerPortalAction.SubmitListing -> {
                viewModelScope.launch {
                    _state.update { it.copy(isPublishing = true) }
                    val parsedPrice = _state.value.priceInput.filter { it.isDigit() }.toLongOrNull() ?: 25000L
                    val newProp = Property(
                        id = "prop-${kotlin.random.Random.nextInt(1000, 9999)}",
                        title = "${_state.value.propertyType.label} in Roma Norte",
                        zone = "Roma Norte",
                        city = "CDMX",
                        address = "Álvaro Obregón 160",
                        price = parsedPrice,
                        listingType = _state.value.listingType,
                        propertyType = _state.value.propertyType,
                        bedrooms = _state.value.bedrooms,
                        bathrooms = _state.value.bathrooms,
                        areaM2 = _state.value.areaM2,
                        inspectionScore = 9.5
                    )
                    publishPropertyLogic(newProp)
                    _state.update { it.copy(isPublishing = false) }
                    emitEvent(OwnerPortalEvent.ListingSubmitted)
                }
            }
        }
    }
}
