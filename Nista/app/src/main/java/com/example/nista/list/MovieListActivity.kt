package com.example.nista.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nista.ComponentManager
import com.example.nista.R
import com.example.nista.detail.MovieActivity
import com.example.nista.di.factory.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var movieListViewModel: MovieListViewModel

    lateinit var movieListAdapter: MovieListAdapter

    val mDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.instance.getActivityComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieListViewModel::class.java);

        movieListAdapter = MovieListAdapter()
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = movieListAdapter

        list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = layoutManager.getChildCount()
                val totalItemCount: Int = layoutManager.getItemCount()
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    movieListViewModel.loadMore.onNext(true)
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        movieListViewModel.movieList.subscribe {
            movieListAdapter.setNewList(it)
        }.let { mDisposable.add(it) }

        movieListAdapter.movieClicks.subscribe {
            movieListViewModel.movieClicks.onNext(it)
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }.let { mDisposable.add(it) }
    }


    override fun onPause() {
        super.onPause()
        mDisposable.clear()
    }
}