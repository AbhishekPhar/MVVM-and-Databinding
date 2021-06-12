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
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.mylearning.R
import com.example.mylearning.databinding.OfferDetailFragmentBinding
import com.example.mylearning.view.adapter.OfferListAdapter
import com.example.mylearning.view.adapter.ViewPagerAdapter
import com.example.mylearning.viewmodel.OfferDetailViewModel
import java.util.*


class MainFragment : Fragment(),Observer {
    lateinit var melimuHomeWorkViewModel: OfferDetailViewModel
    internal lateinit var adapter: OfferListAdapter
    private var binding: OfferDetailFragmentBinding? = null
    private var bundles:Bundle?=null
    private var filterKey:String?=null
    val ARG_PARAM1 = "fragmentname"
    val ARG_PARAM2 = "parameters"


    // Creating Object of ViewPagerAdapter
    var mViewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Binding Layout
        binding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                R.layout.offer_detail_fragment,
                container,
                false
        ) as OfferDetailFragmentBinding?

        if (arguments != null) {
            bundles = arguments!!.getBundle(ARG_PARAM2)
            if (bundles == null)
                bundles = arguments
            filterKey = bundles!!.getString("filterData")
        }

        setHomeWorkList(binding!!.recycleview)
        melimuHomeWorkViewModel = context?.let { filterKey?.let { it1 -> OfferDetailViewModel(
                it, it1
        ) } }!!
        binding!!.setMelimuHomeWorkViewModel(melimuHomeWorkViewModel)
        // If any changes made in HomeWorkViewModel Reflected in fragment By Observer-- Through obersver
        setupObserver(melimuHomeWorkViewModel)

        return binding!!.root
    }

    private fun setupObserver(melimuHomeWorkViewModel: OfferDetailViewModel) {
        melimuHomeWorkViewModel.addObserver(this)
    }

    private fun setHomeWorkList(recyclerView: RecyclerView) {
        adapter = OfferListAdapter(filterKey.toString(), context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun update(observable: Observable, arg: Any?) {
        if (observable is OfferDetailViewModel) {
            val homeWorkRecycleViewAdapter = binding!!.recycleview.getAdapter() as OfferListAdapter
            val peopleViewModel = observable as OfferDetailViewModel

            if(peopleViewModel.getPeopleList()==null) {
                binding!!.recycleview.visibility= View.INVISIBLE
                binding!!.textView2.visibility= View.VISIBLE

            }else {
                binding!!.textView2.visibility= View.GONE


                if(filterKey.equals("Offers"))
                {
                    Glide.with(context)
                            .asBitmap()
                            .load(peopleViewModel!!.getPeopleList()!!.result!!.decriptionImage)
                            .into(binding!!.imageView);


                    binding!!.recycleview.visibility= View.GONE
                    binding!!.detailLayout.visibility = View.GONE
                    binding!!.offerLayout.visibility = View.VISIBLE
                    binding!!.description.setText(peopleViewModel!!.getPeopleList()!!.result!!.descriptionBody)

                }else{
                    homeWorkRecycleViewAdapter.setPeopleList(peopleViewModel!!.getPeopleList()!!.result!!.cupons)
                    homeWorkRecycleViewAdapter.notifyDataSetChanged()
                    binding!!.recycleview.visibility= View.VISIBLE
                    binding!!.detailLayout.visibility = View.VISIBLE
                    binding!!.offerLayout.visibility = View.GONE

                    // Initializing the ViewPagerAdapter
                    mViewPagerAdapter = context?.let { peopleViewModel!!.getPeopleList()?.result?.banner?.let { it1 -> ViewPagerAdapter(it, it1) } }
                    binding!!.viewPagerMain!!.adapter = mViewPagerAdapter
                }



            }
        }
    }

    companion object {
        private val TAG = "MainActivity"
    }


}