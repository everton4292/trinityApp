package com.everton.trinitychallengeapp.presentation.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.data.model.User
import com.everton.trinitychallengeapp.presentation.cadastro.CadastroActivity
import com.everton.trinitychallengeapp.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var sharedPref: SharedPreferences
    var isRemembered = false
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBarLogin.visibility = View.GONE
        setupObservers()

        sharedPref = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPref.getBoolean("CHECKBOX", false)

        if(isRemembered){
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }

        val cadastroIntent = Intent(this, CadastroActivity::class.java)

        materialButtonIrParaCadastro.setOnClickListener {
            startActivity(cadastroIntent)
        }

        materialButtonEntrar.setOnClickListener {
            if (validateFields(editTextLoginSenha.text.toString())) {
                progressBarLogin.visibility = View.VISIBLE
                user = User(
                    email = editTextLoginEmail.text.toString(),
                    password = editTextLoginSenha.text.toString()
                )
                loginViewModel.startLogin(user, progressBarLogin)
            }
        }
    }

    private fun setupObservers() {
        loginViewModel.errorLogin.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        loginViewModel.userLoginStart.observe(this, Observer {
            val editor: SharedPreferences.Editor = sharedPref.edit()
            val checked: Boolean = checkBoxRemember.isChecked
            editor.putString("USER_EMAIL", user.email)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()
            startActivity(Intent(this, HomeActivity::class.java).apply {
                putExtra("USER_EMAIL", user.email)
            }
            )
        })
    }

    private fun validateFields(password: String): Boolean {
        if (editTextLoginEmail.text!!.isEmpty()) {
            editTextLoginEmail.error = "Favor inserir e-mail"
            editTextLoginEmail.requestFocus()
            return false
        }
        if (!validatePasswordFormat(password)) {
            editTextLoginSenha.error =
                "A senha deve conter de 8 a 16 digitos e conter pelo menos uma letra\n" +
                        "maiscula, uma minuscula, um número e um caractér especial."
            editTextLoginSenha.requestFocus()
            return false
        }
        if (editTextLoginSenha.text!!.isEmpty()) {
            editTextLoginSenha.error = "Favor inserir senha"
            editTextLoginSenha.requestFocus()
            return false
        }
        if (editTextLoginSenha.text!!.isEmpty() && editTextLoginEmail.text!!.isEmpty()) {
            Toast.makeText(this, "Favor preencher todos os campos", Toast.LENGTH_LONG).show()
        }
        return true
    }

    private fun validatePasswordFormat(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val passwordPattern =
            "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}\$"
        pattern = Pattern.compile(passwordPattern)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }
}