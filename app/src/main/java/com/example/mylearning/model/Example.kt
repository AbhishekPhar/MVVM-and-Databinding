package com.example.mylearning.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {
    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null

    @SerializedName("APICODERESULT")
    @Expose
    var apicoderesult: String? = null

    @SerializedName("result")
    @Expose
    var result: Result? = null
}