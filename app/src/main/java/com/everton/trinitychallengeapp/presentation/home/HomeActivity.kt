package com.everton.trinitychallengeapp.presentation.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by inject()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        val userEmail = intent.getStringExtra("USER_EMAIL")

        userEmailIdentification.text = userEmail

        if (userEmailIdentification.text.isEmpty()) {
            val userEmailShared = preferences.getString("USER_EMAIL", "")
            userEmailIdentification.text = userEmailShared
        }

        materialButtonSair.setOnClickListener {
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}