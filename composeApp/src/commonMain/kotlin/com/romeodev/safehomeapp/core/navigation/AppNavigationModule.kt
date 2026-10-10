package com.romeodev.safehomeapp.core.navigation

import androidx.compose.runtime.rememberCoroutineScope
import com.romeodev.safehomeapp.feature_analytics_domain.EventsTracker
import com.romeodev.safehomeapp.feature_core_domain.AppEvents
import com.romeodev.safehomeapp.feature_core_presentation.screens.OnboardingV1Screen
import com.romeodev.safehomeapp.feature_core_presentation.screens.SplashScreen
import com.romeodev.safehomeapp.feature_navigation.StarterNavigator
import com.romeodev.safehomeapp.feature_navigation.di.navigationCoreModule
import com.romeodev.safehomeapp.feature_purchases_presentation.ui.screens.PurchasesScreen
import com.romeodev.safehomeapp.feature_auth_presentation.ui.SignInScreen
import com.romeodev.safehomeapp.feature_auth_presentation.ui.SignUpScreen
import com.romeodev.safehomeapp.feature_auth_presentation.ui.WelcomeScreen
import com.romeodev.safehomeapp.feature_verification_presentation.ui.FaceScanScreen
import com.romeodev.safehomeapp.feature_verification_presentation.ui.IdCaptureScreen
import com.romeodev.safehomeapp.feature_verification_presentation.ui.VerificationStartScreen
import com.romeodev.safehomeapp.feature_verification_presentation.ui.VerificationSuccessScreen
import com.romeodev.safehomeapp.feature_appointments_presentation.ui.BookViewingScreen
import com.romeodev.safehomeapp.feature_appointments_presentation.ui.ViewingConfirmedScreen
import com.romeodev.safehomeapp.feature_chat_presentation.ui.ChatConversationScreen
import com.romeodev.safehomeapp.feature_chat_presentation.ui.ReportScamScreen
import com.romeodev.safehomeapp.feature_map_presentation.ui.ConditionReportScreen
import com.romeodev.safehomeapp.feature_map_presentation.ui.ExploreResultsScreen
import com.romeodev.safehomeapp.feature_map_presentation.ui.PropertyDetailScreen
import com.romeodev.safehomeapp.feature_map_presentation.ui.TitleValidationScreen
import com.romeodev.safehomeapp.feature_home_presentation.ui.MainRootScreen
import com.romeodev.safehomeapp.feature_owner_presentation.ui.InspectionSubmittedScreen
import com.romeodev.safehomeapp.feature_owner_presentation.ui.OffersScreen
import com.romeodev.safehomeapp.feature_owner_presentation.ui.PublishPropertyScreen
import com.romeodev.safehomeapp.feature_profile_presentation.ui.LanguageScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val appNavigationModule = module {
    includes(navigationCoreModule)

    navigation<AppScreens.Welcome> { route ->
        val navigator = StarterNavigator.getCurrent()
        WelcomeScreen(
            onNavigateToSignIn = {
                navigator.navigateTo(AppScreens.SignIn)
            },
            onNavigateToSignUp = {
                navigator.navigateTo(AppScreens.SignUp)
            }
        )
    }

    navigation<AppScreens.SignIn> { route ->
        val navigator = StarterNavigator.getCurrent()
        SignInScreen(
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToSignUp = {
                navigator.navigateTo(AppScreens.SignUp)
            },
            onSignInSuccess = {
                navigator.popAllAndNavigate(AppScreens.MainRoot)
            }
        )
    }

    navigation<AppScreens.SignUp> { route ->
        val navigator = StarterNavigator.getCurrent()
        SignUpScreen(
            onNavigateBack = {
                navigator.navigateUp()
            },
            onVerificationSuccess = {
                navigator.navigateTo(AppScreens.VerificationStart)
            }
        )
    }

    navigation<AppScreens.VerificationStart> { route ->
        val navigator = StarterNavigator.getCurrent()
        VerificationStartScreen(
            onNavigateBack = {
                navigator.navigateUp()
            },
            onStartVerification = {
                navigator.navigateTo(AppScreens.IdCapture)
            }
        )
    }

    navigation<AppScreens.IdCapture> { route ->
        val navigator = StarterNavigator.getCurrent()
        IdCaptureScreen(
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToFaceScan = {
                navigator.navigateTo(AppScreens.FaceScan)
            }
        )
    }

    navigation<AppScreens.FaceScan> { route ->
        val navigator = StarterNavigator.getCurrent()
        FaceScanScreen(
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToSuccess = {
                navigator.navigateTo(AppScreens.VerificationSuccess)
            }
        )
    }

    navigation<AppScreens.VerificationSuccess> { route ->
        val navigator = StarterNavigator.getCurrent()
        VerificationSuccessScreen(
            onContinueToHome = {
                navigator.popAllAndNavigate(AppScreens.MainRoot)
            }
        )
    }

    navigation<AppScreens.MainRoot> { route ->
        val navigator = StarterNavigator.getCurrent()
        MainRootScreen(
            onNavigateToResults = {
                navigator.navigateTo(AppScreens.ExploreResults)
            },
            onNavigateToPropertyDetail = { propertyId ->
                navigator.navigateTo(AppScreens.PropertyDetail(propertyId))
            },
            onNavigateToChat = { chatId ->
                navigator.navigateTo(AppScreens.ChatConversation(chatId))
            },
            onNavigateToPublish = {
                navigator.navigateTo(AppScreens.PublishProperty)
            },
            onNavigateToOffers = { propertyId ->
                navigator.navigateTo(AppScreens.Offers(propertyId))
            },
            onNavigateToLanguage = {
                navigator.navigateTo(AppScreens.Language)
            },
            onNavigateToReportScam = {
                navigator.navigateTo(AppScreens.ReportScam())
            },
            onSignOut = {
                navigator.popAllAndNavigate(AppScreens.Welcome)
            }
        )
    }

    navigation<AppScreens.ExploreResults> { route ->
        val navigator = StarterNavigator.getCurrent()
        ExploreResultsScreen(
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToDetail = { propertyId ->
                navigator.navigateTo(AppScreens.PropertyDetail(propertyId))
            }
        )
    }

    navigation<AppScreens.PropertyDetail> { route ->
        val navigator = StarterNavigator.getCurrent()
        PropertyDetailScreen(
            propertyId = route.propertyId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToConditionReport = { id ->
                navigator.navigateTo(AppScreens.ConditionReport(id))
            },
            onNavigateToTitleValidation = { id ->
                navigator.navigateTo(AppScreens.TitleValidation(id))
            },
            onNavigateToBookViewing = { id ->
                navigator.navigateTo(AppScreens.BookViewing(id))
            },
            onNavigateToChat = { chatId ->
                navigator.navigateTo(AppScreens.ChatConversation(chatId))
            }
        )
    }

    navigation<AppScreens.ConditionReport> { route ->
        val navigator = StarterNavigator.getCurrent()
        ConditionReportScreen(
            propertyId = route.propertyId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            }
        )
    }

    navigation<AppScreens.TitleValidation> { route ->
        val navigator = StarterNavigator.getCurrent()
        TitleValidationScreen(
            propertyId = route.propertyId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            }
        )
    }

    navigation<AppScreens.BookViewing> { route ->
        val navigator = StarterNavigator.getCurrent()
        BookViewingScreen(
            propertyId = route.propertyId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            },
            onViewingConfirmed = { code, title, dateTime ->
                navigator.popAndNavigate(AppScreens.ViewingConfirmed(code, title, dateTime))
            }
        )
    }

    navigation<AppScreens.ViewingConfirmed> { route ->
        val navigator = StarterNavigator.getCurrent()
        ViewingConfirmedScreen(
            bookingCode = route.bookingCode,
            propertyTitle = route.propertyTitle,
            dateTime = route.dateTime,
            onNavigateToAppointments = {
                navigator.popAllAndNavigate(AppScreens.MainRoot)
            }
        )
    }

    navigation<AppScreens.ChatConversation> { route ->
        val navigator = StarterNavigator.getCurrent()
        ChatConversationScreen(
            chatId = route.chatId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            },
            onNavigateToReportScam = { id ->
                navigator.navigateTo(AppScreens.ReportScam(id))
            }
        )
    }

    navigation<AppScreens.ReportScam> { route ->
        val navigator = StarterNavigator.getCurrent()
        ReportScamScreen(
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            }
        )
    }

    navigation<AppScreens.PublishProperty> { route ->
        val navigator = StarterNavigator.getCurrent()
        PublishPropertyScreen(
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            },
            onListingSubmitted = {
                navigator.popAndNavigate(AppScreens.InspectionSubmitted)
            }
        )
    }

    navigation<AppScreens.InspectionSubmitted> { route ->
        val navigator = StarterNavigator.getCurrent()
        InspectionSubmittedScreen(
            onNavigateToMyProperties = {
                navigator.popAllAndNavigate(AppScreens.MainRoot)
            }
        )
    }

    navigation<AppScreens.Offers> { route ->
        val navigator = StarterNavigator.getCurrent()
        OffersScreen(
            propertyId = route.propertyId,
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            }
        )
    }

    navigation<AppScreens.Language> { route ->
        val navigator = StarterNavigator.getCurrent()
        LanguageScreen(
            viewModel = koinViewModel(),
            onNavigateBack = {
                navigator.navigateUp()
            }
        )
    }

    navigation<AppScreens.Splash> { route ->
        val navigator = StarterNavigator.getCurrent()
        SplashScreen(
            onNavigate = {
                navigator.popAndNavigate(
                    route = AppScreens.Welcome
                )
            },
            onNavigateToOnboarding = {
                navigator.popAndNavigate(
                    route = AppScreens.Onboarding
                )
            }
        )
    }

    navigation<AppScreens.Onboarding> { route ->
        val navigator = StarterNavigator.getCurrent()
        OnboardingV1Screen(
            onNavigate = {
                navigator.popAndNavigate(
                    route = AppScreens.Welcome
                )
            }
        )
    }

    navigation<AppScreens.Purchases> { route ->
        val navigator = StarterNavigator.getCurrent()
        val eventsTracker: EventsTracker = koinInject()
        val scope = rememberCoroutineScope()
        PurchasesScreen(
            paywall = starterDefaultPaywallV1(),
            onNavigate = {
                route.onNavigate.invoke(navigator)
            },
            onProductsLoadFailure = {
                scope.launch {
                    eventsTracker.track(
                        event = AppEvents.OnPurchaseProductsLoadFailure(
                            error = it.message ?: "--"
                        )
                    )
                }
            },
            onPurchaseFailure = { err, id ->
                scope.launch {
                    eventsTracker.track(
                        event = AppEvents.OnPurchaseFailure(
                            error = err.message ?: "--",
                            productId = id
                        )
                    )
                }
            },
            onRestoreFailure = {
                scope.launch {
                    eventsTracker.track(
                        event = AppEvents.OnPurchaseRestoreFailure(
                            error = it.message ?: "--"
                        )
                    )
                }
            },
            onPurchaseSuccess = { trx ->
                scope.launch {
                    eventsTracker.track(
                        event = AppEvents.OnPurchaseSuccess(
                            transactionId = trx.transactionId,
                            value = trx.amount,
                            currency = trx.currency,
                            itemId = trx.product.id
                        )
                    )
                }
            },
        )
    }
}
