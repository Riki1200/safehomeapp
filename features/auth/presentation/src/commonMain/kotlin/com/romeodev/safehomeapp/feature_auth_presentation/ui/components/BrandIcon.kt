package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BrandIcon(
    modifier: Modifier = Modifier,
    size: Dp = 68.dp,
) {
    val cornerRadius = size * 0.32f
    val iconSize = size * 0.54f

    Box(
        modifier = modifier
            .size(size)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = Color(0x333E6DFF),
                spotColor = Color(0x553E6DFF)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4C7BFE),
                        Color(0xFF3866F2),
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Safe Home Logo",
            tint = Color.White,
            modifier = Modifier.size(iconSize)
        )
    }
}
