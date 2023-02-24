package com.example.travel.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.travel.api.model.ATTR002_Rs
import com.example.travel.api.model.TCMSV_003_Rs

class TravelAdapter : BaseDataBindingAdapter<ATTR002_Rs>(ChargeDiffCallback()) {

    private class ChargeDiffCallback : DiffUtil.ItemCallback<ATTR002_Rs>() {
        override fun areItemsTheSame(oldItem: ATTR002_Rs, newItem: ATTR002_Rs): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ATTR002_Rs, newItem: ATTR002_Rs): Boolean {
            return oldItem == newItem
        }
    }
}