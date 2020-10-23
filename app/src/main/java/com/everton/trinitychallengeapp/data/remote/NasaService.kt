package com.everton.trinitychallengeapp.data.remote

import com.everton.trinitychallengeapp.data.model.Data
import retrofit2.http.GET
import retrofit2.http.Path



interface NasaService {

    @GET("api/v1/rovers/curiosity/photos?sol=10&api_key=DEMO_KEY")
    suspend fun getMarsData(): Data

}