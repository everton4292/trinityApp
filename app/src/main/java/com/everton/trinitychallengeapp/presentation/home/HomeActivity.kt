package com.everton.trinitychallengeapp.presentation.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.everton.trinitychallengeapp.R
import com.everton.trinitychallengeapp.data.model.Photo
import com.everton.trinitychallengeapp.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeAdapter: HomeAdapter by inject()
    private val homeViewModel: HomeViewModel by inject()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel.getMarsData()
        homeViewModel.listPhotos.observe(this, Observer {
            if(it != null) updatePhotoList(it)
        })
        loadRecyclerView()


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

    private fun updatePhotoList(photoList: List<Photo>) {
        homeAdapter.updateData(photoList)
    }

    private fun loadRecyclerView() {
        val linearLayoutTodo = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        homeRecyclerView.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
            this.layoutManager = linearLayoutTodo
        }
    }
}