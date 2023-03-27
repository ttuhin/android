package com.tuhin.mymovies.presentation.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.RecyclerView

import com.tuhin.mymovies.databinding.MovieItemBinding
import com.tuhin.mymovies.domain.model.Movie

import com.bumptech.glide.Glide

import com.tuhin.mymovies.constant.RemoteConstant

class MovieAdapter (private val context: Context, movieList : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList : List<Movie>? = movieList

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.titleText.text = movieList?.get(position)?.title
        holder.releaseDateText.text = android.text.format.DateFormat.format("dd MMMM yyyy", movieList?.get(position)?.releaseDate)
        holder.ratingText.text = movieList?.get(position)?.rating.toString()
        val imageLink = "${RemoteConstant.IMAGE_BASE_URL}${movieList?.get(position)?.posterPath}"
        Glide.with(context)
            .load(imageLink)
            .centerCrop()
            .into(holder.posterImage)
    }

    override fun getItemCount(): Int {
        return movieList?.size!!
    }

    override fun getItemId(position: Int): Long {
        return movieList?.get(position)?.id!!.toLong()
    }

    fun setMovieList(movieList : List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        val titleText = itemBinding.titleText
        val releaseDateText = itemBinding.releaseDateText
        val posterImage: ImageView = itemBinding.posterImage
        val ratingText = itemBinding.ratingText

        init {
            itemBinding.root.setOnClickListener(View.OnClickListener {
                val position: Int = adapterPosition
                if (position >= 0) {
                    val selectedId = movieList?.get(adapterPosition)
                    selectedId?.let {
                        _selectedMovie.value = it
                    } ?: run {
                        return@OnClickListener
                    }
                }
            })
        }
    }
}