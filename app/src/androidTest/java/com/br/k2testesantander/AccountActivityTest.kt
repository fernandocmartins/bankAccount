package com.br.k2testesantander

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.k2testesantander.network.model.Error
import com.br.k2testesantander.network.model.LoginResponse
import com.br.k2testesantander.network.model.AccountData
import com.br.k2testesantander.account.AccountActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountActivityTest {

    @get :Rule
    var mActivityRule: ActivityTestRule<AccountActivity> = object :
        ActivityTestRule<AccountActivity>(AccountActivity::class.java) {

        override fun getActivityIntent(): Intent {
            val intentBankApp = Intent()
            val userAccount  = AccountData(1,"Jose da Silva Teste","2050","012314564",3.3445F)
            val fail = Error()
            val loginResponse = LoginResponse(userAccount,fail)
            intentBankApp.putExtra("login",loginResponse)
            return intentBankApp
        }
    }

    @Test
    fun openStatemtsActivity() {
        onView(allOf(withText("Jose da Silva Teste"))).
            check(matches(isDisplayed()))
        onView(allOf(withText("2050 / 01.231456-4"))).
            check(matches(isDisplayed()))
    }
}