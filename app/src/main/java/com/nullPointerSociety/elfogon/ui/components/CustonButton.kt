package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nullPointerSociety.elfogon.ui.theme.AppColors

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.primaryButtonBg
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = AppColors.primaryButtonText
        )
    }
}
