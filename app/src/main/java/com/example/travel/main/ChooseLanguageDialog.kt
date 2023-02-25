package com.example.travel.main

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel.R
import com.example.travel.api.model.LangType
import com.example.travel.callback.ChooseLanguageHandler
import com.example.travel.databinding.ChooseLanguageFragmentBinding
import com.example.travel.fragment.BaseBottomSheetDialogFragment

/**
 * Created by Ricky on 2021/9/8.<br/>
 * choose bottom sheet dialog fragment<br/>
 * include search and list
 */
class ChooseLanguageDialog(private val languageHandler: ChooseLanguageHandler) : BaseBottomSheetDialogFragment<ChooseLanguageFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogCircle)
        initView()
        setEvent()
    }

    private fun initView() {
        val languageList = listOf(
            LangType.TW, LangType.CN, LangType.EN, LangType.JP, LangType.KO, LangType.ES, LangType.ID, LangType.TH, LangType.VI
        )
        binding.rvLanguage.run {
            adapter = ChooseLanguageAdapter(languageList, onItemClick = { langInfo ->
                languageHandler.onLanguageChoose(langInfo)
                dialog?.dismiss()
            })
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(ColorDrawable(R.drawable.divider_common))
            }
            addItemDecoration(itemDecoration)
        }
    }

    private fun setEvent() {
        binding.apply {
            tvDismiss.setOnClickListener { dialog?.dismiss() }
        }
    }

    override fun bindingCallback(): (LayoutInflater, ViewGroup?) -> ChooseLanguageFragmentBinding =
        { layoutInflater, viewGroup -> ChooseLanguageFragmentBinding.inflate(layoutInflater, viewGroup, false) }
}