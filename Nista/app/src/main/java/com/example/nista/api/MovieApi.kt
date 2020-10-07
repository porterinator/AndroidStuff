package com.example.nista.api

import com.example.nista.model.TopRatedResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/top_rated?api_key=274f828ad283bd634ef4fc1ee4af255f")
    fun getTopRated(@Query("page") page : Int): Single<TopRatedResponse>

}