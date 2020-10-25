package com.everton.trinitychallengeapp.presentation.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.everton.trinitychallengeapp.data.model.User
import com.everton.trinitychallengeapp.presentation.BaseViewModel
import com.everton.trinitychallengeapp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : BaseViewModel() {

    lateinit var firebaseAuth: FirebaseAuth
    var userLoginStart = MutableLiveData<Unit>()
    var errorLogin = MutableLiveData<String>()

    fun startLogin(user: User, progressBarLogin: View) {
        firebaseAuth = FirebaseConfiguration().getFirebaseAuth()
        firebaseAuth.signInWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBarLogin.visibility = View.GONE
                userLoginStart.value = Unit
            } else {
                errorLogin.value = "Erro ao realizar login. Verifique usuário e senha."
                progressBarLogin.visibility = View.GONE
            }
        }
    }
}