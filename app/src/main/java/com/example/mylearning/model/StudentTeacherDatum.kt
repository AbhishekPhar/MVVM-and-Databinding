package com.example.mylearning.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StudentTeacherDatum {
    @SerializedName("Image")
    @Expose
    var image: String? = null

    @SerializedName("RollNumber")
    @Expose
    var rollNumber: String? = null

    @SerializedName("Name")
    @Expose
    var name: String? = null

    @SerializedName("Class Name")
    @Expose
    var className: String? = null

    @SerializedName("Class Section")
    @Expose
    var classSection: String? = null

    @SerializedName("Role")
    @Expose
    var role: String? = null

    @SerializedName("Teacher Data")
    @Expose
    var teacherData: String? = null

    @SerializedName("Teacher Id")
    @Expose
    var teacherId: String? = null

    @SerializedName("studentName")
    @Expose
    var studentName: String? = null

    @SerializedName("Subject")
    @Expose
    var subject: String? = null
}