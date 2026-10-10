package com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.romeodev.safehomeapp.feature_appointments_domain.logics.BookAppointmentLogic
import com.romeodev.safehomeapp.feature_appointments_domain.logics.GetAppointmentsLogic
import com.romeodev.safehomeapp.feature_appointments_presentation.events.AppointmentsAction
import com.romeodev.safehomeapp.feature_appointments_presentation.events.AppointmentsEvent
import com.romeodev.safehomeapp.feature_appointments_presentation.events.BookViewingAction
import com.romeodev.safehomeapp.feature_appointments_presentation.events.BookViewingEvent
import com.romeodev.safehomeapp.feature_appointments_presentation.states.AppointmentsUiState
import com.romeodev.safehomeapp.feature_appointments_presentation.states.BookViewingUiState
import com.romeodev.safehomeapp.ui_utils.viewmodels.MviViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    private val getAppointmentsLogic: GetAppointmentsLogic
) : MviViewModel<AppointmentsUiState, AppointmentsAction, AppointmentsEvent>() {

    override val initialState: AppointmentsUiState
        get() = AppointmentsUiState()

    init {
        getAppointmentsLogic().onEach { list ->
            _state.update { it.copy(appointments = list) }
        }.launchIn(viewModelScope)
    }

    override fun onAction(action: AppointmentsAction) {
        when (action) {
            is AppointmentsAction.SelectTab -> _state.update { it.copy(selectedTab = action.index) }
        }
    }
}

class BookViewingViewModel(
    private val bookAppointmentLogic: BookAppointmentLogic
) : MviViewModel<BookViewingUiState, BookViewingAction, BookViewingEvent>() {

    override val initialState: BookViewingUiState
        get() = BookViewingUiState()

    override fun onAction(action: BookViewingAction) {
        when (action) {
            is BookViewingAction.SelectDate -> _state.update { it.copy(selectedDate = action.date) }
            is BookViewingAction.SelectTime -> _state.update { it.copy(selectedTime = action.time) }
            is BookViewingAction.SetVideoCall -> _state.update { it.copy(isVideoCall = action.isVideo) }
            BookViewingAction.ConfirmBooking -> {
                viewModelScope.launch {
                    _state.update { it.copy(isBooking = true) }
                    val result = bookAppointmentLogic(
                        propertyId = _state.value.propertyId,
                        date = _state.value.selectedDate,
                        time = _state.value.selectedTime,
                        isVideoCall = _state.value.isVideoCall
                    )
                    _state.update { it.copy(isBooking = false) }
                    result.onSuccess { apt ->
                        emitEvent(
                            BookViewingEvent.BookingConfirmed(
                                code = apt.bookingCode,
                                propertyTitle = apt.propertyTitle,
                                dateTime = "${apt.date} · ${apt.time}"
                            )
                        )
                    }
                }
            }
        }
    }
}
