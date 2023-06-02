package com.emirk.emirkarabeyvize2.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("images")
    val images: List<String>
)