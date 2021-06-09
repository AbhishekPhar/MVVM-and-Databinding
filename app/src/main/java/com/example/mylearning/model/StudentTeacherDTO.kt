package com.example.mylearning.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StudentTeacherDTO {
    @SerializedName("StudentTeacherData")
    @Expose
    var studentTeacherData: List<StudentTeacherDatum>? = null
}