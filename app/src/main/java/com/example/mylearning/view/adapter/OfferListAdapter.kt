package com.example.mylearning.view.adapter


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mylearning.R
import com.example.mylearning.databinding.OfferListItemBinding
import com.example.mylearning.model.Cupon
import kotlin.collections.ArrayList

class OfferListAdapter(filterData: String, context: Context?) : RecyclerView.Adapter<OfferListAdapter.ViewHolder>()
{
    private var listVal:ArrayList<Cupon>? = null
    var filterData:String? = null
    var context:Context? = null
    private var mAlertDialog: AlertDialog? = null
    var messageBundle = Bundle()
    private var showPushHandler: Handler? = null

    init {
        this.filterData=filterData
        this.context = context
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.offer_list_item, parent, false
        )

        return ViewHolder(binding as OfferListItemBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val homeWorksListDTO = listVal!![position]
        holder.layoutHomeworkListItemBinding.setDatamodel(homeWorksListDTO)

    }

    fun setPeopleList(peopleList: ArrayList<Cupon>?) {
        this.listVal = peopleList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if(listVal!=null)
        {
            return listVal!!.size
        }
        else
        {
            return 0
        }

    }

    inner class ViewHolder(var layoutHomeworkListItemBinding: OfferListItemBinding) :
        RecyclerView.ViewHolder(layoutHomeworkListItemBinding.getRoot())

    companion object {
        private val TAG = "RecyclerViewAdapter"
    }
}
