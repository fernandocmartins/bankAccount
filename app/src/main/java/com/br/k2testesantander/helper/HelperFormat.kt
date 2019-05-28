package com.br.k2testesantander.helper

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class HelperFormat {

    companion object {

        fun accountFormat(agency : String, bank : String) : String{
            val bankFormat = bank.substring(0,2) + "." + bank.substring(2 ,bank.length -1) + "-" + bank.substring(bank.length-1)
            return "$agency / $bankFormat"
        }

        fun moneyCheck(value : Float) : String {
            val ptBr = Locale("pt", "BR")
            return NumberFormat.getCurrencyInstance(ptBr).format(value)
        }

        fun formatDate(inputDateStr : String) : String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = inputFormat.parse(inputDateStr)
            return outputFormat.format(date)
        }

        fun isConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = connectivityManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
    }
}