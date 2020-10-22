package com.everton.trinitychallengeapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.data.model.local.User
import com.everton.trinitychallengeapp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        progressBarCadastro.visibility = View.GONE

        materialButtonRegistrar.setOnClickListener {
            if (validateFields()) {
                val user = User(
                    email = textInputLayoutCadastroEmail.editText?.text.toString(),
                    password = textInputLayoutCadastroSenha.editText?.text.toString()
                )
                registerUser(user)
            }
        }
    }

    fun registerUser(user: User) {
        progressBarCadastro.visibility = View.VISIBLE
        firebaseAuth = FirebaseConfiguration().getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBarCadastro.visibility = View.GONE
                Toast.makeText(this, "Cadastro com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            } else {
                var error = ""
                progressBarCadastro.visibility = View.GONE
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthWeakPasswordException) {
                    error = "Digite uma senha mais forte"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    error = "Digite um e-mail válido"
                } catch (e: FirebaseAuthUserCollisionException) {
                    error = "Essa conta já foi cadastrada"
                } catch (e: Exception) {
                    error = "ao cadastrar usuário, foi encontrado o erro: " + e.message
                    e.printStackTrace()
                }
                Toast.makeText(this, "Erro: $error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validateFields(): Boolean {
        if (textInputEditTextCadastroEmail.text!!.isEmpty()) {
            textInputEditTextCadastroEmail.error = "Favor inserir e-mail de cadastro"
            textInputEditTextCadastroEmail.requestFocus()
            return false
        }
        if (textInputEditTextCadastroSenha.text!!.isEmpty()) {
            textInputEditTextCadastroSenha.error = "Favor inserir senha de cadastro"
            textInputEditTextCadastroSenha.requestFocus()
            return false
        }
        if (textInputEditTextCadastroConfirmaSenha.text!!.isEmpty()) {
            textInputEditTextCadastroConfirmaSenha.error =
                "Favor inserir confirmação de senha de cadastro"
            textInputEditTextCadastroConfirmaSenha.requestFocus()
            return false
        }
        if (textInputEditTextCadastroSenha.text.toString() != textInputEditTextCadastroConfirmaSenha.text.toString()) {
            textInputEditTextCadastroConfirmaSenha.error =
                "A senha e a confirmação de senha precisam ser iguais"
            textInputEditTextCadastroConfirmaSenha.requestFocus()
            return false
        }
        return true
    }
}