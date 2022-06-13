package com.example.movies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.MovieModel
import com.example.movies.data.model.baseUrlImg
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.domain.model.Movie
import com.example.movies.util.ItemActionListener
import com.squareup.picasso.Picasso

private const val TAG = ":::MovieAdapter -> "

class MovieAdapter(
    val itemActionListener: ItemActionListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val moviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            itemActionListener.onClickItem(movie, position)
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun updateData(items: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(items)
        Log.d(TAG, "updateData")
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean = moviesList.isEmpty()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)

        fun bind(item: Movie) {
            //binding.titleTextView.text = item.title
            //Picasso.get().load("$baseUrlImg${item.poster_path}").into(binding.movieImageView)
            Glide.with(itemView).load("$baseUrlImg${item.poster_path}")
                .into(binding.movieImageView)
        }

    }

}