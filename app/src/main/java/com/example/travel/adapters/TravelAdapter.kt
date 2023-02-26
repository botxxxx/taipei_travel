package com.example.travel.adapters

import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.DiffUtil
import com.example.travel.api.data.ATTR002_Rs
import com.example.travel.databinding.ItemViewBinding
import com.example.travel.main.EntryFragment

class TravelAdapter : BaseDataBindingAdapter<ATTR002_Rs>(ChargeDiffCallback()) {

    override fun setViewHolder(binding: ItemViewBinding): BaseDataBindingViewHolder<ATTR002_Rs> {
        return TravelViewHolder(binding)
    }

    private class TravelViewHolder<T>(binding: ItemViewBinding) : BaseDataBindingViewHolder<T>(binding) {
        init {
            binding.root.setOnClickListener {
                itemView.findFragment<EntryFragment>().navigateToDetail(binding.attr, it)
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