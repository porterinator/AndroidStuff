package com.example.nista.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.nista.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieViewModel @Inject constructor (val repository : MovieRepository) : ViewModel() {

    val imageUrl = ObservableField<String>()
    val title = ObservableField<String>()
    val overview = ObservableField<String>()

    val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    init {
        repository.currentMovie.subscribe {
            imageUrl.set(it.poster_path)
            title.set(it.title)
            overview.set(it.overview)
        }.let { mDisposable.add(it) }
    }
}