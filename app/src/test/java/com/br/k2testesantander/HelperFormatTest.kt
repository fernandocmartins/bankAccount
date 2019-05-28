package com.br.k2testesantander

import com.br.k2testesantander.helper.HelperFormat
import org.junit.Assert
import org.junit.Test

class HelperFormatTest {

    @Test
    fun oks_format_date() {
        val dateInserted = "2019-05-07"
        val dateOut = "07/05/2019"
        val verify = HelperFormat.formatDate(dateInserted)
        Assert.assertEquals(dateOut, verify)
    }

    @Test
    fun ok_money_check(){
        val money = 987.15
        val formatMoney = "R$ 987,15"
        val verify = HelperFormat.moneyCheck(money.toFloat())
        Assert.assertEquals(verify,formatMoney)
    }
}