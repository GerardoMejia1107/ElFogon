package com.nullPointerSociety.elfogon

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.nullPointerSociety.elfogon.ui.layout.CustomScaffold
import com.nullPointerSociety.elfogon.ui.theme.ElFogonTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElFogonTheme() {
                CustomScaffold()
            }
        }
    }
}

