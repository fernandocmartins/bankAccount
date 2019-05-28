package com.br.k2testesantander.account

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.br.k2testesantander.R
import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.network.model.Statement
import com.br.k2testesantander.network.model.StatementResponse
import kotlinx.android.synthetic.main.activity_statements.*

class AccountActivity : AppCompatActivity() , AccountContract.View, AccountHeader.OnItemClickListener{

    lateinit var presenter: AccountContract.Presenter
    lateinit var loginResponse: LoginResponse
    lateinit var adapter: AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statements)
        if(intent.getParcelableExtra<LoginResponse>("login") != null){
            loginResponse = intent.getParcelableExtra("login")
            start()
        }
        setOnClickHeader()
    }

    private fun start() {
        presenter = AccountPresenter()
        presenter.onAttach(this)
        presenter.loginResponse(loginResponse)
        presenter.start()
    }

    override fun dataHeader(loginResponse: LoginResponse) {
        ah_header_statements.setDataClient(loginResponse)
    }

    override fun accountListFill(statements: StatementResponse) {
        rv_statement.layoutManager = LinearLayoutManager(this)
        adapter = AccountAdapter(statements.statementListResponse as ArrayList<Statement>,this)
        rv_statement.adapter = adapter
        hideProgressBar()
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

    override fun onItemClick(item: ImageView?) {
        finish()
    }

    private fun setOnClickHeader() {
        ah_header_statements.setListener(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showMsgFail(msg: String) {
        Snackbar.make(findViewById(android.R.id.content),msg, Snackbar.LENGTH_LONG).show()
    }
}