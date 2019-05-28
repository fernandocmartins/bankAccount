package com.br.k2testesantander

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.br.k2testesantander.login.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun loginOkSuccess() {
        onView(withId(R.id.edt_client))
            .perform(typeText("josesilvateste@uol.com.br"),  closeSoftKeyboard())
        onView(withId(R.id.edt_password))
            .perform(typeText("Q@") , closeSoftKeyboard())
        onView(withId(R.id.btn_login))
            .perform(click() , closeSoftKeyboard())
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun loginOkPasswordFail() {
        onView(withId(R.id.edt_client))
            .perform(typeText("josesilvateste@uol.com.br"),  closeSoftKeyboard())
        onView(withId(R.id.edt_password))
            .perform(typeText("123456") , closeSoftKeyboard())
        onView(withId(R.id.btn_login))
            .perform(click() , closeSoftKeyboard())
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.invalid_password_text)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verifyCpfFail() {
        onView(withId(R.id.edt_client))
            .perform(typeText("1111111111"),  closeSoftKeyboard())
        onView(withId(R.id.btn_login))
            .perform(click() , closeSoftKeyboard())
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.invalid_user_text)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verifyEmailFail() {
        onView(withId(R.id.edt_client))
            .perform(typeText("carochinha@.com"),  closeSoftKeyboard())
        onView(withId(R.id.btn_login))
            .perform(click() , closeSoftKeyboard())
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.invalid_user_text)))
            .check(matches(isDisplayed()))
    }
}