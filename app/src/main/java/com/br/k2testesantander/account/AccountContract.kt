package com.br.k2testesantander.account

import com.br.k2testesantander.BasePresenter
import com.br.k2testesantander.BaseView
import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.network.model.StatementResponse

interface AccountContract {

    interface View : BaseView {
        fun dataHeader(loginResponse: LoginResponse)
        fun accountListFill(statements : StatementResponse)
    }

    interface Presenter : BasePresenter {
        fun loginResponse(loginResponse : LoginResponse)
        fun onAttach(view: View)
    }
}