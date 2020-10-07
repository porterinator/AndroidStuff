package com.example.nista.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nista.R
import com.example.nista.databinding.ListitemMovieBinding
import com.example.nista.model.Movie
import io.reactivex.subjects.PublishSubject
import java.util.*
import kotlin.collections.ArrayList

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var movieList: ArrayList<Movie> = ArrayList()
    val movieClicks: PublishSubject<Movie> = PublishSubject.create()


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListitemMovieBinding>(inflater, R.layout.listitem_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(movieList.get(pos))
        holder.itemView.setOnClickListener {
            movieClicks.onNext(movieList.get(pos))
        }
    }

    override fun getItemCount(): Int {
        return if (movieList == null) 0 else movieList.size
    }
    public fun setNewList(list:List<Movie>){
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ListitemMovieBinding

        constructor(binding: ListitemMovieBinding) : this(binding.root) {
            this.binding = binding
        }

        fun bind(movie: Movie) {
            binding.viewmodel = movie
            binding.executePendingBindings()
        }
    }

}