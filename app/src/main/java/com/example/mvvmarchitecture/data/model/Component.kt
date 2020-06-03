package com.example.mvvmarchitecture.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Quang Nguyen on 6/3/20.
 */
@Parcelize
data class Component(
    val id: String,
    val name: String? = null,
    val quantity: String? = null,
    val unit: String? = null
) : Parcelable