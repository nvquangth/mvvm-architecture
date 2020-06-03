package com.example.mvvmarchitecture.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Quang Nguyen on 6/3/20.
 */
@Parcelize
data class Category(
    val id: String,
    val name: String? = null,
    val images: List<String>? = null,
    @SerializedName("total_recipes")
    val totalRecipe: Int? = null
) : Parcelable