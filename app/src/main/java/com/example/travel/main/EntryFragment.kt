package com.example.travel.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.travel.R
import com.example.travel.adapters.TravelAdapter
import com.example.travel.api.model.LangType
import com.example.travel.databinding.FragmentEntryBinding
import com.example.travel.fragment.BaseViewBindingFragment
import com.example.travel.utils.DialogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EntryFragment : BaseViewBindingFragment<FragmentEntryBinding>(), ChooseLanguageHandler {

    private lateinit var viewModel: EntryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
    }

    private fun setView() {
        binding.rvList.apply {
            adapter = TravelAdapter()
        }

        binding.mbtnLang.setOnClickListener {
            ChooseLanguageDialog(this).show(this.parentFragmentManager, null)
        }

        viewModel = ViewModelProvider(this@EntryFragment)[EntryViewModel::class.java].apply {
            chargeLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    (binding.rvList.adapter as TravelAdapter).submitList(it.data)
                    clearResponse()
                }
            }
            onFailureLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    onError()
                    clearResponse()
                }
            }
            languageLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    getAttractionsList(it, this@EntryFragment)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            viewModel.languageLiveData.postValue(LangType.TW)
            ChooseLanguageDialog(this@EntryFragment).show(parentFragmentManager, null)
        }
    }

    override fun onDestroyView() {
        binding.rvList.adapter = null
        super.onDestroyView()
    }

    private fun onError() {
        DialogUtils.showNormalAlert(
            context = context,
            title = resources.getString(R.string.common_text_error_msg),
            msg = resources.getString(R.string.common_text_unknown_fail),
            rightButtonText = resources.getString(R.string.common_text_i_know_it),
        )
    }

    override fun bindingCallback(): (LayoutInflater, ViewGroup?) -> FragmentEntryBinding = { layoutInflater, viewGroup ->
        FragmentEntryBinding.inflate(layoutInflater, viewGroup, false)
    }

    companion object {
        fun newInstance() = EntryFragment()
    }

    override fun onLanguageChoose(langType: LangType) {
        viewModel.languageLiveData.postValue(langType)
    }
}

interface ChooseLanguageHandler {
    fun onLanguageChoose(langType: LangType)
}