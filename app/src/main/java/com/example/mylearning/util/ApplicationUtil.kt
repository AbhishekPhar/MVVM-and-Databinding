package com.example.mylearning.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mylearning.R

class ApplicationUtil {

    companion object {
        fun openClass(
            fragmentClass: Class<*>,
            fragmentManager: FragmentManager,
            bundle: Bundle?
        ) {
            try {
                val fragment = fragmentClass.newInstance() as Fragment
                fragment.arguments = bundle
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment)
                    .addToBackStack("BACKSTACK").commit()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}