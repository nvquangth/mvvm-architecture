package com.example.mvvmarchitecture.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Created by Quang Nguyen on 6/29/20.
 */

@BindingAdapter("glideUrl")
fun ImageView.setGlideUrl(url: String?) {
    GlideApp.with(context)
        .load(url)
        .into(this)
}