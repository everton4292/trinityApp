package com.everton.trinitychallengeapp.presentation

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everton.trinitychallengeapp.data.model.local.User
import com.everton.trinitychallengeapp.util.FirebaseConfiguration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroViewModel : ViewModel() {

    lateinit var firebaseAuth: FirebaseAuth
    var error = MutableLiveData<String>()
    var startAct = MutableLiveData<Unit>()

    fun registerUser(user: User, progressBarCadastro: View) {
        progressBarCadastro.visibility = View.VISIBLE
        firebaseAuth = FirebaseConfiguration().getFirebaseAuth()
        firebaseAuth.createUserWithEmailAndPassword(
            user.email,
            user.password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressBarCadastro.visibility = View.GONE
                startAct.value = Unit
            } else {
                progressBarCadastro.visibility = View.GONE
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthWeakPasswordException) {
                    error.value = "Digite uma senha mais forte"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    error.value = "Digite um e-mail válido"
                } catch (e: FirebaseAuthUserCollisionException) {
                    error.value = "Essa conta já foi cadastrada"
                } catch (e: Exception) {
                    error.value = "ao cadastrar usuário, foi encontrado o erro: " + e.message
                    e.printStackTrace()
                }

            }
        }
    }


}