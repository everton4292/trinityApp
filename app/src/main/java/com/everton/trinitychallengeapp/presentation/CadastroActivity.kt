package com.everton.trinitychallengeapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.data.model.local.User
import com.everton.trinitychallengeapp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private val viewModel: CadastroViewModel by lazy {
        ViewModelProvider(this).get(CadastroViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setupObservers()
        progressBarCadastro.visibility = View.GONE

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.startAct.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })

        materialButtonRegistrar.setOnClickListener {
            if (validateFields()) {
                val user = User(
                    email = textInputLayoutCadastroEmail.editText?.text.toString(),
                    password = textInputLayoutCadastroSenha.editText?.text.toString()
                )
                viewModel.registerUser(user, progressBarCadastro)
            }
        }
    }

    fun setupObservers(){
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.startAct.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })
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