package com.catalog.moviecatalogapp.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catalog.moviecatalogapp.BuildConfig
import com.catalog.moviecatalogapp.data.source.local.entity.TvShowEntity
import com.catalog.moviecatalogapp.databinding.ItemTvshowListBinding
import com.catalog.moviecatalogapp.ui.detail.DetailActivity
import com.catalog.moviecatalogapp.ui.detail.DetailViewModel.Companion.TV_SHOW

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(private val binding : ItemTvshowListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (show: TvShowEntity) {
            with(binding) {
                binding.tvItemTitle.text = show.filmName
                binding.tvItemDate.text = show.filmDate

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE,show.filmId)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY,TV_SHOW)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                        .load(BuildConfig.IMAGE_URL + show.poster)
                        .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTvShowBinding = ItemTvshowListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = getItem(position)
        if (show != null) {
            holder.bind(show)
        }
    }

}