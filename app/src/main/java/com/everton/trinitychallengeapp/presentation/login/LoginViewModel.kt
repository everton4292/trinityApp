package com.everton.trinitychallengeapp.presentation.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everton.trinitychallengeapp.data.model.User
import com.everton.trinitychallengeapp.data.repository.TrinityRepository
import com.everton.trinitychallengeapp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LoginViewModel(private val trinityRepository: TrinityRepository) : ViewModel() {

    lateinit var firebaseAuth: FirebaseAuth
    var userLoginStart = MutableLiveData<Unit>()
    var errorLogin = MutableLiveData<String>()
    var errorSaveToken = MutableLiveData<String>()

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

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