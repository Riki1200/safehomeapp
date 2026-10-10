package com.romeodev.safehomeapp.feature_map_domain.di

import com.romeodev.safehomeapp.feature_map_domain.logics.GetConditionReportLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetPropertiesLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetPropertyDetailLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetTitleValidationLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.GetZonesLogic
import com.romeodev.safehomeapp.feature_map_domain.logics.ToggleFavoriteLogic
import org.koin.dsl.module

val mapDomainModule = module {
    factory { GetPropertiesLogic(get()) }
    factory { GetPropertyDetailLogic(get()) }
    factory { GetZonesLogic(get()) }
    factory { GetConditionReportLogic(get()) }
    factory { GetTitleValidationLogic(get()) }
    factory { ToggleFavoriteLogic(get()) }
}
