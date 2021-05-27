package com.catalog.moviecatalogapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.catalog.moviecatalogapp.BuildConfig
import com.catalog.moviecatalogapp.catalog.factory.ViewModelFactory
import com.catalog.moviecatalogapp.databinding.ActivityDetailBinding
import com.catalog.moviecatalogapp.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]

        val filmId = intent.getIntExtra(EXTRA_MOVIE,0)
        val filmCategory = intent.getStringExtra(EXTRA_CATEGORY)
        Log.d("Extras : ","$filmId, $filmCategory")

        if (filmCategory != null) {
            viewModel.setFilmDetail(filmId,filmCategory)
        }
        Log.d("is Loading...","Loading")

        if (filmCategory == DetailViewModel.MOVIE) {
            viewModel.getDetailMovie().observe(this, { detail ->
                when (detail.status) {
                    Status.LOADING -> {binding.progressBar.visibility = View.VISIBLE
                        Log.d("Status Loading...","Loading")}
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val state = detail.data!!.favorite
                        binding.favoriteBtn.isChecked = state
                        binding.favoriteBtn.setOnClickListener {
                            viewModel.setFavoriteMovie()
                        }
                        with(binding) {
                            tvTitle.text = detail.data.filmName
                            tvDate.text = detail.data.filmDate
                            tvRating.text = detail.data.filmRating
                            tvSynopsis.text = detail.data.filmSynopsis

                            Glide.with(this@DetailActivity)
                                .load(BuildConfig.IMAGE_URL +detail.data.poster)
                                .into(imgPoster)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (filmCategory == DetailViewModel.TV_SHOW) {
            viewModel.getDetailTvShow().observe(this, { detail ->
                when (detail.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (detail.data != null) {
                            binding.progressBar.visibility = View.INVISIBLE
                            val state = detail.data.favorite
                            binding.favoriteBtn.isChecked = state
                            binding.favoriteBtn.setOnClickListener {
                                viewModel.setFavoriteTvShow()
                            }
                            with(binding) {
                                tvTitle.text = detail.data.filmName
                                tvDate.text = detail.data.filmDate
                                tvRating.text = detail.data.filmRating
                                tvSynopsis.text = detail.data.filmSynopsis

                                Glide.with(this@DetailActivity)
                                    .load(BuildConfig.IMAGE_URL +detail.data.poster)
                                    .into(imgPoster)
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.progressBar.visibility = View.INVISIBLE
    }
}