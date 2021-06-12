package com.example.mylearning.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mylearning.view.MainFragment

@SuppressLint("WrongConstant")
class OfferDetailTabAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private lateinit var fragment: Fragment

    override fun getItem(position: Int):Fragment
    {
        return when (position) {
            0 -> {
                Log.d("filterData", "todo"+position);
                fragment = MainFragment()
                val b = Bundle()
                b.putString("filterData", "Offers")
                fragment.arguments = b
                return fragment
            }
            else-> {
                Log.d("filterData", "submit"+position);

                fragment = MainFragment()
                val b = Bundle()
                b.putString("filterData", "Details")
                fragment.arguments = b
                return fragment
            }
//            else -> {
//                Log.d("filterData", "grade"+position);
//                fragment = StudentTeacherListFragment()
//                val b = Bundle()
//                b.putString("filterData", "Graded")
//                fragment.arguments = b
//                return fragment
//            }
        }
    }

    override fun getCount(): Int { return 2 }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Offers"
            else -> "Details"
        }
    }
}