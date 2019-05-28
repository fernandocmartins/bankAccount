package com.br.k2testesantander.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.br.k2testesantander.R
import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.account.AccountActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , LoginContract.View {

    private lateinit var presenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        start()
        startListener()
    }

    override fun openScreenStatement(loginResponse: LoginResponse) {
        val intent = Intent(this, AccountActivity::class.java)
        intent.putExtra("login", loginResponse)
        startActivity(intent)
        finish()
    }

    override fun startProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun getContext(): Context {
        return this
    }

    private fun start() {
        presenter = LoginPresenter()
        presenter.attachView(this)
        presenter.setEditUser(edt_client)
        presenter.setEditPassword(edt_password)
    }

    private fun startListener() {
        btn_login.setOnClickListener {
            presenter.startLogin()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showMsgFail(msg: String) {
        Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG).show()
    }
}
