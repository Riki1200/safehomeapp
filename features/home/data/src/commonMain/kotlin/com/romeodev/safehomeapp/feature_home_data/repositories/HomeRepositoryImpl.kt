package com.romeodev.safehomeapp.feature_home_data.repositories

import com.romeodev.safehomeapp.feature_home_domain.models.Appointment
import com.romeodev.safehomeapp.feature_home_domain.models.ChatMessage
import com.romeodev.safehomeapp.feature_home_domain.models.ConditionReport
import com.romeodev.safehomeapp.feature_home_domain.models.EphemeralChat
import com.romeodev.safehomeapp.feature_home_domain.models.Lead
import com.romeodev.safehomeapp.feature_home_domain.models.ListingType
import com.romeodev.safehomeapp.feature_home_domain.models.OwnerDashboardData
import com.romeodev.safehomeapp.feature_home_domain.models.Property
import com.romeodev.safehomeapp.feature_home_domain.models.PropertyOffer
import com.romeodev.safehomeapp.feature_home_domain.models.PropertyType
import com.romeodev.safehomeapp.feature_home_domain.models.TitleValidation
import com.romeodev.safehomeapp.feature_home_domain.models.ZoneSafety
import com.romeodev.safehomeapp.feature_home_domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl : HomeRepository {

    private val propertiesFlow = MutableStateFlow(
        listOf(
            Property(
                id = "prop-1",
                title = "Apartment with terrace",
                zone = "Roma Norte",
                city = "CDMX",
                address = "Colima 142, Roma Norte, Cuauhtémoc",
                price = 28000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 2,
                bathrooms = 2,
                areaM2 = 85,
                parkingSpots = 1,
                inspectionScore = 9.4,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                description = "Modern and bright 2-bedroom apartment with private terrace overlooking leafy Colima street. Certified zero structural defects, public deed verified with Notary 128."
            ),
            Property(
                id = "prop-2",
                title = "House with garden",
                zone = "Coyoacán",
                city = "CDMX",
                address = "Francisco Sosa 218, Coyoacán",
                price = 8950000,
                listingType = ListingType.SALE,
                propertyType = PropertyType.HOUSE,
                bedrooms = 3,
                bathrooms = 3,
                areaM2 = 240,
                parkingSpots = 2,
                inspectionScore = 9.6,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                description = "Charming colonial-style residence with private mature garden, high wood-beam ceilings, and solar water heating. Full legal deed check passed."
            ),
            Property(
                id = "prop-3",
                title = "Loft in Juárez",
                zone = "Juárez",
                city = "CDMX",
                address = "Havre 77, Juárez",
                price = 22000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.LOFT,
                bedrooms = 1,
                bathrooms = 1,
                areaM2 = 65,
                parkingSpots = 0,
                inspectionScore = 9.1,
                isVerified = true,
                ownerName = "Andrea Salinas",
                ownerTrustScore = 98,
                status = "Inspection booked · Mon Oct 12, 10:00"
            ),
            Property(
                id = "prop-4",
                title = "Penthouse in Condesa",
                zone = "Condesa",
                city = "CDMX",
                address = "Amsterdam 89, Hipódromo Condesa",
                price = 45000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 3,
                bathrooms = 3,
                areaM2 = 145,
                parkingSpots = 2,
                inspectionScore = 9.5,
                isVerified = true,
                ownerName = "Carlos Slim Jr.",
                ownerTrustScore = 95
            ),
            Property(
                id = "prop-5",
                title = "Design Studio in Polanco",
                zone = "Polanco",
                city = "CDMX",
                address = "Campos Elíseos 310, Polanco",
                price = 38000,
                listingType = ListingType.RENT,
                propertyType = PropertyType.APARTMENT,
                bedrooms = 1,
                bathrooms = 1,
                areaM2 = 70,
                parkingSpots = 1,
                inspectionScore = 9.8,
                isVerified = true,
                ownerName = "Elena Garza",
                ownerTrustScore = 99
            )
        )
    )

    private val zonesFlow = MutableStateFlow(
        listOf(
            ZoneSafety("Roma Norte", 94, "$28,500 MXN", 14),
            ZoneSafety("Condesa", 92, "$32,000 MXN", 18),
            ZoneSafety("Polanco", 96, "$48,000 MXN", 22),
            ZoneSafety("Juárez", 88, "$22,000 MXN", 9),
            ZoneSafety("Coyoacán", 91, "$26,000 MXN", 11)
        )
    )

    private val appointmentsFlow = MutableStateFlow(
        listOf(
            Appointment(
                id = "apt-1",
                propertyId = "prop-1",
                propertyTitle = "Apartment with terrace",
                date = "Thu, Oct 8",
                time = "16:00",
                hostName = "Andrea Salinas",
                isVideoCall = false,
                bookingCode = "SH-4821",
                status = "Confirmed",
                isUpcoming = true
            ),
            Appointment(
                id = "apt-2",
                propertyId = "prop-2",
                propertyTitle = "House with garden",
                date = "Sat, Oct 10",
                time = "11:30",
                hostName = "Andrea Salinas",
                isVideoCall = true,
                bookingCode = "SH-4933",
                status = "Confirmed",
                isUpcoming = true
            ),
            Appointment(
                id = "apt-3",
                propertyId = "prop-4",
                propertyTitle = "Penthouse in Condesa",
                date = "Tue, Sep 29",
                time = "15:00",
                hostName = "Carlos Slim Jr.",
                isVideoCall = false,
                bookingCode = "SH-3810",
                status = "Completed",
                isUpcoming = false
            )
        )
    )

    private val chatsFlow = MutableStateFlow(
        listOf(
            EphemeralChat(
                id = "chat-1",
                participantName = "Andrea Salinas",
                propertyTitle = "Apartment with terrace",
                lastMessage = "See you on Thursday at 4:00 PM outside Colima 142!",
                remainingHours = 27,
                remainingMinutes = 52,
                remainingSeconds = 20,
                isExpired = false,
                unreadCount = 1,
                messages = listOf(
                    ChatMessage("m1", "Andrea Salinas", false, "Hello Andrea! Excited to show you the apartment.", "10:15 AM"),
                    ChatMessage("m2", "You", true, "Hi Andrea! Does the building have 24/7 security concierge?", "10:18 AM"),
                    ChatMessage("m3", "Andrea Salinas", false, "Yes! 24/7 guarded lobby with CCTV, and the parking space is right by the elevator.", "10:20 AM"),
                    ChatMessage("m4", "Andrea Salinas", false, "See you on Thursday at 4:00 PM outside Colima 142!", "10:22 AM")
                )
            ),
            EphemeralChat(
                id = "chat-2",
                participantName = "Jorge Castañeda",
                propertyTitle = "House with garden",
                lastMessage = "Thanks for the mortgage pre-approval document.",
                remainingHours = 12,
                remainingMinutes = 15,
                remainingSeconds = 44,
                isExpired = false,
                unreadCount = 0,
                messages = listOf(
                    ChatMessage("m1", "Jorge Castañeda", false, "Hello Andrea, I submitted the mortgage pre-approval from BBVA.", "Yesterday"),
                    ChatMessage("m2", "You", true, "Thanks for the mortgage pre-approval document. It looks solid.", "Yesterday")
                )
            ),
            EphemeralChat(
                id = "chat-3",
                participantName = "Daniel Ortega",
                propertyTitle = "Loft in Juárez",
                lastMessage = "Viewing completed. Chat expired.",
                remainingHours = 0,
                remainingMinutes = 0,
                remainingSeconds = 0,
                isExpired = true,
                unreadCount = 0,
                messages = emptyList()
            )
        )
    )

    private val leadsFlow = MutableStateFlow(
        listOf(
            Lead("ld-1", "LM", "Laura Méndez", 96, "House with garden", "Offered $9.1M", "Offered", "2 h ago"),
            Lead("ld-2", "JC", "Jorge Castañeda", 90, "Apartment with terrace", "Viewing booked Thu 8", "Booked", "4 h ago"),
            Lead("ld-3", "SR", "Sofía Rangel", 88, "House with garden", "Offered $9.0M", "Offered", "Yesterday"),
            Lead("ld-4", "PI", "Paola Ibarra", 82, "Apartment with terrace", "Requested video viewing", "New", "Yesterday"),
            Lead("ld-5", "DO", "Daniel Ortega", 92, "House with garden", "Visited Sat 3", "Visited", "4 d ago"),
            Lead("ld-6", "ER", "Emilio Rojas", 74, "Apartment with terrace", "Asked about pet policy", "New", "5 d ago")
        )
    )

    private val offersFlow = MutableStateFlow(
        listOf(
            PropertyOffer(
                id = "of-1",
                rank = 1,
                buyerName = "Laura Méndez",
                amount = 9100000,
                priceDiff = "+150,000 vs. price",
                paymentType = "Pre-approved mortgage",
                proofOfFunds = "Pre-approval letter verified",
                estimatedClosingDays = 45,
                visitedProperty = true,
                buyerTrustScore = 96,
                isRecommended = true
            ),
            PropertyOffer(
                id = "of-2",
                rank = 2,
                buyerName = "Daniel Ortega",
                amount = 8800000,
                priceDiff = "-150,000 vs. price",
                paymentType = "Cash",
                proofOfFunds = "Bank statement verified",
                estimatedClosingDays = 20,
                visitedProperty = true,
                buyerTrustScore = 92,
                isRecommended = false
            ),
            PropertyOffer(
                id = "of-3",
                rank = 3,
                buyerName = "Sofía Rangel",
                amount = 9000000,
                priceDiff = "+50,000 vs. price",
                paymentType = "Mortgage + Cash",
                proofOfFunds = "Pending appraisal",
                estimatedClosingDays = 60,
                visitedProperty = false,
                buyerTrustScore = 88,
                isRecommended = false
            )
        )
    )

    override fun getProperties(): Flow<List<Property>> = propertiesFlow.asStateFlow()

    override fun getPropertyById(id: String): Flow<Property?> =
        propertiesFlow.map { list -> list.find { it.id == id } ?: list.firstOrNull() }

    override fun getZones(): Flow<List<ZoneSafety>> = zonesFlow.asStateFlow()

    override fun getConditionReport(propertyId: String): Flow<ConditionReport> =
        MutableStateFlow(ConditionReport(propertyId = propertyId)).asStateFlow()

    override fun getTitleValidation(propertyId: String): Flow<TitleValidation> =
        MutableStateFlow(TitleValidation(propertyId = propertyId)).asStateFlow()

    override fun getAppointments(): Flow<List<Appointment>> = appointmentsFlow.asStateFlow()

    override suspend fun bookAppointment(
        propertyId: String,
        date: String,
        time: String,
        isVideoCall: Boolean
    ): Result<Appointment> {
        val newAppointment = Appointment(
            id = "apt-${appointmentsFlow.value.size + 1}",
            propertyId = propertyId,
            propertyTitle = "Apartment with terrace",
            date = date,
            time = time,
            hostName = "Andrea Salinas",
            isVideoCall = isVideoCall,
            bookingCode = "SH-4821",
            status = "Confirmed",
            isUpcoming = true
        )
        appointmentsFlow.value = listOf(newAppointment) + appointmentsFlow.value
        return Result.success(newAppointment)
    }

    override fun getChats(): Flow<List<EphemeralChat>> = chatsFlow.asStateFlow()

    override fun getChatById(id: String): Flow<EphemeralChat?> =
        chatsFlow.map { list -> list.find { it.id == id } ?: list.firstOrNull() }

    override suspend fun sendMessage(chatId: String, messageText: String): Result<Unit> {
        val currentList = chatsFlow.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == chatId }
        if (index != -1) {
            val chat = currentList[index]
            val newMsg = ChatMessage(
                id = "m-${chat.messages.size + 1}",
                senderName = "You",
                isFromMe = true,
                text = messageText,
                timestamp = "Just now"
            )
            currentList[index] = chat.copy(
                lastMessage = messageText,
                messages = chat.messages + newMsg
            )
            chatsFlow.value = currentList
        }
        return Result.success(Unit)
    }

    override suspend fun reportScam(reason: String, details: String): Result<Unit> {
        return Result.success(Unit)
    }

    override fun getOwnerDashboard(): Flow<OwnerDashboardData> =
        MutableStateFlow(OwnerDashboardData()).asStateFlow()

    override fun getMyProperties(): Flow<List<Property>> = propertiesFlow.asStateFlow()

    override fun getLeads(): Flow<List<Lead>> = leadsFlow.asStateFlow()

    override fun getOffers(propertyId: String): Flow<List<PropertyOffer>> = offersFlow.asStateFlow()

    override suspend fun publishProperty(property: Property): Result<Unit> {
        propertiesFlow.value = listOf(property) + propertiesFlow.value
        return Result.success(Unit)
    }
}
