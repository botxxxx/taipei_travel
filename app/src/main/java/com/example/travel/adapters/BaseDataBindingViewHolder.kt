package com.example.travel.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.travel.BR

open class BaseDataBindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(BR.attr, item)
        binding.executePendingBindings()
    }
}