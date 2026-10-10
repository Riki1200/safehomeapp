package com.romeodev.safehomeapp.feature_assistant_data.repositories

import com.romeodev.safehomeapp.feature_assistant_domain.repositories.AssistantRepository
import kotlinx.coroutines.delay

class AssistantRepositoryImpl : AssistantRepository {
    override suspend fun askAssistant(prompt: String): String {
        delay(800)
        return when {
            prompt.contains("deposit", ignoreCase = true) ->
                "Under CDMX Civil Code Article 2448, the security deposit must be returned within 30 to 60 calendar days following the return of possession, provided all utility payments (SACMEX & CFE) and property conditions are settled."
            prompt.contains("maintenance", ignoreCase = true) ->
                "In this apartment (Colima 142), the $2,500 MXN maintenance fee is explicitly included in the advertised $28,000 MXN rent. The owner Andrea Salinas has verified zero outstanding building dues with the condominium committee."
            prompt.contains("notary", ignoreCase = true) || prompt.contains("deed", ignoreCase = true) ->
                "Deed #45,820 was verified via Notary Public #128 and matches Folio Real #9482019 in the Public Property Registry. There are zero encumbrances or mortgages on the title."
            else ->
                "SafeHome verification confirmed that the property title is 100% lien-free and Andrea Salinas is the verified sole owner. All lease templates generated here include our anti-fraud deposit escrow guarantee."
        }
    }
}
