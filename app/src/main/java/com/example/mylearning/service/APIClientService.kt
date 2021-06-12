package com.melimu.exam.service

import com.example.mylearning.model.Example
import com.example.mylearning.util.ApplicationUtil
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class APIClientService {

    private val TAG = "APIClientService"


    private var retrofit: Retrofit? = null

    companion object
    {
        private var instance: APIClientService? = null
        @Synchronized
        fun getInstance(): APIClientService? {
            if (instance == null) {
                instance = APIClientService()
            }
            return instance
        }
    }

    init {
        getClient()
    }
    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
                .baseUrl("http://13.232.62.239")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit
    }




    @Throws(Exception::class)
    fun getExamList(mCallback: NetworkResponseCallback, forceFetch: Boolean):Example {
        val service: APIService = retrofit!!.create(APIService::class.java)
        var examDataList: Example

        // Remote call can be executed synchronously since the job calling it is already backgrounded.
        val response: Response<ResponseBody?> = service.getExample()!!.execute()

        if (response == null || !response.isSuccessful || response.errorBody()!= null) {
            throw RemoteException(response)
            mCallback.onNetworkFailure(throw RemoteException(response))
        } else {
            examDataList = ApplicationUtil.parseExamList(response.body()!!.string())!!
            mCallback.onNetworkSuccess()
        }
        return examDataList
    }



}