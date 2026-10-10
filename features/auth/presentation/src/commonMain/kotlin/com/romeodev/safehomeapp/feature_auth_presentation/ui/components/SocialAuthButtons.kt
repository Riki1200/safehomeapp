package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.ic_apple
import com.romeodev.safehomeapp.feature_resources.ic_google
import com.romeodev.safehomeapp.feature_resources.safehome_apple
import com.romeodev.safehomeapp.feature_resources.safehome_continue_with_apple
import com.romeodev.safehomeapp.feature_resources.safehome_continue_with_google
import com.romeodev.safehomeapp.feature_resources.safehome_google
import com.romeodev.safehomeapp.ui_utils.resources.toActualString
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppleSignInButton(
    modifier: Modifier = Modifier,
    isCompact: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(14.dp)
    val text = if (isCompact) {
        Res.string.safehome_apple.toActualString()
    } else {
        Res.string.safehome_continue_with_apple.toActualString()
    }

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(shape)
            .background(Color(0xFFF1F3F5))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_apple),
                contentDescription = "Apple logo",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = text,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    isCompact: Boolean = false,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(14.dp)
    val bg = Color(0xFF131825)
    val border = Color(0xFF233044)
    val text = if (isCompact) {
        Res.string.safehome_google.toActualString()
    } else {
        Res.string.safehome_continue_with_google.toActualString()
    }

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(shape)
            .background(bg)
            .border(width = 1.dp, color = border, shape = shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_google),
                contentDescription = "Google logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = text,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
