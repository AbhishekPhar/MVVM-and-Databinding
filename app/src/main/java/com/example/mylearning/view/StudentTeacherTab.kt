package com.example.mylearning.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mylearning.R
import com.example.mylearning.view.adapter.StudentTeacherTabAdapter
import com.example.mylearning.databinding.FragmentStudentTeacherTabBinding

/**
 * A simple [Fragment] subclass.
 * Use the [StudentTeacherTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentTeacherTab : Fragment() {
    private var views: View? = null
    private var binding: FragmentStudentTeacherTabBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            R.layout.fragment_student_teacher_tab, container, false) as FragmentStudentTeacherTabBinding?
        views = binding!!.root
        return views
    }

    override fun onResume() {
        super.onResume()
        Log.d("AbhiPhar","----OnResume()")
        val fragmentAdapter = childFragmentManager?.let { StudentTeacherTabAdapter(it) }
      //  if(ApplicationUtil.isAppOnForeground())
    //    {
            binding!!.viewpagerMain.adapter = fragmentAdapter
            binding!!.tabsMain.setupWithViewPager(binding!!.viewpagerMain)
      //  }
    }
}