package com.example.movies.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.model.baseUrlImg
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.domain.model.Movie
import com.example.movies.util.ItemActionListener
import com.example.movies.util.UIBehavior
import com.example.movies.view.activity.MovieDetailsActivity
import com.example.movies.view.adapter.MovieAdapter
import com.example.movies.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

//id de listas de peliculas
const val MARVEL_UNIVERSE_MOVIES: Long = 1
const val BEST_PICTURES_NOMINATIONS: Long = 4
const val THE_AVENGERS: Long = 5
const val FILMS_DEJA_VUS: Long = 6
const val GROSSING_FILMS_OF_ALL_TIME: Long = 10
const val STREAM_AW: Long = 13

@AndroidEntryPoint
class MoviesFragment() : Fragment(), UIBehavior, UIBehavior.RecyclerView, ItemActionListener {

    companion object{
        val instance = MoviesFragment()
    }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun initUI() {
        movieAdapter = MovieAdapter(this)
        initRecyclerView()

        viewModel.getMoviesList(context!!)

        viewModel.moviesListLiveData.observe(viewLifecycleOwner, Observer {
            movieAdapter.updateData(it.last())

            val itemRandom= it.random().random()
            Glide.with(this).load("$baseUrlImg${itemRandom.backdrop_path}").into(binding.posterImageView)
            binding.titleTextView.text = itemRandom.title
            binding.descriptionTextView.text = itemRandom.overview
        })
    }

    override fun onClickItem(item: Any, position: Int) {
        val intent = Intent(context, MovieDetailsActivity::class.java).apply {
            putExtra("movie", item as Movie)
        }
        startActivity(intent)
    }

    override fun initRecyclerView() {
        binding.listOneRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.listOneRecyclerView.adapter = movieAdapter
    }


}