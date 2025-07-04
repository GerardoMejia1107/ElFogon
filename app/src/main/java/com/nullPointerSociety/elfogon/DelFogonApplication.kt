package com.nullPointerSociety.elfogon

import android.app.Application
import com.nullPointerSociety.elfogon.data.AppProvider

class DelFogonApplication : Application() {
    val appProvider by lazy {
        AppProvider(this)
    }
}