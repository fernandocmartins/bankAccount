package com.br.k2testesantander

import com.br.k2testesantander.helper.HelperCpf
import org.junit.Assert
import org.junit.Test

class HelperCpfTest {

    @Test
    fun fail_validationCpf(){
        val cpf = "111.111.111-11"
        val verify = HelperCpf.checkCpf(cpf)
        Assert.assertFalse(verify)
    }

    @Test
    fun null_validationCpf() {
       val cpf = ""
       val verify = HelperCpf.checkCpf(cpf)
       Assert.assertFalse(verify)
    }

    @Test
    fun ok_validationCpf(){
        val cpf = "550.945.038-04"
        val verify = HelperCpf.checkCpf(cpf)
        Assert.assertTrue(verify)
    }

    @Test
    fun ok_validationPassword() {
        val information = "Q@"
        val verify = HelperCpf.checkPassword(information)
        Assert.assertTrue(verify)
    }

    @Test
    fun fail_validationPassword() {
        val pass = "2345677"
        val verify = HelperCpf.checkPassword(pass)
        Assert.assertFalse(verify)
    }

    @Test
    fun null_validationPassword() {
        val pass = ""
        val verify = HelperCpf.checkPassword(pass)
        Assert.assertFalse(verify)
    }
}