package com.example.travel.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travel.R
import com.example.travel.api.model.ATTR002_Rs
import com.example.travel.api.model.TCMSV_003_Rs
import kotlinx.coroutines.launch

@BindingAdapter("chargeItems")
fun bindRecyclerViewWithDataItemList(recyclerView: RecyclerView, dataItemList: List<ATTR002_Rs>?) {
    dataItemList?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is TravelAdapter -> submitList(it)
            }
        }
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrls: List<TCMSV_003_Rs>?) {
    if (!imageUrls.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrls[0].src)
            .centerCrop()
            .placeholder(R.drawable.ic_dialogs_bell)
            .into(view)
    }
}