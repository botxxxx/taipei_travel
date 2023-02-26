package com.example.travel.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.travel.databinding.FragmentDetailBinding
import com.example.travel.fragment.BaseViewBindingFragment

class DetailFragment : BaseViewBindingFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setView()
    }

    private fun setView() {
        binding.apply {
            attr = args.attr
            ivClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun bindingCallback(): (LayoutInflater, ViewGroup?) -> FragmentDetailBinding = { layoutInflater, viewGroup ->
        FragmentDetailBinding.inflate(layoutInflater, viewGroup, false)
    }
}