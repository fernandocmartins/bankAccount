package com.br.k2testesantander.account

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.br.k2testesantander.R
import com.br.k2testesantander.helper.HelperFormat
import com.br.k2testesantander.network.model.Statement

class AccountItemViewHolder (itemStatements : View) : RecyclerView.ViewHolder(itemStatements) {

     var paymentText : TextView? = null
     var paymentDateText : TextView? = null
     var paymentDescText : TextView? = null
     var paymentValueText : TextView? = null

    init {
        paymentText = itemStatements.findViewById(R.id.tv_payment)
        paymentDateText = itemStatements.findViewById(R.id.tv_payment_date)
        paymentDescText = itemStatements.findViewById(R.id.tv_payment_discount)
        paymentValueText = itemStatements.findViewById(R.id.tv_payment_value)
    }

    fun bindInfo(statement : Statement) {
        paymentText?.text = statement.title
        paymentDescText?.text = statement.desc
        paymentValueText?.text = HelperFormat.moneyCheck(statement.value)
        paymentDateText?.text = statement.date?.let { HelperFormat.formatDate(it) }
    }
}