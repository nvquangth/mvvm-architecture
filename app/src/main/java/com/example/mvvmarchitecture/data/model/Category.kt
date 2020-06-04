package com.example.mvvmarchitecture.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Quang Nguyen on 6/3/20.
 */

@Entity(tableName = "category")
@Parcelize
data class Category(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val images: List<String>? = null,
    @SerializedName("total_recipes")
    val totalRecipe: Int? = null
) : Parcelable