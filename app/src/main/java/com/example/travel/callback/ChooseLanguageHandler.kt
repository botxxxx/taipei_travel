package com.example.travel.callback

import com.example.travel.api.model.LangType

interface ChooseLanguageHandler {
    fun onLanguageChoose(langType: LangType)
}