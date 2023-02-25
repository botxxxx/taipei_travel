package com.example.travel.callback

import com.example.travel.adapters.BaseDataBindingViewHolder
import com.example.travel.databinding.ItemViewBinding

interface BaseAdapterHandler<T> {
    fun setViewHolder(binding: ItemViewBinding): BaseDataBindingViewHolder<T>
}