package com.everton.trinitychallengeapp.di.modules

import com.everton.trinitychallengeapp.presentation.cadastro.CadastroViewModel
import com.everton.trinitychallengeapp.presentation.home.HomeAdapter
import com.everton.trinitychallengeapp.presentation.home.HomeViewModel
import com.everton.trinitychallengeapp.presentation.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    single { createTrinityRepository(get(), get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { CadastroViewModel() }

    factory { HomeAdapter() }

}