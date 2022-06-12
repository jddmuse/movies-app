package com.example.movies.view.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING
import com.example.movies.databinding.FragmentRecommendedBinding
import com.example.movies.util.UIBehavior
import com.example.movies.view.adapter.MoviesPosterViewPagerAdapter
import com.example.movies.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val TAG = "::RecommendedFragment -> "

@AndroidEntryPoint
class RecommendedFragment : Fragment(), UIBehavior {

    private var _binding:FragmentRecommendedBinding? = null
    private val binding get() = _binding!!

    private val viewModel:MainViewModel by viewModels()
    private lateinit var moviesPosterAdapter:MoviesPosterViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecommendedBinding.inflate(inflater, container, false)
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
        initViewPager2()
    }

    fun initViewPager2(){
        val viewPager = binding.moviesPosterViewPager
        val handler = Handler()

        moviesPosterAdapter = MoviesPosterViewPagerAdapter()
        viewPager.adapter = moviesPosterAdapter

        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { items ->
            moviesPosterAdapter.updateData(items)
        })

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val runnable = Runnable {
                    viewPager.currentItem = position+1
                    Log.e(TAG, "Siguiente poster")
                }
                if (position < viewPager.adapter?.itemCount ?: 0)
                    handler.postDelayed(runnable, 5000)
            }
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == SCROLL_STATE_DRAGGING)
                    handler.removeMessages(0)
            }
        })
    }

}