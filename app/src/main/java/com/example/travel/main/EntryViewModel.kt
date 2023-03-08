package com.example.travel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.travel.api.data.ATTR002_Rs
import com.example.travel.api.data.EntryRepository
import com.example.travel.api.data.LangType
import com.example.travel.api.model.BaseModel
import com.example.travel.utils.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryRepository: EntryRepository
) : ViewModel() {
    val languageLiveData: MutableLiveData<LangType?> = MutableLiveData()
    val onFailureLiveData: MutableLiveData<BaseModel?> = MutableLiveData()
    fun clearResponse() {
        onFailureLiveData.value = null
        Loading.hide()
    }

    fun fetchList(langType: LangType): Flow<PagingData<ATTR002_Rs>> {
        return entryRepository.getSearchAttr(langType).cachedIn(viewModelScope)
    }
}