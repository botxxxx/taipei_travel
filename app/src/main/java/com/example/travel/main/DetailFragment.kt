package com.example.travel.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.travel.databinding.FragmentDetailBinding
import com.example.travel.fragment.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseViewBindingFragment<FragmentDetailBinding>() {

//    private val args: UserDetailFragmentArgs by navArgs()
//    private lateinit var binding: FragmentDetailViewBinding
//    private val viewModel: DetailViewModel by viewModels()
//    private var searchJob: Job? = null


//    private fun subscribeUi(login: String) {
//        searchJob?.cancel()
//        searchJob = lifecycleScope.launch {
//            binding.detail = viewModel.getResult(login)
//        }
//    }

    override fun bindingCallback(): (LayoutInflater, ViewGroup?) -> FragmentDetailBinding = { layoutInflater, viewGroup ->
        FragmentDetailBinding.inflate(layoutInflater, viewGroup, false)
    }
}