package com.example.mylearning.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mylearning.R
import com.example.mylearning.model.Example
import com.google.gson.Gson

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

        fun parseExamList(response: String): Example? {
            var examList: Example? = null
            try {
                val gson = Gson()
                examList = gson.fromJson(response, Example::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return examList
            TODO("Not yet implemented")
        }

        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> { return true }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> { return true }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> { return true }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) { return true }
            }
            return false
        }

    }
}