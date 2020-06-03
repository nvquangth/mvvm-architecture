package com.example.mvvmarchitecture.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Quang Nguyen on 6/3/20.
 */
@Parcelize
data class Recipe(
    val id: String,
    val name: String? = null,
    val time: Int? = null,
    val level: String? = null,
    val serving: Int? = null,
    @SerializedName("img")
    val imageUrl: String? = null,
    val components: List<Component>? = null,
    @SerializedName("cook_steps")
    val cookSteps: List<Step>? = null,
    val totalComponent: Int? = null,
    val totalStep: Int? = null
) : Parcelable