package com.example.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.view.adapter.MainViewPagerAdapter
import com.example.movies.view.adapter.MovieAdapter
import com.example.movies.view.fragment.MoviesFragment
import com.example.movies.view.fragment.RecommendedFragment
import com.example.movies.view.fragment.TvFragment
import com.example.movies.view.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

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
        initBottomNavigationView()
        //initViewPager()

    }

    private fun initBottomNavigationView() {
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home_fragment ->{
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, MoviesFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.fav_fragment -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, RecommendedFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.tv_fragment -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, TvFragment.instance)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                else -> false
            }
        }

    }

    /*private fun initViewPager() {
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
    }*/
}