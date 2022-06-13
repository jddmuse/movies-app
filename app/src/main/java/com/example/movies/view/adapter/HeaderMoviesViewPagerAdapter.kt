package com.example.movies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.baseUrlImg
import com.example.movies.databinding.ItemHeaderFragmentMoviesBinding
import com.example.movies.domain.model.Movie

private const val TAG = "::HeaderMoviesViewPagerAdapter -> "

class HeaderMoviesViewPagerAdapter :
    RecyclerView.Adapter<HeaderMoviesViewPagerAdapter.ViewHolder>() {

    val moviesList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_header_fragment_movies, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = moviesList.size

    fun updateData(items: List<Movie>) {
        Log.d(TAG, "updateData")
        moviesList.clear()
        moviesList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemHeaderFragmentMoviesBinding.bind(view)

        fun bind(item: Movie) {
            Glide.with(itemView).load("$baseUrlImg${item.backdrop_path}")
                .into(binding.posterImageView)
            binding.descriptionTextView.text = item.overview
            binding.titleTextView.text = item.title
        }
    }
}