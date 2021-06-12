package com.example.mylearning.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("description_title")
    @Expose
    var descriptionTitle: String? = null

    @SerializedName("decription_image")
    @Expose
    var decriptionImage: String? = null

    @SerializedName("description_body")
    @Expose
    var descriptionBody: String? = null

    @SerializedName("banner")
    @Expose
    var banner: ArrayList<String>? = null

    @SerializedName("latitudes")
    @Expose
    var latitudes: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("cupons")
    @Expose
    var cupons: ArrayList<Cupon>? = null
}