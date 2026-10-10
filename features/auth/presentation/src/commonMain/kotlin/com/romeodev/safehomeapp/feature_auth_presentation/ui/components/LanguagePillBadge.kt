package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguagePillBadge(
    modifier: Modifier = Modifier,
    languageText: String = "English",
    onClick: () -> Unit = {},
) {
    val shape = RoundedCornerShape(20.dp)
    val pillBg = Color(0xFF131926)
    val pillBorder = Color(0xFF1F2B3E)

    Row(
        modifier = modifier
            .clip(shape)
            .background(pillBg)
            .border(width = 1.dp, color = pillBorder, shape = shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = "Language",
            tint = Color(0xFFD0D7E2),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = languageText,
            color = Color(0xFFE2E8F0),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
