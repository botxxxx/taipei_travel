package com.example.travel.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.travel.R
import com.example.travel.api.model.ATTR002_Rs
import com.example.travel.databinding.ItemViewBinding
import com.example.travel.utils.DialogUtils

class TravelAdapter : BaseDataBindingAdapter<ATTR002_Rs>(ChargeDiffCallback()) {

    override fun setViewHolder(binding: ItemViewBinding): BaseDataBindingViewHolder<ATTR002_Rs> {
        return TravelViewHolder(binding)
    }

    private class TravelViewHolder<T>(binding: ItemViewBinding) : BaseDataBindingViewHolder<T>(binding) {
        init {
            binding.root.apply {
                setOnClickListener {
                    DialogUtils.showNormalAlert(
                        context = context,
                        title = resources.getString(R.string.common_text_error_msg),
                        msg = resources.getString(R.string.common_login_failure),
                        rightButtonText = resources.getString(R.string.common_text_i_know_it),
                    )
                }
            }
        }
    }

    private class ChargeDiffCallback : DiffUtil.ItemCallback<ATTR002_Rs>() {
        override fun areItemsTheSame(oldItem: ATTR002_Rs, newItem: ATTR002_Rs): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ATTR002_Rs, newItem: ATTR002_Rs): Boolean {
            return oldItem == newItem
        }
    }
}