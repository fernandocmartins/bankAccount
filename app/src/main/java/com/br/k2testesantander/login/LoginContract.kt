package com.br.k2testesantander.login

import android.content.Context
import android.widget.EditText
import com.br.k2testesantander.BasePresenter
import com.br.k2testesantander.BaseView
import com.br.k2testesantander.network.model.LoginResponse

interface LoginContract {

    interface View : BaseView {
        fun openScreenStatement(loginResponse: LoginResponse)
    }

    interface Presenter : BasePresenter {
        fun setEditUser(editUser: EditText)
        fun setEditPassword(editPassword : EditText)
        fun startLogin()
        fun attachView(view: View)
    }
}