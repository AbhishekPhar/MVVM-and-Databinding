package com.example.mylearning.view.adapter


import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylearning.R
import com.example.mylearning.databinding.AddLayoutBinding
import com.example.mylearning.databinding.StudentTeacherListItemBinding
import com.example.mylearning.model.StudentTeacherDatum
import java.util.*

class StudentTeacherListAdapter(filterData: String, context: Context?) : RecyclerView.Adapter<StudentTeacherListAdapter.ViewHolder>()
{
    private var listVal:ArrayList<StudentTeacherDatum>? = null
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
                R.layout.student_teacher_list_item, parent, false
        )

        return ViewHolder(binding as StudentTeacherListItemBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //   listVal?.get(position)?.getDate()?.let { listVal!!.get(position).setDate(it) }

        if(filterData!!.equals("Student"))
        {
            holder.layoutHomeworkListItemBinding!!.studentlay.visibility = View.VISIBLE
            holder.layoutHomeworkListItemBinding!!.teacherlay.visibility = View.GONE
        }else{
            holder.layoutHomeworkListItemBinding!!.studentlay.visibility = View.GONE
            holder.layoutHomeworkListItemBinding!!.teacherlay.visibility = View.VISIBLE
        }

        val homeWorksListDTO = listVal!![position]
        holder.layoutHomeworkListItemBinding.setDatamodel(homeWorksListDTO)

        Glide.with(context)
            .asBitmap()
            .load(listVal!!.get(position).image)
            .into(holder.layoutHomeworkListItemBinding.imageView);

        setUpHandler()
        setUpListener(holder, position)
    }

    private fun setUpListener(holder: ViewHolder, position: Int)
    {
        holder.layoutHomeworkListItemBinding.parentId.setOnClickListener {
            showSettingsAlert(position)
        }
    }

    private fun setUpHandler() {
        showPushHandler = Handler {
            if(it.what==0)
            {
                listVal!!.removeAt(messageBundle.getInt("position"))
                notifyItemRemoved(messageBundle.getInt("position"))
            }else{
                notifyItemInserted(listVal!!.size - 1)
            }

            notifyDataSetChanged()
            false
        }
    }

    private fun showStudentDialog() {
        val binding: AddLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.add_layout, null, false)
        val builder = AlertDialog.Builder(context!!)
        builder.setView(binding.getRoot())
        val alertDialog = builder.create()
        alertDialog.show()

        binding!!.studentlay.visibility = View.VISIBLE

        binding.addButton.setOnClickListener(View.OnClickListener {
            var studentTeacherDatum = StudentTeacherDatum()
            studentTeacherDatum.rollNumber = binding!!.rollno.text.toString()
            studentTeacherDatum.name = binding!!.studentName.text.toString()
            studentTeacherDatum.className = binding!!.className.text.toString()
            studentTeacherDatum.classSection = binding!!.section.text.toString()
            listVal!!.add(studentTeacherDatum)
            showPushHandler!!.sendEmptyMessage(1)
            alertDialog.dismiss()
        })
    }

    private fun showTeacherDialog() {
        val binding: AddLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.add_layout, null, false)
        val builder = AlertDialog.Builder(context!!)
        builder.setView(binding.getRoot())
        val alertDialog = builder.create()
        alertDialog.show()

        binding!!.teacherlay.visibility = View.VISIBLE

        binding.addButton.setOnClickListener(View.OnClickListener {
            var studentTeacherDatum = StudentTeacherDatum()
            studentTeacherDatum.name = binding!!.teacherName.text.toString()
            studentTeacherDatum.teacherId = binding!!.teacherid.text.toString()
            studentTeacherDatum.subject = binding!!.teachersubject.text.toString()
            listVal!!.add(studentTeacherDatum)
            showPushHandler!!.sendEmptyMessage(1)
            alertDialog.dismiss()
        })
    }

    fun showSettingsAlert(position: Int) {
            if (mAlertDialog != null && mAlertDialog!!.isShowing) return
            val alertDialogSetting = AlertDialog.Builder(context!!)
            alertDialogSetting.setTitle("Make Choice")
            alertDialogSetting.setMessage("Please select to add or delete the candidate")
            alertDialogSetting.setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, which ->
                if(filterData.equals("Student")) {
                    showStudentDialog()
                }
                else {
                    showTeacherDialog()
                }
                //showPushHandler!!.sendEmptyMessage(1)
                dialog.cancel()
            })
            alertDialogSetting.setNegativeButton("DELETE", DialogInterface.OnClickListener { dialog, which ->
                messageBundle.putInt("position", position)
                showPushHandler!!.sendEmptyMessage(0)
                dialog.cancel()
            })
            mAlertDialog = alertDialogSetting.show()
            mAlertDialog!!.show()
        }


    fun setPeopleList(peopleList: ArrayList<StudentTeacherDatum>?) {
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

    inner class ViewHolder(var layoutHomeworkListItemBinding: StudentTeacherListItemBinding) :
        RecyclerView.ViewHolder(layoutHomeworkListItemBinding.getRoot())

    companion object {
        private val TAG = "RecyclerViewAdapter"
    }
}
