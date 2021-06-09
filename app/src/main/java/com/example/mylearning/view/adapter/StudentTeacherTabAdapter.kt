package com.example.mylearning.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mylearning.view.StudentTeacherListFragment

@SuppressLint("WrongConstant")
class StudentTeacherTabAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private lateinit var fragment: Fragment

    override fun getItem(position: Int):Fragment
    {
        return when (position) {
            0 -> {
                Log.d("filterData", "todo"+position);
                fragment = StudentTeacherListFragment()
                val b = Bundle()
                b.putString("filterData", "Student")
                fragment.arguments = b
                return fragment
            }
            else-> {
                Log.d("filterData", "submit"+position);

                fragment = StudentTeacherListFragment()
                val b = Bundle()
                b.putString("filterData", "Teacher")
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
            0 -> "Student"
            else -> "Teacher"
        }
    }
}