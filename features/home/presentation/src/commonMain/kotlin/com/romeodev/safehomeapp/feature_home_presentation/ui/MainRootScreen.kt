package com.romeodev.safehomeapp.feature_home_presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.romeodev.safehomeapp.feature_appointments_presentation.ui.MyAppointmentsScreen
import com.romeodev.safehomeapp.feature_appointments_presentation.viewmodels.AppointmentsViewModel
import com.romeodev.safehomeapp.feature_assistant_presentation.ui.AssistantScreen
import com.romeodev.safehomeapp.feature_assistant_presentation.viewmodels.AssistantViewModel
import com.romeodev.safehomeapp.feature_chat_presentation.ui.EphemeralChatsListScreen
import com.romeodev.safehomeapp.feature_chat_presentation.viewmodels.ChatsViewModel
import com.romeodev.safehomeapp.feature_core_presentation.theme.SafeHomeColors
import com.romeodev.safehomeapp.feature_map_presentation.ui.ExploreMapScreen
import com.romeodev.safehomeapp.feature_map_presentation.viewmodels.ExploreViewModel
import com.romeodev.safehomeapp.feature_owner_presentation.ui.LeadsScreen
import com.romeodev.safehomeapp.feature_owner_presentation.ui.MyPropertiesScreen
import com.romeodev.safehomeapp.feature_owner_presentation.ui.OwnerDashboardScreen
import com.romeodev.safehomeapp.feature_owner_presentation.viewmodels.OwnerPortalViewModel
import com.romeodev.safehomeapp.feature_profile_presentation.ui.ProfileScreen
import com.romeodev.safehomeapp.feature_profile_presentation.viewmodels.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainRootScreen(
    exploreViewModel: ExploreViewModel = koinViewModel(),
    appointmentsViewModel: AppointmentsViewModel = koinViewModel(),
    chatsViewModel: ChatsViewModel = koinViewModel(),
    assistantViewModel: AssistantViewModel = koinViewModel(),
    ownerPortalViewModel: OwnerPortalViewModel = koinViewModel(),
    profileViewModel: ProfileViewModel = koinViewModel(),
    onNavigateToResults: () -> Unit,
    onNavigateToPropertyDetail: (String) -> Unit,
    onNavigateToChat: (String) -> Unit,
    onNavigateToPublish: () -> Unit,
    onNavigateToOffers: (String) -> Unit,
    onNavigateToLanguage: () -> Unit,
    onNavigateToReportScam: () -> Unit,
    onSignOut: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val profileState by profileViewModel.state.collectAsStateWithLifecycle()
    val isOwnerMode = profileState.isOwnerMode

    Scaffold(
        containerColor = SafeHomeColors.DarkBackground,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SafeHomeColors.DarkSurface)
                    .border(1.dp, SafeHomeColors.CardBorder, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!isOwnerMode) {
                        BottomNavItem(
                            icon = Icons.Default.Explore,
                            label = "Explore",
                            isSelected = selectedTab == 0,
                            onClick = { selectedTab = 0 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.CalendarMonth,
                            label = "Viewings",
                            isSelected = selectedTab == 1,
                            onClick = { selectedTab = 1 }
                        )
                        BottomNavItem(
                            icon = Icons.AutoMirrored.Filled.Chat,
                            label = "Chats",
                            isSelected = selectedTab == 2,
                            onClick = { selectedTab = 2 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.AutoAwesome,
                            label = "Assistant",
                            isSelected = selectedTab == 3,
                            onClick = { selectedTab = 3 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.Person,
                            label = "Profile",
                            isSelected = selectedTab == 4,
                            onClick = { selectedTab = 4 }
                        )
                    } else {
                        BottomNavItem(
                            icon = Icons.Default.Dashboard,
                            label = "Dashboard",
                            isSelected = selectedTab == 0,
                            onClick = { selectedTab = 0 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.Home,
                            label = "Properties",
                            isSelected = selectedTab == 1,
                            onClick = { selectedTab = 1 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.Group,
                            label = "Leads",
                            isSelected = selectedTab == 2,
                            onClick = { selectedTab = 2 }
                        )
                        BottomNavItem(
                            icon = Icons.AutoMirrored.Filled.Chat,
                            label = "Chats",
                            isSelected = selectedTab == 3,
                            onClick = { selectedTab = 3 }
                        )
                        BottomNavItem(
                            icon = Icons.Default.Person,
                            label = "Profile",
                            isSelected = selectedTab == 4,
                            onClick = { selectedTab = 4 }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (!isOwnerMode) {
                when (selectedTab) {
                    0 -> ExploreMapScreen(
                        viewModel = exploreViewModel,
                        onNavigateToResults = onNavigateToResults,
                        onNavigateToDetail = onNavigateToPropertyDetail
                    )
                    1 -> MyAppointmentsScreen(
                        viewModel = appointmentsViewModel,
                        onNavigateToChat = onNavigateToChat
                    )
                    2 -> EphemeralChatsListScreen(
                        viewModel = chatsViewModel,
                        onNavigateToConversation = onNavigateToChat
                    )
                    3 -> AssistantScreen(
                        viewModel = assistantViewModel
                    )
                    4 -> ProfileScreen(
                        viewModel = profileViewModel,
                        onNavigateToLanguage = onNavigateToLanguage,
                        onNavigateToReportScam = onNavigateToReportScam,
                        onSignOut = onSignOut
                    )
                }
            } else {
                when (selectedTab) {
                    0 -> OwnerDashboardScreen(
                        viewModel = ownerPortalViewModel,
                        onNavigateToPublish = onNavigateToPublish,
                        onNavigateToOffers = onNavigateToOffers
                    )
                    1 -> MyPropertiesScreen(
                        viewModel = ownerPortalViewModel,
                        onNavigateToPublish = onNavigateToPublish,
                        onNavigateToOffers = onNavigateToOffers,
                        onNavigateToLeads = { selectedTab = 2 }
                    )
                    2 -> LeadsScreen(
                        viewModel = ownerPortalViewModel
                    )
                    3 -> EphemeralChatsListScreen(
                        viewModel = chatsViewModel,
                        onNavigateToConversation = onNavigateToChat
                    )
                    4 -> ProfileScreen(
                        viewModel = profileViewModel,
                        onNavigateToLanguage = onNavigateToLanguage,
                        onNavigateToReportScam = onNavigateToReportScam,
                        onSignOut = onSignOut
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.TextMuted,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) SafeHomeColors.PrimaryBlue else SafeHomeColors.TextMuted
        )
    }
}
