package com.example.travel.adapters

import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.travel.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrls: String?) {
    imageUrls?.let {
        Glide.with(view.context)
            .load(imageUrls)
            .centerCrop()
            .placeholder(R.drawable.ic_dialogs_bell)
            .into(view)
    }
}

@BindingAdapter("isHtml")
fun bindIsHtml(view: TextView, description: String?) {
    view.apply {
        if (description != null) {
            movementMethod = LinkMovementMethod.getInstance()
            autoLinkMask = Linkify.WEB_URLS
            text = Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            setLinkTextColor(context.getColor(R.color.apple))
        } else {
            text = ""
        }
    }
}