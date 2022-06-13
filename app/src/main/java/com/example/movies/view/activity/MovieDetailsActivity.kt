package com.example.movies.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.baseUrlImg
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.databinding.ActivityMovieDetailsBinding
import com.example.movies.domain.model.Movie
import com.example.movies.util.UIBehavior

class MovieDetailsActivity : AppCompatActivity(), UIBehavior {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
    }

    override fun initUI() {
        val item = intent?.getSerializableExtra("movie") as Movie
        Glide.with(applicationContext).load("$baseUrlImg${item.backdrop_path}")
            .into(binding.backdropImageView)
        Glide.with(applicationContext).load("$baseUrlImg${item.poster_path}")
            .into(binding.posterImageView)
        binding.titleTextView.text = item.title
        binding.descriptionTextView.text = item.overview
    }
}