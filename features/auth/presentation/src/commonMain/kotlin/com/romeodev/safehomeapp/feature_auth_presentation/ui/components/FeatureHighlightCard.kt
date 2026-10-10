package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_feature_condition_report
import com.romeodev.safehomeapp.feature_resources.safehome_feature_kyc_validated
import com.romeodev.safehomeapp.feature_resources.safehome_feature_scam_reports
import com.romeodev.safehomeapp.ui_utils.resources.toActualString

@Composable
fun FeatureHighlightCard(
    modifier: Modifier = Modifier,
) {
    val containerShape = RoundedCornerShape(18.dp)
    val cardBg = Color(0xFF121722)
    val cardBorder = Color(0xFF1E2838)
    val dividerColor = Color(0xFF1A2333)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(containerShape)
            .background(cardBg)
            .border(width = 1.dp, color = cardBorder, shape = containerShape)
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            FeatureRow(
                icon = Icons.AutoMirrored.Filled.Assignment,
                text = Res.string.safehome_feature_condition_report.toActualString()
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = dividerColor
            )
            FeatureRow(
                icon = Icons.Default.Security,
                text = Res.string.safehome_feature_kyc_validated.toActualString()
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = dividerColor
            )
            FeatureRow(
                icon = Icons.Default.WarningAmber,
                text = Res.string.safehome_feature_scam_reports.toActualString()
            )
        }
    }
}

@Composable
private fun FeatureRow(
    icon: ImageVector,
    text: String,
) {
    val iconColor = Color(0xFF3E6DFF)
    val textColor = Color(0xFFEDEDED)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
        )
    }
}
