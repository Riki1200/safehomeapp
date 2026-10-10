package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_or_with_email
import com.romeodev.safehomeapp.ui_utils.resources.toActualString

@Composable
fun AuthDivider(
    modifier: Modifier = Modifier,
    text: String = Res.string.safehome_or_with_email.toActualString(),
) {
    val dividerColor = Color(0xFF1E2838)
    val textColor = Color(0xFF64748B)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.6.sp
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = dividerColor
        )
    }
}
