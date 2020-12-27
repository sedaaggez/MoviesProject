package com.sedaaggez.moviesproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.moviesproject.R
import com.sedaaggez.moviesproject.databinding.ItemMovieBinding
import com.sedaaggez.moviesproject.model.MoviesResult
import com.sedaaggez.moviesproject.view.MoviesFragmentDirections
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val resultList: ArrayList<MoviesResult>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), MovieClickListener {

    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movieResult = resultList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateResultList(newResultList: List<MoviesResult>) {
        resultList.clear()
        resultList.addAll(newResultList)
        notifyDataSetChanged()
    }

    override fun onMovieClicked(v: View) {
        val movieId = v.movieIdText.text.toString().toInt()
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieId)
        Navigation.findNavController(v).navigate(action)
    }
}