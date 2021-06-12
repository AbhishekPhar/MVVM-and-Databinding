package com.melimu.exam.service

import okhttp3.ResponseBody
import retrofit2.Response

class RemoteException(response: Response<ResponseBody?>) :Exception() {
    private var response: Response<*>? = null

    fun RemoteException(response: Response<*>?) {
        this.response = response
    }

    fun getResponse(): Response<*>? {
        return response
    }
}