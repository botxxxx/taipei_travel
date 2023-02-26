package com.example.travel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travel.api.NetworkService
import com.example.travel.api.data.ATTR001_Rs
import com.example.travel.api.data.LangType
import com.example.travel.api.model.BaseCallBack
import com.example.travel.api.model.BaseModel
import com.example.travel.callback.BaseViewInterface
import com.example.travel.utils.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor() : ViewModel() {
    val languageLiveData: MutableLiveData<LangType?> = MutableLiveData()
    val onSuccessLiveData: MutableLiveData<ATTR001_Rs?> = MutableLiveData()
    val onFailureLiveData: MutableLiveData<BaseModel?> = MutableLiveData()
    fun clearResponse() {
        onSuccessLiveData.value = null
        onFailureLiveData.value = null
        Loading.hide()
    }

    fun getAttractionsList(langType: LangType = LangType.TW, baseViewInterface: BaseViewInterface) {
        clearResponse()
        Log.e("request", "getAttractionsList()")
        Loading.show(baseViewInterface.getRootView())
        NetworkService.loadAttractions(langType, object : BaseCallBack<ATTR001_Rs>(baseViewInterface) {
            override fun onResponse(response: ATTR001_Rs) {
                Log.e("response", "success")
                onSuccessLiveData.postValue(response)
            }

            override fun onFailure() {
                Log.e("response", "fail")
                onFailureLiveData.postValue(BaseModel())
            }
        })
    }
}