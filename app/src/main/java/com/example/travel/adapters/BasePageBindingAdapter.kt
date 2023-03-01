package com.example.travel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.travel.callback.BaseAdapterHandler
import com.example.travel.databinding.ItemViewBinding

abstract class BasePageBindingAdapter<T : Any>(diffCallback: DiffUtil.ItemCallback<T>) : PagingDataAdapter<T, BaseDataBindingViewHolder<T>>(diffCallback), BaseAdapterHandler<T> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
        return setViewHolder(binding)
    }

    override fun setViewHolder(binding: ItemViewBinding): BaseDataBindingViewHolder<T> {
        return BaseDataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseDataBindingViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }
}