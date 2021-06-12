package com.melimu.exam.service

interface NetworkResponseCallback {
    fun onNetworkSuccess()
    fun onNetworkFailure(th : Throwable)
}