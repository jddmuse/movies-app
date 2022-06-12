package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.movies.view.MovieAdapter
import com.example.movies.view.MainViewPagerAdapter
import com.example.movies.view.fragment.MoviesFragment
import com.example.movies.view.fragment.RecommendedFragment
import com.example.movies.view.fragment.TvFragment
import com.google.android.material.tabs.TabLayoutMediator

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
    }

    private fun initUI() {
        movieAdapter = MovieAdapter()
        initRecyclerView()
        initViewPager()

        viewModel.moviesLiveData.observe(this, Observer { items ->
            movieAdapter.updateData(items)
        })
    }

    private fun initViewPager() {
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager
        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(RecommendedFragment(), "For you")
        viewPagerAdapter.addFragment(MoviesFragment(), "Movies")
        viewPagerAdapter.addFragment(TvFragment(), "TV")
        viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            when(position){
                0 -> tab.text = "For you"
                1 -> tab.text = "Movies"
                2 -> tab.text = "TV"
            }
        }.attach()
    }


    private fun initRecyclerView() {
        /*binding.moviesRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.moviesRecyclerView.adapter = movieAdapter*/
    }
}