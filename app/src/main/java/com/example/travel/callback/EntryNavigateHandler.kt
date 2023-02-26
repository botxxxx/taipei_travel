package com.example.travel.callback

import android.view.View
import com.example.travel.api.data.ATTR002_Rs

interface EntryNavigateHandler {
    fun navigateToDetail(item: ATTR002_Rs?, view: View)
}