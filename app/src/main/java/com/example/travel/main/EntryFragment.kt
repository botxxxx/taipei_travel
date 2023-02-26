package com.example.travel.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.travel.R
import com.example.travel.adapters.TravelAdapter
import com.example.travel.api.NetworkService
import com.example.travel.api.data.ATTR002_Rs
import com.example.travel.api.data.LangType
import com.example.travel.callback.ChooseLanguageHandler
import com.example.travel.callback.EntryNavigateHandler
import com.example.travel.databinding.FragmentEntryBinding
import com.example.travel.fragment.BaseViewBindingFragment
import com.example.travel.utils.DialogUtils

class EntryFragment : BaseViewBindingFragment<FragmentEntryBinding>(), EntryNavigateHandler, ChooseLanguageHandler {

    private val viewModel: EntryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NetworkService.init()
        setView()
    }

    private fun setView() {
        binding.rvList.apply {
            adapter = TravelAdapter()
        }

        binding.mbtnLang.setOnClickListener {
            ChooseLanguageDialog(this).show(this.parentFragmentManager, null)
        }

        viewModel.apply {
            onSuccessLiveData.observe(viewLifecycleOwner) {
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
            viewModel.languageLiveData.postValue(LangType.TW)
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

    override fun navigateToDetail(item: ATTR002_Rs?, view: View) {
        val direction = EntryFragmentDirections.actionToDetail(item)
        view.findNavController().navigate(direction)
    }

    override fun onLanguageChoose(): (LangType) -> Unit = {
        viewModel.languageLiveData.postValue(it)
    }
}