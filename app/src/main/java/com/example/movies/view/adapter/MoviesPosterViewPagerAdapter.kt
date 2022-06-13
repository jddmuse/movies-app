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
import com.example.movies.databinding.ItemPosterMovieRecommendedBinding
import com.example.movies.domain.model.Movie
import com.squareup.picasso.Picasso

private const val TAG = ":::MoviesPosterViewPagerAdapter -> "

class MoviesPosterViewPagerAdapter: RecyclerView.Adapter<MoviesPosterViewPagerAdapter.ViewHolder>() {

    val moviesPosterList = mutableListOf<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesPosterViewPagerAdapter.ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_poster_movie_recommended, parent, false
        )
    )

    override fun onBindViewHolder(holder: MoviesPosterViewPagerAdapter.ViewHolder, position: Int) {
        val item = moviesPosterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int =
        moviesPosterList.size

    fun updateData(items:List<Movie>){
        Log.d(TAG, "updateData")
        moviesPosterList.clear()
        moviesPosterList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view)  {
        val binding = ItemPosterMovieRecommendedBinding.bind(view)

        fun bind(item:Movie){
            binding.movieNameTextView.text = item.title
            //binding.categoryTextViewAux.text = item.genres[0].name +""
            binding.durationTextView.text = "2h"
            binding.averageTextView.text = item.vote_average.toString()
            binding.popularityTextView.text = item.popularity.toString()
            //Picasso.get().load("$baseUrlImg${item.poster_path}").into(binding.posterImageView)
            //Log.d(TAG, "posterpath= $$baseUrlImg${item.poster_path}")
            Glide.with(itemView).load("$baseUrlImg${item.poster_path}").into(binding.posterImageView)
        }
    }

}