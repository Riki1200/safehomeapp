package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romeodev.safehomeapp.feature_resources.Res
import com.romeodev.safehomeapp.feature_resources.safehome_terms_notice
import com.romeodev.safehomeapp.ui_utils.resources.toActualString

@Composable
fun TermsCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String = Res.string.safehome_terms_notice.toActualString(),
) {
    val boxShape = RoundedCornerShape(6.dp)
    val checkedBg by animateColorAsState(
        targetValue = if (checked) Color(0xFF3E6DFF) else Color(0xFF101623),
        label = "checkbox_bg"
    )
    val borderColor by animateColorAsState(
        targetValue = if (checked) Color(0xFF3E6DFF) else Color(0xFF263347),
        label = "checkbox_border"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCheckedChange(!checked) }
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(20.dp)
                .clip(boxShape)
                .background(checkedBg)
                .border(width = 1.5.dp, color = borderColor, shape = boxShape),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
        Text(
            text = text,
            color = Color(0xFF94A3B8),
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}
