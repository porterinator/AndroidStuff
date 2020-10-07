package com.example.nista.api

import com.example.nista.model.TopRatedResponse
import io.reactivex.Single
import retrofit2.http.Query


class MovieService(val movieApi : MovieApi) {

    fun getTopRated(page : Int): Single<TopRatedResponse> {
        return movieApi.getTopRated(page)
    }
}
