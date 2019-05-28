package com.br.k2testesantander.account

import com.br.k2testesantander.R
import com.br.k2testesantander.helper.HelperFormat
import com.br.k2testesantander.network.api.Retrofit
import com.br.k2testesantander.network.api.SetupRetrofit
import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.network.model.StatementResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountPresenter : AccountContract.Presenter {

    var loginResponse: LoginResponse? = null
    var view: AccountContract.View? = null

    override fun start() {
        if (this.loginResponse == null) {
            this.view?.showMsgFail("Falha na obtenção dos dados, desculpe-nos!")
            this.view?.hideProgressBar()
            return
        }
        startPaymentList()
        this.view?.dataHeader(loginResponse!!)
    }

    private fun startPaymentList() {
        if (view?.getContext()?.let { HelperFormat.isConnected(it) }!!) {
            val retrofitClient = SetupRetrofit
                .getRetrofitInstance()
            val connection = retrofitClient.create(Retrofit::class.java)
            val call = loginResponse?.userAccount?.userId?.let { connection.getStatements(it) }

            call?.enqueue(object : Callback<StatementResponse> {
                override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
                    t.message?.let { view?.showMsgFail(it) }
                    view?.hideProgressBar()
                }
                override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                    response.body()?.let { view?.accountListFill(it) }
                }
            })
        } else {
            this.view?.showMsgFail(this.view!!.getContext().getString(R.string.no_connection_text))
        }
    }

    override fun onAttach(view: AccountContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun loginResponse(loginResponse: LoginResponse) {
        this.loginResponse = loginResponse
    }
}