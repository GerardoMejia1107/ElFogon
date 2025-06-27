package com.nullPointerSociety.elfogon.data.repository

import com.nullPointerSociety.elfogon.data.model.tips.Tip
import kotlinx.coroutines.flow.StateFlow

interface SystemRepository {
    val tips: StateFlow<List<Tip>>

    suspend fun fetchTips()
}
