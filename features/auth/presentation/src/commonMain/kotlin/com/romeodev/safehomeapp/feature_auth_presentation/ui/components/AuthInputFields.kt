package com.romeodev.safehomeapp.feature_auth_presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
    errorMessage: String? = null,
) {
    val fieldShape = RoundedCornerShape(12.dp)
    val inputBg = Color(0xFF101623)
    val defaultBorder = Color(0xFF1E2838)
    val focusedBorder = Color(0xFF3E6DFF)
    val errorBorder = Color(0xFFEF4444)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFFC7D0DE),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            singleLine = true,
            isError = errorMessage != null,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF475569),
                    fontSize = 15.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = inputBg,
                unfocusedContainerColor = inputBg,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = focusedBorder,
                unfocusedBorderColor = defaultBorder,
                errorBorderColor = errorBorder,
                errorContainerColor = inputBg,
                cursorColor = focusedBorder,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onAny = { onImeAction() }
            )
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = errorBorder,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun AuthPasswordTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    errorMessage: String? = null,
    helperText: String? = null,
    counterText: String? = null,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val fieldShape = RoundedCornerShape(12.dp)
    val inputBg = Color(0xFF101623)
    val defaultBorder = Color(0xFF1E2838)
    val focusedBorder = Color(0xFF3E6DFF)
    val errorBorder = Color(0xFFEF4444)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFFC7D0DE),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = fieldShape,
            singleLine = true,
            isError = errorMessage != null,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF475569),
                    fontSize = 15.sp
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = Color(0xFF64748B)
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = inputBg,
                unfocusedContainerColor = inputBg,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = focusedBorder,
                unfocusedBorderColor = defaultBorder,
                errorBorderColor = errorBorder,
                errorContainerColor = inputBg,
                cursorColor = focusedBorder,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onAny = { onImeAction() }
            )
        )

        if (helperText != null || counterText != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (helperText != null) {
                    Text(
                        text = helperText,
                        color = Color(0xFF64748B),
                        fontSize = 13.sp
                    )
                }
                if (counterText != null) {
                    Text(
                        text = counterText,
                        color = Color(0xFF64748B),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = errorBorder,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
