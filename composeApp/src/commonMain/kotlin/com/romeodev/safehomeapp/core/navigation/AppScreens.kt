package com.romeodev.safehomeapp.core.navigation

import androidx.navigation3.runtime.NavKey
import com.romeodev.safehomeapp.feature_navigation.StarterNavigator
import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreens : NavKey {
    @Serializable
    data object Welcome : AppScreens()

    @Serializable
    data object SignIn : AppScreens()

    @Serializable
    data object SignUp : AppScreens()

    @Serializable
    data object VerificationStart : AppScreens()

    @Serializable
    data object IdCapture : AppScreens()

    @Serializable
    data object FaceScan : AppScreens()

    @Serializable
    data object VerificationSuccess : AppScreens()

    @Serializable
    data object MainRoot : AppScreens()

    @Serializable
    data object ExploreResults : AppScreens()

    @Serializable
    data class PropertyDetail(val propertyId: String) : AppScreens()

    @Serializable
    data class ConditionReport(val propertyId: String) : AppScreens()

    @Serializable
    data class TitleValidation(val propertyId: String) : AppScreens()

    @Serializable
    data class BookViewing(val propertyId: String) : AppScreens()

    @Serializable
    data class ViewingConfirmed(
        val bookingCode: String,
        val propertyTitle: String,
        val dateTime: String
    ) : AppScreens()

    @Serializable
    data class ChatConversation(val chatId: String) : AppScreens()

    @Serializable
    data class ReportScam(val targetId: String = "") : AppScreens()

    @Serializable
    data object PublishProperty : AppScreens()

    @Serializable
    data object InspectionSubmitted : AppScreens()

    @Serializable
    data class Offers(val propertyId: String = "") : AppScreens()

    @Serializable
    data object Language : AppScreens()

    @Serializable
    data object Splash : AppScreens()

    @Serializable
    data object Onboarding : AppScreens()

    @Serializable
    data class Purchases(val onNavigate: (StarterNavigator) -> Unit = { it.navigateUp() }) :
        AppScreens()
}