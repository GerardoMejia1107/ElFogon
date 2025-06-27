package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.data.model.tips.Tip

@Composable
fun TipModal(tipData: Tip, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painterResource(R.drawable.del_fogon),
                    contentDescription = "Del Fogon Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text("Cooking Hint!", style = MaterialTheme.typography.titleLarge)
            }
        },
        text = { Text(tipData.description) },
        confirmButton = {
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                CustomButton("Got it!", onDismiss)
            }
        }
    )
}

