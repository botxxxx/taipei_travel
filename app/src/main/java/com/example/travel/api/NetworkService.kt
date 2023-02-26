package com.example.travel.api

import android.util.Log
import com.example.travel.api.data.ATTR001_Rs
import com.example.travel.api.model.BaseCallBack
import com.example.travel.api.data.LangType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object NetworkService {

    private lateinit var apiService: ApiService

    fun init() {
        apiService = ApiService.create()
    }

    fun loadAttractions(langType: LangType, callback: BaseCallBack<ATTR001_Rs>) {
        val safeCoroutineScope = callback.baseViewInterface.getLifeCycleScope()
        safeCoroutineScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    val request = apiService.getAttractions(langType.lang)
                    Log.e("request", "$request")
                    callback.getResponse(request)
                }
            } catch (ex: Exception) {
                Log.e("error", "sendAppRequest fail: ${Log.getStackTraceString(ex)}")
                callback.onFailure()
            }
        }
    }
}