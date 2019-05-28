package com.br.k2testesantander.login

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.br.k2testesantander.R
import com.br.k2testesantander.helper.HelperFormat
import com.br.k2testesantander.helper.HelperCpf
import com.br.k2testesantander.network.api.Retrofit
import com.br.k2testesantander.network.api.SetupRetrofit
import com.br.k2testesantander.network.model.UserLogin
import com.br.k2testesantander.network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : LoginContract.Presenter {

    private var userAccount: EditText? = null
    private var userPassword: EditText? = null
    private var view : LoginContract.View ? = null

    override fun start() {
    }

    override fun startLogin() {

        val user  =  userAccount?.text.toString()
        this.view?.startProgressBar()

        if(!HelperCpf.checkCpf(user) && !HelperCpf.checkEmail(user)) {
            this.view?.hideProgressBar()
            this.view?.showMsgFail(this.view!!.getContext().getString(R.string.invalid_user_text))
            userAccount?.requestFocus()
            return
        }

        if(!HelperCpf.checkPassword(userPassword?.text.toString())){
            this.view?.hideProgressBar()
            this.view?.showMsgFail(this.view!!.getContext().getString(R.string.invalid_password_text))
            userPassword?.requestFocus()
            return
        }
        startLoginUser()
    }

    private fun startLoginUser() {
        val login =
            UserLogin(userAccount?.text.toString(), userPassword?.text.toString())
        checkDataLogin(login)
    }

    private fun checkDataLogin(loginModel: UserLogin) {

        if(view?.getContext()?.let { HelperFormat.isConnected(it) }!!) {
            val retrofitClient = SetupRetrofit.getRetrofitInstance()
            val network = retrofitClient.create(Retrofit::class.java)
            val callback = network.login(loginModel.user, loginModel.password)
            callback.enqueue(object : Callback<LoginResponse> {

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.message?.let { view?.showMsgFail(it) }
                    view?.hideProgressBar()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    response.body()?.let { view?.openScreenStatement(it) }
                    view?.hideProgressBar()
                }
            })
        } else {
            view?.hideProgressBar()
            this.view?.showMsgFail(this.view!!.getContext().getString(R.string.no_connection_text))
        }
    }

    override fun setEditUser(editUser: EditText) {
        userAccount = editUser
    }

    override fun setEditPassword(editPassword: EditText) {
        userPassword = editPassword
    }

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }
}