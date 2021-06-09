package com.example.mylearning.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylearning.R
import com.example.mylearning.util.ApplicationUtil


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApplicationUtil.openClass(StudentTeacherTab::class.java, supportFragmentManager , null )

    }
}