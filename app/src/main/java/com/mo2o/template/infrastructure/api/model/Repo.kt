package com.mo2o.template.infrastructure.api.model

import com.google.gson.annotations.SerializedName

data class Repo(
        val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("description") val description: String,
        @SerializedName("owner") val owner: Owner,
        @SerializedName("stargazers_count") val stars: Int,
        @SerializedName("forks_count") val forks: Int,
        @SerializedName("updated_at") val updated: String,
        @SerializedName("language") val language: String
)
