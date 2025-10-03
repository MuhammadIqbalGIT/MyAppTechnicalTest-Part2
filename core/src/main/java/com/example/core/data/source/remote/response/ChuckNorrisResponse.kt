package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class JokeSearchResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("result")
    val result: List<JokeItem>
)

data class JokeItem(
    @SerializedName("categories")
    val categories: List<String>,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("icon_url")
    val iconUrl: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("value")
    val value: String
)
