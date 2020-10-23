package com.everton.trinitychallengeapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everton.trinitychallengeapp.data.model.User
import com.everton.trinitychallengeapp.data.repository.TrinityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val trinityRepository: TrinityRepository) : ViewModel() {

    var errorDeleteToken = MutableLiveData<String>()

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )



}