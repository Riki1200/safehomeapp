package com.romeodev.safehomeapp.feature_home_presentation.di

import com.romeodev.safehomeapp.feature_appointments_presentation.di.appointmentsPresentationModule
import com.romeodev.safehomeapp.feature_assistant_presentation.di.assistantPresentationModule
import com.romeodev.safehomeapp.feature_chat_presentation.di.chatPresentationModule
import com.romeodev.safehomeapp.feature_map_presentation.di.mapPresentationModule
import com.romeodev.safehomeapp.feature_owner_presentation.di.ownerPresentationModule
import com.romeodev.safehomeapp.feature_profile_presentation.di.profilePresentationModule
import org.koin.dsl.module

val featureHomePresentationModule = module {
    includes(
        mapPresentationModule,
        appointmentsPresentationModule,
        chatPresentationModule,
        assistantPresentationModule,
        ownerPresentationModule,
        profilePresentationModule
    )
}