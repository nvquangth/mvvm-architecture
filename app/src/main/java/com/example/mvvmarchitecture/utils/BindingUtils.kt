package com.example.mvvmarchitecture.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmarchitecture.BuildConfig

/**
 * Created by Quang Nguyen on 6/29/20.
 */

@BindingAdapter("glideUrl")
fun ImageView.setGlideUrl(url: String?) {
    GlideApp.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("glideUrlCategory")
fun ImageView.setImageUrlCategory(url: List<String>?) {
    val name = BuildConfig.BASE_URL + url?.get((0..2).random())?.apply {
        substring(0, this.length - 1)
    }
    GlideApp.with(context)
        .load(name)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(Constants.RADIUS_CATEGORY_IMAGE)))
        .into(this)
}