package com.sedaaggez.moviesproject.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.moviesproject.R
import com.sedaaggez.moviesproject.adapter.MovieAdapter
import com.sedaaggez.moviesproject.common.API_KEY
import com.sedaaggez.moviesproject.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val movieAdapter = MovieAdapter(arrayListOf())
    private var page = 1
    private var totalPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.getData(API_KEY, page)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = movieAdapter

        linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

        observeLiveData()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    var findLastCompletelyVisibleItemPosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    var itemCount = linearLayoutManager.itemCount

                    if (page < totalPage && findLastCompletelyVisibleItemPosition == itemCount - 1) {
                        page++
                            viewModel.getData(API_KEY, page)
                    }
                }

                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    private fun observeLiveData() {

        viewModel.results.observe(viewLifecycleOwner, Observer { results ->
            results.let {
                recyclerView.visibility = View.VISIBLE
                movieAdapter.updateResultList(results)
            }
        })

        viewModel.totalPage.observe(viewLifecycleOwner, Observer { totalLiveData ->
            totalLiveData.let {
                totalPage = totalLiveData
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                    textViewError.visibility = View.GONE
                    recyclerView.visibility = View.GONE

                } else {
                    progressBar.visibility = View.GONE

                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    textViewError.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    textViewError.visibility = View.GONE
                }
            }
        })
    }
}