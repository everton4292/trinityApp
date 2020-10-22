package com.everton.trinitychallengeapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.everton.trinitychallengeapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginIntent = Intent(this, HomeActivity::class.java)
        val cadastroIntent = Intent(this, CadastroActivity::class.java)

        materialButtonEntrar.setOnClickListener {

        }

        materialButtonIrParaCadastro.setOnClickListener{
            startActivity(cadastroIntent)
        }
    }
}