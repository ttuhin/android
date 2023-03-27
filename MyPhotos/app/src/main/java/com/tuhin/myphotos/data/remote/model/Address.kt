package com.tuhin.myphotos.data.remote.model

import com.google.gson.annotations.SerializedName

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    @SerializedName("zipcode")
    val zipCode: String
)