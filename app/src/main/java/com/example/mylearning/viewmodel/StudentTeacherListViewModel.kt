package com.example.mylearning.viewmodel

import android.content.Context
import android.os.Handler
import android.widget.Toast
import com.example.mylearning.R
import com.example.mylearning.model.StudentTeacherDTO
import com.example.mylearning.model.StudentTeacherDatum
import com.google.gson.Gson
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class StudentTeacherListViewModel(private val context: Context, filterData:String) : Observable() {

    private var handler: Handler? = null
    var listVal: ArrayList<StudentTeacherDatum>?=null
    var filterData: String?=null

    init {
        this.filterData = filterData
        try {
            populateview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun populateview() {
        fetchData()
    }

    @Throws(IOException::class)
    fun fetchData() {

        handler = Handler(Handler.Callback {
            if(it.what==1)
            {
                Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
            }
            else
            {
                changeSampleDataSet(listVal)
            }

            false
        })

        val thread = Thread(Runnable {
            try {
                loadJSONFromAsset()
                handler!!.sendEmptyMessage(0)

            } catch (e: Exception) {
                handler!!.sendEmptyMessage(1)
            }
        })
        thread.start()
    }

    private fun changeSampleDataSet(peoples: ArrayList<StudentTeacherDatum>?) {
        setChanged()
        notifyObservers()
    }

    fun loadJSONFromAsset() {
        var json1: String? = null
        var json2: String? = null
        try {
            val inputStream = context.resources.openRawResource(R.raw.data)

                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json1 = String(buffer)

            val inputStream1 = context.resources.openRawResource(R.raw.data2)

            val size1 = inputStream1.available()
            val buffer1 = ByteArray(size1)
            inputStream1.read(buffer1)
            inputStream1.close()
            json2 = String(buffer1)

        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        listVal = ArrayList<StudentTeacherDatum>()

        if(filterData.equals("Student"))
            Gson().fromJson(json1, StudentTeacherDTO::class.java).studentTeacherData?.let {
                listVal!!.addAll(
                    it
                )
            }
        else
            Gson().fromJson(json2, StudentTeacherDTO::class.java).studentTeacherData?.let {
                listVal!!.addAll(
                    it
                )
            }
    }

    fun getPeopleList(): ArrayList<StudentTeacherDatum>? {
        return listVal
    }


}
