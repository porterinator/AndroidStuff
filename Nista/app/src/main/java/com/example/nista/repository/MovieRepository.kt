package com.example.nista.repository

import com.example.nista.api.MovieService
import com.example.nista.model.Movie
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class MovieRepository(val service : MovieService) {

    val currentMovie : BehaviorSubject<Movie> = BehaviorSubject.create()

}