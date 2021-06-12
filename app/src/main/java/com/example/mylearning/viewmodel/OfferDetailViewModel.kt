package com.example.mylearning.viewmodel

import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mylearning.model.Example
import com.example.mylearning.util.ApplicationUtil
import com.melimu.exam.service.APIClientService
import com.melimu.exam.service.NetworkResponseCallback
import com.melimu.exam.service.RemoteException
import java.io.IOException
import java.util.*

class OfferDetailViewModel(private val context: Context, filterData:String) : Observable() {
    private lateinit var mList: Example
    var mShowProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    private var mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var mShowApiError: MutableLiveData<Boolean> = MutableLiveData()


    private var handler: Handler? = null
    var listVal: Example?=null
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
                changeSampleDataSet()
            }

            false
        })

        val thread = Thread(Runnable {
            try {
                listVal = fetchExamFromServer(context, false)
               // loadJSONFromAsset()
                handler!!.sendEmptyMessage(0)

            } catch (e: Exception) {
                handler!!.sendEmptyMessage(1)
            }
        })
        thread.start()
    }

    private fun changeSampleDataSet() {
        setChanged()
        notifyObservers()
    }

//    fun loadJSONFromAsset() {
//        var json1: String? = null
//        var json2: String? = null
//        try {
//            val inputStream = context.resources.openRawResource(R.raw.data)
//
//                val size = inputStream.available()
//                val buffer = ByteArray(size)
//                inputStream.read(buffer)
//                inputStream.close()
//                json1 = String(buffer)
//
//            val inputStream1 = context.resources.openRawResource(R.raw.data2)
//
//            val size1 = inputStream1.available()
//            val buffer1 = ByteArray(size1)
//            inputStream1.read(buffer1)
//            inputStream1.close()
//            json2 = String(buffer1)
//
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//        }
//        listVal = ArrayList<StudentTeacherDatum>()
//
//        if(filterData.equals("Student"))
//            Gson().fromJson(json1, StudentTeacherDTO::class.java).studentTeacherData?.let {
//                listVal!!.addAll(
//                    it
//                )
//            }
//        else
//            Gson().fromJson(json2, StudentTeacherDTO::class.java).studentTeacherData?.let {
//                listVal!!.addAll(
//                    it
//                )
//            }
//    }



    fun getPeopleList(): Example? {
        return listVal
    }


    @Throws(IOException::class, RemoteException::class)
    fun fetchExamFromServer(context: Context, forceFetch : Boolean): Example {
        if (ApplicationUtil.isNetworkAvailable(context)) {
            mList = APIClientService.getInstance()?.getExamList(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {}
                override fun onNetworkSuccess() {}
            }, forceFetch)!!
        } else {
            Toast.makeText(context,"Please check internet connection", Toast.LENGTH_SHORT).show();
        }
        return mList
    }


}
