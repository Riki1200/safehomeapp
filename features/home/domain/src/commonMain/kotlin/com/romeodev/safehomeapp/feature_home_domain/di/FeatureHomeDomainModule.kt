/*
 *
 *  *
 *  *  * Copyright (c) 2026
 *  *  *
 *  *  * Author: Athar Gul
 *  *  * GitHub: https://github.com/DevAtrii/Kmp-Starter-Template
 *  *  * YouTube: https://www.youtube.com/@devatrii/videos
 *  *  *
 *  *  * All rights reserved.
 *  *
 *  *
 *
 */

package com.romeodev.safehomeapp.feature_home_domain.di

import com.romeodev.safehomeapp.feature_home_domain.logics.BookAppointmentLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetAppointmentsLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetChatConversationLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetChatsLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetConditionReportLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetLeadsLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetMyPropertiesLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetOffersLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetOwnerDashboardLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetPropertiesLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetPropertyDetailLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetTitleValidationLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.GetZonesLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.PublishPropertyLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.ReportScamLogic
import com.romeodev.safehomeapp.feature_home_domain.logics.SendMessageLogic
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val featureHomeDomainModule = module {
    factoryOf(::GetPropertiesLogic)
    factoryOf(::GetPropertyDetailLogic)
    factoryOf(::GetZonesLogic)
    factoryOf(::GetConditionReportLogic)
    factoryOf(::GetTitleValidationLogic)
    factoryOf(::GetAppointmentsLogic)
    factoryOf(::BookAppointmentLogic)
    factoryOf(::GetChatsLogic)
    factoryOf(::GetChatConversationLogic)
    factoryOf(::SendMessageLogic)
    factoryOf(::ReportScamLogic)
    factoryOf(::GetOwnerDashboardLogic)
    factoryOf(::GetMyPropertiesLogic)
    factoryOf(::GetLeadsLogic)
    factoryOf(::GetOffersLogic)
    factoryOf(::PublishPropertyLogic)
}