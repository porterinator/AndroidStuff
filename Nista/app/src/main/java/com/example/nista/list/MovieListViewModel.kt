package com.example.nista.list

import androidx.lifecycle.ViewModel
import com.example.nista.api.MovieService
import com.example.nista.model.Movie
import com.example.nista.model.TopRatedResponse
import com.example.nista.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MovieListViewModel @Inject constructor (val service : MovieService, val repository : MovieRepository) : ViewModel() {


    val mDisposable = CompositeDisposable()

    val movieList : BehaviorSubject<ArrayList<Movie>> = BehaviorSubject.create()

    val movieClicks: PublishSubject<Movie> = PublishSubject.create()

    val loadMore: PublishSubject<Boolean> = PublishSubject.create()

    var page = 1

    var totalPages = 0


    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    fun getTopRated() {
        service.getTopRated(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    totalPages = it.total_pages
                    if (!movieList.hasValue()) {
                        movieList.onNext(it.results)
                    } else {
                        movieList.value?.let { movies ->
                            movies.addAll(it.results)
                            movieList.onNext(movies)
                        }

                    }
                },
                onError = {
                    Timber.e(it)
                }
            ).let { mDisposable.add(it) }
    }

    init {

       getTopRated()
        movieClicks.subscribe {
            repository.currentMovie.onNext(it)
        }.let { mDisposable.add(it) }

        loadMore.subscribe {
            if (page <= totalPages) {
                ++page
                getTopRated()
            }
        }.let { mDisposable.add(it) }
    }

}