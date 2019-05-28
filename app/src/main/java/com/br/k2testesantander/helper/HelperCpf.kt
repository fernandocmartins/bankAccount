package com.br.k2testesantander.helper

import java.util.*
import java.util.regex.Pattern

class HelperCpf {

  companion object{

      fun checkCpf(CPF: String): Boolean {

          var cpf = CPF
          cpf = specialCharRemove(cpf)

          if (cpf == "00000000000" ||
              cpf == "11111111111" ||
              cpf == "22222222222" ||
              cpf == "33333333333" ||
              cpf == "44444444444" ||
              cpf == "55555555555" ||
              cpf == "66666666666" ||
              cpf == "77777777777" ||
              cpf == "88888888888" ||
              cpf == "99999999999" ||
              cpf.length != 11)
              return false

          val number10: Char
          val number11: Char
          var sum: Int
          var aux: Int
          var aux2: Int
          var number: Int
          var weight: Int

          try {
              sum = 0
              weight = 10
              aux = 0
              while (aux < 9) {
                  number = cpf[aux].toInt() - 48
                  sum = sum + number * weight
                  weight = weight - 1
                  aux++
              }

              aux2 = 11 - sum % 11
              if (aux2 == 10 || aux2 == 11)
                  number10 = '0'
              else
                  number10 = (aux2 + 48).toChar()

              sum = 0
              weight = 11
              aux = 0
              while (aux < 10) {
                  number = cpf[aux].toInt() - 48
                  sum = sum + number * weight
                  weight = weight - 1
                  aux++
              }

              aux2 = 11 - sum % 11
              if (aux2 == 10 || aux2 == 11)
                  number11 = '0'
              else
                  number11 = (aux2 + 48).toChar()

              return number10 == cpf[9] && number11 == cpf[10]
          } catch (erro: InputMismatchException) {
              return false
          }
      }

      fun checkEmail(target: CharSequence?): Boolean {
          return if (target == null) false else android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
      }

      fun checkPassword(password : String) : Boolean {
          return if(password.length >= 2) {
              val letter = Pattern.compile("[A-z]")
              val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
              val hasLetter = letter.matcher(password)
              val hasSpecial = special.matcher(password)
              hasLetter.find() && hasSpecial.find()
          } else {
              false
          }
      }

       fun specialCharRemove(doc: String): String {
          var cpfNumber = doc
          if (cpfNumber.contains(".")) {
              cpfNumber = cpfNumber.replace(".", "")
          }
          if (cpfNumber.contains("-")) {
              cpfNumber = cpfNumber.replace("-", "")
          }
          if (cpfNumber.contains("/")) {
              cpfNumber = cpfNumber.replace("/", "")
          }
          return cpfNumber
      }
  }
}