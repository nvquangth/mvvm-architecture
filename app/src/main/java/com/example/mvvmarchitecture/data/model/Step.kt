package com.example.mvvmarchitecture.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Quang Nguyen on 6/3/20.
 */
@Parcelize
data class Step(
    val id: String,
    val step: Int? = null,
    @SerializedName("des")
    val description: String? = null,
    val pictures: List<String>? = null
) : Parcelable