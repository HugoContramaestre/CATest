package com.example.catest.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.catest.ui.common.extensions.loadUrl

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (!url.isNullOrEmpty()) loadUrl(url)
}