package com.example.mylearning.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylearning.R
import com.example.mylearning.view.adapter.StudentTeacherListAdapter
import com.example.mylearning.viewmodel.StudentTeacherListViewModel
import com.example.mylearning.databinding.FragmentStudentTeacherListBinding
import java.util.*

class StudentTeacherListFragment : Fragment(),Observer {
    lateinit var melimuHomeWorkViewModel: StudentTeacherListViewModel
    internal lateinit var adapter: StudentTeacherListAdapter
    private var binding: FragmentStudentTeacherListBinding? = null
    private var bundles:Bundle?=null
    private var filterKey:String?=null
    val ARG_PARAM1 = "fragmentname"
    val ARG_PARAM2 = "parameters"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Binding Layout
        binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_student_teacher_list,
            container,
            false
        ) as FragmentStudentTeacherListBinding?

        if (arguments != null) {
            bundles = arguments!!.getBundle(ARG_PARAM2)
            if (bundles == null)
                bundles = arguments
            filterKey = bundles!!.getString("filterData")
        }

        setHomeWorkList(binding!!.recycleview)
        melimuHomeWorkViewModel = context?.let { filterKey?.let { it1 -> StudentTeacherListViewModel(
            it, it1
        ) } }!!
        binding!!.setMelimuHomeWorkViewModel(melimuHomeWorkViewModel)
        // If any changes made in HomeWorkViewModel Reflected in fragment By Observer-- Through obersver
        setupObserver(melimuHomeWorkViewModel)

        return binding!!.root
    }

    private fun setupObserver(melimuHomeWorkViewModel: StudentTeacherListViewModel) {
        melimuHomeWorkViewModel.addObserver(this)
    }

    private fun setHomeWorkList(recyclerView: RecyclerView) {
        adapter = StudentTeacherListAdapter(filterKey.toString(),context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun update(observable: Observable, arg: Any?) {
        if (observable is StudentTeacherListViewModel) {
            val homeWorkRecycleViewAdapter = binding!!.recycleview.getAdapter() as StudentTeacherListAdapter
            val peopleViewModel = observable as StudentTeacherListViewModel

            if(peopleViewModel.getPeopleList()!!.size==0) {
                binding!!.recycleview.visibility= View.INVISIBLE
                binding!!.textView2.visibility= View.VISIBLE

            }else {
                homeWorkRecycleViewAdapter.setPeopleList(peopleViewModel.getPeopleList())
                homeWorkRecycleViewAdapter.notifyDataSetChanged()
                binding!!.textView2.visibility= View.INVISIBLE
                binding!!.recycleview.visibility= View.VISIBLE
            }
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }


}