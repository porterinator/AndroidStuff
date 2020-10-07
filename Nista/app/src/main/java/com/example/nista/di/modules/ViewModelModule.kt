package com.leroymerlin.cds.di.modules


import androidx.lifecycle.ViewModel
import com.example.nista.detail.MovieViewModel
import com.example.nista.list.MovieListViewModel
import com.leroymerlin.cds.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun movieListViewModel(movieListViewModel: MovieListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun movieViewModel(movieViewModel: MovieViewModel): ViewModel

}
