package com.br.k2testesantander.account

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.br.k2testesantander.R
import com.br.k2testesantander.network.model.Statement

class AccountAdapter(private val listStatement: ArrayList<Statement>, private val context: Context) :
    RecyclerView.Adapter<AccountItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AccountItemViewHolder {
        return AccountItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_statement, parent, false))
    }

    override fun getItemCount(): Int {
        return listStatement.size
    }

    override fun onBindViewHolder(holderAccount: AccountItemViewHolder, position: Int) {
        holderAccount.bindInfo(listStatement[position])
    }
}
