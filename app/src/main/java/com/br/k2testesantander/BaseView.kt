package com.br.k2testesantander

import android.content.Context

interface BaseView  {
    fun startProgressBar()
    fun hideProgressBar()
    fun getContext() : Context
    fun showMsgFail(msg : String)
}