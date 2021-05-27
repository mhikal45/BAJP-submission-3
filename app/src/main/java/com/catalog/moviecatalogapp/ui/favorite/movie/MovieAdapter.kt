package com.catalog.moviecatalogapp.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catalog.moviecatalogapp.BuildConfig
import com.catalog.moviecatalogapp.data.source.local.entity.MovieEntity
import com.catalog.moviecatalogapp.databinding.ItemMovieListBinding
import com.catalog.moviecatalogapp.ui.detail.DetailActivity
import com.catalog.moviecatalogapp.ui.detail.DetailViewModel.Companion.MOVIE

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(private val binding : ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.filmName
                tvItemDate.text = movie.filmDate
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE,movie.filmId)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY,MOVIE)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL +movie.poster)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}