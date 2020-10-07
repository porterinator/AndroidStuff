package com.example.nista.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.nista.ComponentManager
import com.example.nista.R
import com.example.nista.databinding.ActivityMovieBinding
import com.example.nista.di.factory.ViewModelFactory
import com.example.nista.list.MovieListViewModel
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel : MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.instance.getActivityComponent(this).inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieViewModel::class.java);

        val binding: ActivityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
    }
}