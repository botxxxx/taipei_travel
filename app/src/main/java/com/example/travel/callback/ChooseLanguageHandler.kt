package com.example.travel.callback

import com.example.travel.api.data.LangType

interface ChooseLanguageHandler {
    fun onLanguageChoose(): (LangType) -> Unit
}