package com.example.popularmovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.popularmovies.databinding.ItemHomeRecyclerViewBinding
import com.example.popularmovies.model.MovieItem
import com.example.popularmovies.util.loadCircleImage

interface MovieClickListener {
    fun onMovieClicked(movieId: Int?)
}

class MovieAdapter(private val movieList: List<MovieItem?>,private val movieClickListener: MovieClickListener): RecyclerView.
Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHomeRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]

        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.textViewMovieVote.text = movie?.voteAverage.toString()

        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)

        holder.binding.root.setOnClickListener{
            movieClickListener.onMovieClicked(movieId = movie?.id)
        }


    }
}