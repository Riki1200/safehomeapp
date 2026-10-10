package com.romeodev.safehomeapp.feature_owner_domain.di

import com.romeodev.safehomeapp.feature_owner_domain.logics.GetLeadsLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetMyPropertiesLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetOffersLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.GetOwnerDashboardLogic
import com.romeodev.safehomeapp.feature_owner_domain.logics.PublishPropertyLogic
import org.koin.dsl.module

val ownerDomainModule = module {
    factory { GetOwnerDashboardLogic(get()) }
    factory { GetMyPropertiesLogic(get()) }
    factory { GetLeadsLogic(get()) }
    factory { GetOffersLogic(get()) }
    factory { PublishPropertyLogic(get()) }
}
