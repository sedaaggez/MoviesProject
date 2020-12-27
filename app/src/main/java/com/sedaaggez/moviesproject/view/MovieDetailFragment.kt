package com.sedaaggez.moviesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sedaaggez.moviesproject.R
import com.sedaaggez.moviesproject.databinding.FragmentMovieDetailBinding
import com.sedaaggez.moviesproject.viewmodel.MovieDetailViewModel

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private var movieId = 0
    private lateinit var dataBinding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).movieId
        }

        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        viewModel.getDataFromRoom(movieId)

        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.movieResultLiveData.observe(viewLifecycleOwner, Observer { movieResult ->
            movieResult.let {
                dataBinding.movieDetail = movieResult
            }
        })
    }
}