package com.tuhin.myphotos.data.remote.model

import com.google.gson.annotations.SerializedName

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    @SerializedName("username")
    val userName: String,
    val website: String
)