package com.everton.trinitychallengeapp.data.remote

import com.everton.trinitychallengeapp.data.model.Data
import retrofit2.http.GET
import retrofit2.http.Path



interface NasaService {

    @GET("api/v1/rovers/curiosity/photos?sol=100&api_key=RjLeNM6LhKOp2MeyVt9ldrqArIlcYIV3Hy1I7Sdz")
    suspend fun getMarsData(): Data

}